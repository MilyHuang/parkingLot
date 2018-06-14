package com.parkinglot.admin.controller.impl;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.parkinglot.admin.controller.IParkingCardController;
import com.parkinglot.admin.entity.ParkingBillEntity;
import com.parkinglot.admin.entity.ParkingCardEntity;
import com.parkinglot.admin.entity.ParkingLotEntity;
import com.parkinglot.admin.entity.UserAndCardEntity;
import com.parkinglot.admin.entity.UsersInfoEntity;
import com.parkinglot.admin.service.IParkingBillService;
import com.parkinglot.admin.service.IParkingCardService;
import com.parkinglot.admin.service.IParkingLotService;
import com.parkinglot.admin.service.IUsersInfoService;
import com.parkinglot.common.service.ServiceException;
import com.parkinglot.common.util.BillUtils;
import com.parkinglot.common.util.JsonResult;

/**
 * @Description:
 * @version:1.0
 * @author:MilyHuang
 * @Date:May 22, 20182:36:05 PM
 * @Email:Mily-ML.Huang@aia.com
 * 
 */
@Controller
@RequestMapping("/parkingCard")
public class ParkingCardControllerImpl implements IParkingCardController {

	private static Logger logger = Logger.getLogger(ParkingCardControllerImpl.class);

	@Autowired
	private IUsersInfoService userService;

	@Autowired
	private IParkingCardService cardService;

	@Autowired
	private IParkingLotService parkingService;

	@Autowired
	private IParkingBillService parkingBillService;

	@Autowired
	private BillUtils billUtils;
	
	/**
	 * 新用户办理停车卡
	 */
	@RequestMapping(value = "/createNewParkingCard", method = RequestMethod.POST)
	@ResponseBody
	@Override
	public JsonResult createNewParkingCard(@RequestBody UserAndCardEntity entity) {
		UsersInfoEntity userEntity = new UsersInfoEntity();
		userEntity.setPhone(entity.getPhone());
		userEntity.setUsername(entity.getUsername());
		userEntity.setPassword(entity.getPassword());

		JsonResult jsonResult = new JsonResult();
		// 判断当前是否是出账日，出账日不能办理新卡
		if (isLastDay()) {
			jsonResult = new JsonResult(new ServiceException("今天是出账日，暂停办理业务"));
			return jsonResult;
		} else {
			// 查询该用户是否存在，如果存在则反显用户信息
			UsersInfoEntity user = userService.selectUserInfoByPhone(userEntity.getPhone());
			// 如果该用户存在
			if (user != null) {
				//卡的总数
				int count = cardService.countCardsForUser(user.getId()); 
				Map<String, Object> map = new HashMap<String,Object>();
				map.put("user", user);
				map.put("count", count);
				jsonResult = new JsonResult(map);
				return jsonResult;
			}
			
			//查询停车场编号是否存在
			if (!isHasParkingLot(entity.getParkingNum())) {
				jsonResult = new JsonResult(new ServiceException("该编号停车场不存在"));
				return jsonResult;
			}
			
			//停车场信息
			ParkingLotEntity parkingLot = parkingService.selectParkingLotByNum(entity.getParkingNum());
			//判断停车场是否已满
			if (cardService.selectCards(parkingLot.getId()) >= parkingService
					.selectParkingLotById(parkingLot.getId()).getTotal()) {
				jsonResult = new JsonResult(new ServiceException("该停车场已满"));
				return jsonResult;
			}
			
			// 查询可用的停车卡号是否存在
			ParkingCardEntity card = cardService.selectParkingCardByCardNum(entity.getCardNum(), 0); //state: 0 可用 ,1 停用
			if (card != null) {
				return new JsonResult(new ServiceException("该卡号已存在"));
			} 
			
			ParkingCardEntity cardEntity = new ParkingCardEntity();
			cardEntity.setParkingId(parkingLot.getId());
			cardEntity.setCardNum(entity.getCardNum());
			// 注册新用户
			userService.insertUserInfo(userEntity);
			//把用户的id赋给cardEntity
			cardEntity.setUserId(userEntity.getId());
			// 添加停车卡
			cardService.insertParkingCard(cardEntity);
			//生成账单
			ParkingBillEntity billEntity = new ParkingBillEntity();
			billEntity.setParkingId(parkingLot.getId());
			billEntity.setParkingName(parkingLot.getParkingName());
			billEntity.setCardId(cardEntity.getId());
			billEntity.setPhone(userEntity.getPhone());
			billUtils.generateBill(billEntity);
			return jsonResult;

		}
	}

	/**
	 * 老用户办卡
	 */
	@RequestMapping("/createNewParkingByOldUser")
	@ResponseBody
	@Override
	public JsonResult createNewParkingCardByOldUser(@RequestBody UserAndCardEntity entity) {
		JsonResult jsonResult = new JsonResult();
		//判断输入的信息是否为空
		if(entity == null) {
			return new JsonResult(new ServiceException("输入的信息不能为空"));
		}
		// 判断当前是否是出账日，出账日不能办理新卡
		if (isLastDay()) {
			jsonResult = new JsonResult(new ServiceException("今天是出账日，暂停办理业务"));
			return jsonResult;
		}

		//判断该停车场是否存在
		ParkingLotEntity parkingLot = parkingService.selectParkingLotByNum(entity.getParkingNum());
		if (!isHasParkingLot(entity.getParkingNum())) {
			jsonResult = new JsonResult(new ServiceException("该停车场编号不存在"));
			return jsonResult;
		} 
		
		//判断停车场是否已满
		if (cardService.selectCards(parkingLot.getId()) >= parkingService
				.selectParkingLotById(parkingLot.getId()).getTotal()) {
			jsonResult = new JsonResult(new ServiceException("该停车场已满"));
			return jsonResult;

		}
		
		// 查询停车卡号是否存在
		ParkingCardEntity card = cardService.selectParkingCardByCardNum(entity.getCardNum(),0); //state: 0 可用,1 停用
		if (card != null) {
			return new JsonResult(new ServiceException("该卡号已存在"));
		} 
		//判断用户账单是否缴清
		String phone = userService.selectUserInfoById(entity.getUserId()).getPhone();
		Integer flag = 0;  //账单状态为未缴费
		List<ParkingBillEntity> bills = parkingBillService.selectBillsByPhoneAndFlag(phone, flag);
		List<ParkingBillEntity> bills2 = parkingBillService.selectBillsByPhoneAndFlag(phone, 3); //账单欠费
		if(!bills.isEmpty() || !bills2.isEmpty()){
			return new JsonResult(new ServiceException("该用户有账单未缴清，请缴清再办理新卡"));
		}else {

			
			// 添加停车卡
			ParkingCardEntity cardEntity = new ParkingCardEntity();
			cardEntity.setParkingId(parkingLot.getId());
			cardEntity.setUserId(entity.getUserId());
			cardEntity.setCardNum(entity.getCardNum());
			cardService.insertParkingCard(cardEntity);
			//生成bill
			ParkingBillEntity billEntity = new ParkingBillEntity();
			billEntity.setParkingId(parkingLot.getId());
			billEntity.setParkingName(parkingLot.getParkingName());
			billEntity.setCardId(cardEntity.getId());
			billEntity.setPhone(phone);
			billUtils.generateBill(billEntity);
			//查询卡总数
			int count = cardService.countCardsForUser(entity.getUserId());
			return new JsonResult(count);
		}

	}

	@RequestMapping("/selectUserInfoByPhone")
	@ResponseBody
	@Override
	public JsonResult selectUserByPhone(@RequestBody UsersInfoEntity userEntity) {
		JsonResult jsonResult = new JsonResult();
		if (userEntity.getPhone() == null) {
			jsonResult = new JsonResult(new ServiceException("查询的手机号不能为空"));
			return jsonResult;
		}
		UsersInfoEntity user = userService.selectUserInfoByPhone(userEntity.getPhone());
		// 查询不到该用户
		if (user == null) {
			jsonResult = new JsonResult(new ServiceException("该用户不存在"));
			return jsonResult;
		} else {
			//卡的总数
			int count = cardService.countCardsForUser(user.getId()); 
			Map<String, Object> map = new HashMap<String,Object>();
			map.put("user", user);
			map.put("count", count);
			jsonResult = new JsonResult(map);
			return jsonResult;
		}
	}

	@RequestMapping(value="/selectUserCardsForList" , method=RequestMethod.POST)
	@ResponseBody
	@Override
	public JsonResult selectUserCardsList(@RequestBody UsersInfoEntity entity) {
		JsonResult jsonResult = new JsonResult();
		if(entity == null) {
			return new JsonResult(new ServiceException("输入的手机号不能为空"));
		}
		//用户的ID
		Integer userId = userService.selectUserInfoByPhone(entity.getPhone()).getId();
		//用户卡信息
		List<ParkingCardEntity> cards = cardService.selectUserCards(userId);
		if(cards.size() == 0) {
			return new JsonResult("该用户没有办理停车卡");
		}
		for(int i=0;i<cards.size();i++) {
			String parkingName = parkingService.selectParkingLotById(cards.get(i).getParkingId()).getParkingName();
			cards.get(i).setParkingName(parkingName);
		}
		return new JsonResult(cards);
	}
	
	/***
	 * 判断该停车场是否存在
	 * 
	 * @param parkingNum
	 *            停车场编号
	 * @return 不存在则返回true ，存在则返回false
	 */
	private boolean isHasParkingLot(String parkingNum) {
		boolean flag = true;
		ParkingLotEntity parking = parkingService.selectParkingLotByNum(parkingNum);
		// 如果为Null,则不存在该编号的停车场
		if (parking == null) {
			flag = false;
			return flag;
		}
		return flag;
	}

	/**
	 * 判断是否是指定的日期
	 * 
	 * @return
	 */
	private boolean isLastDay() {
		boolean flag = false;
		Date date = new Date();
		SimpleDateFormat time = new SimpleDateFormat("MM/dd");
		String now = time.format(date);
		if ("03/31".equals(now) || "06/30".equals(now) || "09/30".equals(now) || "12/31".equals(now)) {
			flag = true;
			return flag;
		}
		return flag;
	}

	/**
	 * 补办新卡
	 * 
	 * @param entity
	 */
	@RequestMapping(value="/createNewCardReplaceOldOne" , method=RequestMethod.POST)
	@ResponseBody
	@Override
	public JsonResult createNewCardReplaceOldOne(@RequestBody ParkingCardEntity cardEntity) {
		JsonResult jsonResult = new JsonResult();
		System.out.println("frfrfrf"+cardEntity);
		int id = cardEntity.getId();
		String cardNum = cardEntity.getCardNum();
		ParkingCardEntity cardinfo = cardService.selectCardByCardNumber(cardNum);
		int cardState = cardService.selectCardStateById(id);
		
		//注销的卡不能补办
		if(cardState == 2) {
			jsonResult = new JsonResult(new ServiceException("该卡已注销！"));
		}
		//卡号必须不存在
		if(cardinfo != null)
		{
			jsonResult = new JsonResult(new ServiceException("卡号已存在！"));
		}
		//补办新卡
		else {
			System.out.println("frfrfrf"+cardNum);
			int rows = cardService.updateCardNumById(id, cardNum);
			if(rows <= 0) {
				jsonResult = new JsonResult(new ServiceException("补办失败！"));
			}
		}
		return jsonResult;
	}

	
	
}
	
