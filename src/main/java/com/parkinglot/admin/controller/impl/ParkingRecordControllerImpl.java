package com.parkinglot.admin.controller.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.parkinglot.admin.controller.IParkingRecordController;
import com.parkinglot.admin.entity.ParkingBillEntity;
import com.parkinglot.admin.entity.ParkingCardEntity;
import com.parkinglot.admin.entity.ParkingLotEntity;
import com.parkinglot.admin.entity.ParkingRecordEntity;
import com.parkinglot.admin.entity.UserAndCardEntity;
import com.parkinglot.admin.service.IParkingBillService;
import com.parkinglot.admin.service.IParkingCardService;
import com.parkinglot.admin.service.IParkingLotService;
import com.parkinglot.admin.service.IParkingRecordService;
import com.parkinglot.admin.service.IUsersInfoService;
import com.parkinglot.common.service.ServiceException;
import com.parkinglot.common.util.JsonResult;

@Controller
@RequestMapping("/parkingRecord")
public class ParkingRecordControllerImpl implements IParkingRecordController {

	@Autowired
	private IParkingCardService parkingCardService;

	@Autowired
	private IParkingRecordService parkingRecordService;

	@Autowired
	private IParkingLotService parkingLotService;

	@Autowired
	private IParkingBillService parkingBillService;

	@Autowired
	private IUsersInfoService userService;

	@RequestMapping("/updateParkingRecord")
	@ResponseBody
	@Override
	public JsonResult updateParkingRecord(@RequestBody ParkingRecordEntity entity) {
		JsonResult jsonResult = new JsonResult();
		ParkingLotEntity parkingLotEntity = parkingLotService.selectParkingLotByNum(entity.getParkingNum());
		if (parkingCardService.selectParkingCardByCardNumAndParkingNum(entity.getCardNum(),
				entity.getParkingNum()) == null) {
			jsonResult = new JsonResult(new ServiceException("该停车卡或停车场不存在"));
			return jsonResult;
		} else if (parkingCardService
				.selectParkingCardByCardNumAndParkingNum(entity.getCardNum(), entity.getParkingNum()).getFlag() == 0) {
			jsonResult = new JsonResult(new ServiceException("您未在此停車場停放辆车"));
			return jsonResult;
		} else if (parkingLotEntity.getInuse() == 0) {
			jsonResult = new JsonResult(new ServiceException("停车场内没有车辆"));
			return jsonResult;
		} else {
			/** 更新停车场正在使用的车位数量 */
			parkingLotEntity.setInuse(parkingLotEntity.getInuse() - 1);
			jsonResult = parkingLotService.updateParkingLotInuse(parkingLotEntity);

			/** 更新停车卡信息 */
			ParkingCardEntity parkingCardEntity = new ParkingCardEntity();
			parkingCardEntity.setCardNum(entity.getCardNum());
			parkingCardEntity.setParkingNum(entity.getParkingNum());
			parkingCardEntity.setFlag(0);
			parkingCardService.updateParkingCard(parkingCardEntity);

			/** 记录停车的记录 */
			ParkingRecordEntity parkingRecordEntity = new ParkingRecordEntity();
			parkingRecordEntity.setCardNum(entity.getCardNum());
			parkingRecordEntity.setParkingNum(entity.getParkingNum());
			parkingRecordEntity.setCheckoutTime(new Date());
			parkingRecordEntity.setFlag(0);
			parkingRecordService.updateParkingRecord(parkingRecordEntity);
			return jsonResult;
		}
	}

	@RequestMapping("/insertParkingRecord")
	@ResponseBody
	@Override
	public JsonResult insertParkingRecord(@RequestBody ParkingRecordEntity entity) {

		JsonResult jsonResult = new JsonResult();
		ParkingLotEntity parkingLotEntity = parkingLotService.selectParkingLotByNum(entity.getParkingNum());
		if (parkingCardService.selectParkingCardByCardNumAndParkingNum(entity.getCardNum(),
				entity.getParkingNum()) == null) {
			jsonResult = new JsonResult(new ServiceException("该停车卡或停车场不存在"));
			return jsonResult;
		} else if (parkingCardService
				.selectParkingCardByCardNumAndParkingNum(entity.getCardNum(), entity.getParkingNum()).getFlag() == 1) {
			jsonResult = new JsonResult(new ServiceException("您已停放辆车，不能停放其他车辆"));
			return jsonResult;
		}else if(parkingCardService.selectParkingCardByCardNumAndParkingNum(entity.getCardNum(), entity.getParkingNum()).getState()==1) {
			jsonResult = new JsonResult(new ServiceException("卡被禁，请缴费激活"));
			return jsonResult;
		} else {
			/** 更新停车场正在使用的车位数量 */
			parkingLotEntity.setInuse(parkingLotEntity.getInuse() + 1);
			jsonResult = parkingLotService.updateParkingLotInuse(parkingLotEntity);

			/** 更新停车卡信息 */
			ParkingCardEntity parkingCardEntity = new ParkingCardEntity();
			parkingCardEntity.setCardNum(entity.getCardNum());
			parkingCardEntity.setParkingNum(entity.getParkingNum());
			parkingCardEntity.setFlag(1);
			parkingCardService.updateParkingCard(parkingCardEntity);

			/** 记录停车的记录 */
			ParkingRecordEntity parkingRecordEntity = new ParkingRecordEntity();
			parkingRecordEntity.setCardNum(entity.getCardNum());
			parkingRecordEntity.setParkingNum(entity.getParkingNum());
			parkingRecordEntity.setCheckinTime(new Date());
			parkingRecordEntity.setFlag(1);
			parkingRecordService.insertParkingRecord(parkingRecordEntity);
			parkingRecordEntity.setFlag(0);
			System.out.println(parkingRecordEntity);
			checkParkingRecord(parkingRecordEntity);
			return jsonResult;
		}
	}

	/**
	 * 判断停车季度，生成新的账单
	 */
	private void checkParkingRecord(ParkingRecordEntity entity) {
		System.out.println(entity);
		ParkingCardEntity parkingCardEntity = parkingCardService
				.selectParkingCardByCardNumAndParkingNum(entity.getCardNum(), entity.getParkingNum());
		System.out.println(parkingCardEntity);
		List<ParkingBillEntity> list = parkingBillService.selectAllParkingBillEntityByCard(parkingCardEntity);
		Date maxDate=list.get(0).getFirstDate();
		for (int i = 0; i < list.size(); i++) {
			if(list.get(i).getStatementDate().compareTo(maxDate)==1) {
				maxDate=list.get(i).getStatementDate();
			}
		}
		System.out.println(maxDate);
		if(maxDate.compareTo(new Date())==-1) {
			UserAndCardEntity en = new UserAndCardEntity();
			en.setCardNum(entity.getCardNum());
			en.setParkingNum(entity.getParkingNum());
			en.setPhone(userService.selectUserInfoById(parkingCardEntity.getUserId()).getPhone());
			System.out.println(en);
			generateBill(en);
		}

	}

	/**
	 * 生成新账单
	 * @param entity
	 */
	public void generateBill(UserAndCardEntity entity) {
		ParkingBillEntity parkingBillEntity = new ParkingBillEntity();
		int rand = new Random().nextInt(100000);
		parkingBillEntity.setBillNum(String.valueOf(rand));
		parkingBillEntity.setAccount(parkingLotService.selectParkingLotByNum(entity.getParkingNum()).getPrice() * 3);
		parkingBillEntity.setPrice(parkingLotService.selectParkingLotByNum(entity.getParkingNum()).getPrice());
		parkingBillEntity.setCardNum(entity.getCardNum());
		Calendar ca = Calendar.getInstance();
		ca.setTime(new Date());
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
		parkingBillEntity.setParkingNum(entity.getParkingNum());
		parkingBillEntity
				.setParkingName(parkingLotService.selectParkingLotByNum(entity.getParkingNum()).getParkingName());
		parkingBillEntity.setPhone(entity.getPhone());
		parkingBillService.insertParkingBill(parkingBillEntity);
	}

}
