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
		UsersInfoEntity userEntity = new UsersInfoEntity();
		userEntity.setPhone(entity.getPhone());
		userEntity.setUsername(entity.getUsername());
		userEntity.setPassword(entity.getPassword());
		ParkingCardEntity cardEntity = new ParkingCardEntity();
		cardEntity.setParkingId(parkingService.selectParkingLotByNum(entity.getParkingNum()).getId());
		cardEntity.setCardNum(entity.getCardNum());
		JsonResult jsonResult = new JsonResult();
		// 判断当前是否是出账日，出账日不能办理新卡
		if (isLastDay()) {
			jsonResult = new JsonResult(new ServiceException("今天是出账日，暂停办理业务"));
			return jsonResult;
		}
		// 判断输入的信息是否为null
		if (userEntity == null || cardEntity == null) {
			jsonResult = new JsonResult(new ServiceException("输入的信息不能为空"));
			return jsonResult;
		} else {
			// 查询该用户是否存在，如果存在则反显用户信息
			UsersInfoEntity user = userService.selectUserInfoByPhone(userEntity.getPhone());
			// 查询停车卡号是否存在
			ParkingCardEntity card = cardService.selectCardByCardNum(cardEntity.getCardNum());
			// 如果该用户存在
			if (user != null) {
				jsonResult = new JsonResult(user);
				return jsonResult;
			} else if (card != null) {
				return new JsonResult(new ServiceException("该卡号已存在"));
			} else if (!isHasParkingLot(entity.getParkingNum())) {
				jsonResult = new JsonResult(new ServiceException("该编号停车场不存在"));
				return jsonResult;
			} else {
				// 注册新用户
				userService.insertUserInfo(userEntity);
				cardEntity.setUserId(userEntity.getId());
				// 添加停车卡
				cardService.insertParkingCard(cardEntity);
				//账单
				generateBill(entity);
				return jsonResult;
			}
		}
	}

	/**
	 * 老用户办卡
	 */
	@RequestMapping("/createNewParkingByOldUser")
	@ResponseBody
	@Override
	public JsonResult createNewParkingCardByOldUser(@RequestBody ParkingCardEntity cardEntity) {
		JsonResult jsonResult = new JsonResult();
		Integer parkingId = parkingService.selectParkingLotByNum(cardEntity.getParkingNum()).getId();
		// 判断当前是否是出账日，出账日不能办理新卡
		if (isLastDay()) {
			jsonResult = new JsonResult(new ServiceException("今天是出账日，暂停办理业务"));
			return jsonResult;
		}
		// 判断输入的信息是否为空
		if (cardEntity == null) {
			jsonResult = new JsonResult(new ServiceException("输入的信息不能为空"));
			return jsonResult;
		}
		// 判断用户是否负费
		UsersInfoEntity user = userService.selectUserInfoById(cardEntity.getUserId());
		List<ParkingBillEntity> list = parkingBillService.selectAllParkingBillEntity(user.getPhone());
		List<ParkingCardEntity> list1 = cardService.selectUserCards(cardEntity.getParkingId(), cardEntity.getUserId());
		for (int i = 0; i < list1.size(); i++) {
			System.out.println(i + "  " + list1.get(i).getState());
			if (list1.get(i).getState() == 1) {
				jsonResult = new JsonResult(new ServiceException("账号负费不能创建新卡"));
				return jsonResult;
			}
		}
		
		ParkingCardEntity card = cardService.selectCardByCardNum(cardEntity.getCardNum());
		// 查询停车卡号是否存在
		if (card != null) {
			jsonResult = new JsonResult(new ServiceException("卡号已存在"));
			return jsonResult;
		} else if (!isHasParkingLot(cardEntity.getParkingNum())) {
			jsonResult = new JsonResult(new ServiceException("该停车场编号不存在"));
			return jsonResult;
			//return new JsonResult(new ServiceException("该停车场编号不存在"));
		} else if (cardService.selectCards(cardEntity.getParkingId()) >= parkingService
				.selectParkingLotByNum(cardEntity.getParkingNum()).getTotal()) {
			jsonResult = new JsonResult(new ServiceException("该停车场已满"));
			return jsonResult;
		} else {
			UserAndCardEntity upEntity = new UserAndCardEntity();
			upEntity.setCardNum(cardEntity.getCardNum());
			upEntity.setParkingNum(cardEntity.getParkingNum());
			upEntity.setPhone(userService.selectUserInfoById(cardEntity.getUserId()).getPhone());
			generateBill(upEntity);
			// 添加停车卡
			cardService.insertParkingCard(cardEntity);
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
	 * @return 存在则返回true ，不存在则返回false
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
	 * 生成新账单
	 * 
	 * @param entity
	 */
	public void generateBill(UserAndCardEntity entity) {
		ParkingBillEntity parkingBillEntity = new ParkingBillEntity();
		int rand = new Random().nextInt(100000);
		parkingBillEntity.setBillNum(String.valueOf(rand));
		parkingBillEntity.setCardNum(entity.getCardNum());
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
		parkingBillEntity.setParkingNum(entity.getParkingNum());
		parkingBillEntity.setParkingName(parkingService.selectParkingLotByNum(entity.getParkingNum()).getParkingName());
		parkingBillEntity.setPhone(entity.getPhone());
		System.out.println(parkingBillEntity);
		parkingBillService.insertParkingBill(parkingBillEntity);
	}
}
