package com.parkinglot.admin.controller.impl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
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
import com.parkinglot.common.util.JsonResult;
import com.sun.tools.doclint.Entity;

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

	/**
	 * 新用户办理停车卡
	 */
	@RequestMapping(value = "/createNewParkingCard", method = RequestMethod.POST)
	@ResponseBody
	@Override
	public JsonResult createNewParkingCard(@RequestBody UserAndCardEntity entity) {
		System.out.println(entity);
		UsersInfoEntity userEntity = new UsersInfoEntity();
		userEntity.setPhone(entity.getPhone());
		userEntity.setUsername(entity.getUsername());
		userEntity.setPassword(entity.getPassword());
		System.out.println(userEntity);

		JsonResult jsonResult = new JsonResult();
		// 判断当前是否是出账日，出账日不能办理新卡
		if (isLastDay()) {
			jsonResult = new JsonResult(new ServiceException("今天是出账日，暂停办理业务"));
			return jsonResult;
		} else {
			
			// 查询该用户是否存在，如果存在则反显用户信息
			UsersInfoEntity user = userService.selectUserInfoByPhone(userEntity.getPhone());
			System.out.println("user" + user);
			// 查询停车卡号是否存在
			List<ParkingCardEntity> card = cardService.selectAllCards();
			System.out.println("card" + card);
			// 如果该用户存在
			ParkingLotEntity parkingLotEntity = parkingService.selectParkingLotByNum(entity.getParkingNum());
			if (user != null) {
				jsonResult = new JsonResult(user);
				return jsonResult;
			}
			if (card != null) {
				for (int i = 0; i < card.size(); i++) {
					if (card.get(i).getCardNum() == entity.getCardNum()) {
						jsonResult = new JsonResult(new ServiceException("该卡号已存在"));
						return jsonResult;
					}
				}

			}
			if (parkingService.selectParkingLotByNum(entity.getParkingNum())==null) {
				System.out.println("cardEntity.getParkingNum()");
				jsonResult = new JsonResult(new ServiceException("该编号停车场不存在"));
				System.out.println(jsonResult);
				return jsonResult;
			}
			ParkingCardEntity cardEntity = new ParkingCardEntity();
			cardEntity.setParkingId(parkingLotEntity.getId());
			cardEntity.setCardNum(entity.getCardNum());
			System.out.println("userEntity" + userEntity);
			// 注册新用户
			userService.insertUserInfo(userEntity);
			cardEntity.setUserId(userEntity.getId());
			// 添加停车卡
			System.out.println(cardEntity);
			cardService.insertParkingCard(cardEntity);
			generateBill(entity);
			return jsonResult;

		}
	}

	/**
	 * 老用户办卡
	 */
	@RequestMapping("/createNewParkingByOldUser")
	@ResponseBody
	@Override
	public JsonResult createNewParkingCardByOldUser(@RequestBody UserAndCardEntity cardEntity) {
		JsonResult jsonResult = new JsonResult();
		List<ParkingCardEntity> card = cardService.selectAllCards();

		ParkingLotEntity parkingLotEntity = parkingService.selectParkingLotByNum(cardEntity.getParkingNum());
		System.out.println(cardEntity);
		System.out.println("card" + card);
		System.out.println("parkingLotEntity:"+parkingLotEntity);
		// 判断当前是否是出账日，出账日不能办理新卡
		if (isLastDay()) {
			jsonResult = new JsonResult(new ServiceException("今天是出账日，暂停办理业务"));
			return jsonResult;
		}

		// 查询停车卡号是否存在
		if (card != null) {
			for (int i = 0; i < card.size(); i++) {
				if (card.get(i).getCardNum().equals(cardEntity.getCardNum())) {
					jsonResult = new JsonResult(new ServiceException("该卡号已存在"));
					return jsonResult;
				}
			}
		} 
		if (parkingLotEntity==null) {
			System.out.println("1111");
			jsonResult = new JsonResult(new ServiceException("该停车场编号不存在"));
			return jsonResult;
			// return new JsonResult(new ServiceException("该停车场编号不存在"));
		} else if (cardService.selectCards(parkingLotEntity.getId()) >= parkingService
				.selectParkingLotById(parkingLotEntity.getId()).getTotal()) {
			jsonResult = new JsonResult(new ServiceException("该停车场已满"));
			return jsonResult;
		} else {
			// 判断用户是否负费
			ParkingCardEntity parkingCardEntity = cardService.selectParkingCardByCardNum(cardEntity.getCardNum());
			System.out.println(parkingCardEntity);
			UsersInfoEntity user = userService.selectUserInfoById(parkingCardEntity.getUserId());
			System.out.println("user" + user);
			List<ParkingBillEntity> list = parkingBillService.selectAllParkingBillEntity(cardEntity.getPhone());
			List<ParkingCardEntity> list1 = cardService.selectUserCards(user.getId());
			System.out.println("list1:" + list1);
			for (int i = 0; i < list1.size(); i++) {
				System.out.println(i + "  " + list1.get(i).getState());
				if (list1.get(i).getState() == 1) {
					jsonResult = new JsonResult(new ServiceException("账号负费不能创建新卡"));
					return jsonResult;
				}
			}
			
			UserAndCardEntity upEntity = new UserAndCardEntity();
			upEntity.setCardNum(cardEntity.getCardNum());
			upEntity.setParkingNum(cardEntity.getParkingNum());
			upEntity.setPhone(cardEntity.getPhone());
			generateBill(upEntity);
			// 添加停车卡
			ParkingCardEntity CardEntity = new ParkingCardEntity();
			CardEntity.setCardNum(cardEntity.getCardNum());
			CardEntity.setCreatedTime(new Date());
			CardEntity.setParkingId(parkingLotEntity.getId());
			CardEntity.setState(0);
			CardEntity.setUserId(user.getId());
			cardService.insertParkingCard(CardEntity);
			return jsonResult;
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
			jsonResult = new JsonResult(user);
			return jsonResult;
		}
	}

	/***
	 * 判断该停车场是否存在
	 * 
	 * @param parkingNum
	 *            停车场编号
	 * @return 不存在则返回true ，存在则返回false
	 */
	private boolean isHasParkingLot(String parkingNum) {
		boolean flag = false;
		ParkingLotEntity parking = parkingService.selectParkingLotByNum(parkingNum);
		// 如果为Null,则不存在该编号的停车场
		if (parking == null) {
			flag = true;
			System.out.println("isHasParkingLot");
			return flag;
		}
		System.out.println("isHasParkingLot " + parking.getParkingNum());
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
	 * 生成新账单
	 * 
	 * @param entity
	 */
	public void generateBill(UserAndCardEntity entity) {
		ParkingBillEntity parkingBillEntity = new ParkingBillEntity();
		ParkingLotEntity parkingLotEntity = parkingService.selectParkingLotByNum(entity.getParkingNum());
		ParkingCardEntity parkingCardEntity = cardService.selectParkingCardByCardNum(entity.getCardNum());
		int rand = new Random().nextInt(100000);
		parkingBillEntity.setBillNum(String.valueOf(rand));
		parkingBillEntity.setCardId(parkingCardEntity.getId());
		Calendar ca = Calendar.getInstance();
		ca.setTime(new Date());
		int nowDate = ca.get(Calendar.DAY_OF_MONTH);
		Integer year = ca.get(Calendar.YEAR);
		Integer month = ca.get(Calendar.MONTH) + 1;
		switch (month) {
		case 1:
			;
		case 2:
			;
		case 3:
			ca.set(year, 2, 31);
			break;
		case 4:
			;
		case 5:
			;
		case 6:
			ca.set(year, 5, 30);
			break;
		case 7:
			;
		case 8:
			;
		case 9:
			ca.set(year, 8, 30);
			break;
		case 10:
			;
		case 11:
			;
		case 12:
			ca.set(year, 11, 31);
			break;
		}
		System.out.println(ca.getTime());
		parkingBillEntity.setFirstDate(new Date());
		parkingBillEntity.setStatementDate(ca.getTime());
		parkingBillEntity.setFlag(2);
		// 获取当前月天数
		System.out.println(nowDate);
		ca.set(Calendar.DATE, 1);// 把日期设置为当月第一天
		ca.roll(Calendar.DATE, -1);// 日期回滚一天，也就是最后一天
		int maxDate = ca.get(Calendar.DATE);
		System.out.println(maxDate);
		double price = parkingService.selectParkingLotByNum(entity.getParkingNum()).getPrice() * (maxDate - nowDate + 1)
				/ maxDate;
		System.out.println(price);
		parkingBillEntity.setAccount(price);
		parkingBillEntity.setPrice(parkingService.selectParkingLotByNum(entity.getParkingNum()).getPrice());
		parkingBillEntity.setParkingId(parkingLotEntity.getId());
		parkingBillEntity.setParkingName(parkingService.selectParkingLotByNum(entity.getParkingNum()).getParkingName());
		parkingBillEntity.setPhone(entity.getPhone());
		System.out.println(parkingBillEntity);
		parkingBillService.insertParkingBill(parkingBillEntity);
	}
}
