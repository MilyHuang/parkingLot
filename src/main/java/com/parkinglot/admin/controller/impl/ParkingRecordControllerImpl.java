package com.parkinglot.admin.controller.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.parkinglot.admin.controller.IParkingRecordController;
import com.parkinglot.admin.entity.ParkingCardEntity;
import com.parkinglot.admin.entity.ParkingLotEntity;
import com.parkinglot.admin.entity.ParkingRecordEntity;
import com.parkinglot.admin.service.IParkingCardService;
import com.parkinglot.admin.service.IParkingLotService;
import com.parkinglot.admin.service.IParkingRecordService;
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
		}else {
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
			return jsonResult;
		}
	}

}
