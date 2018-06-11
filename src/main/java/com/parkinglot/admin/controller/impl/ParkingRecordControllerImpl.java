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
import com.parkinglot.admin.entity.UsersInfoEntity;
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
		ParkingCardEntity parkingCardEntity = parkingCardService.selectParkingCardByCardNum(entity.getCardNum(),0);
		ParkingRecordEntity parkingRecordEntity = parkingRecordService.selectParkingRecord(entity.getCardNum());
		UsersInfoEntity user = userService.selectUserInfoById(parkingCardEntity.getUserId());
		if (parkingLotEntity == null) {
			jsonResult = new JsonResult(new ServiceException("该停车场不存在"));
			return jsonResult;
		}else if (parkingCardEntity == null) {
			jsonResult = new JsonResult(new ServiceException("该卡不存在或已停用"));
			return jsonResult;
		} else if (parkingCardEntity.getParkingId() != parkingLotEntity.getId()) {
			jsonResult = new JsonResult(new ServiceException("该停车卡不能在此取车"));
			return jsonResult;
		} else if (parkingLotEntity.getInuse() == 0) {
			jsonResult = new JsonResult(new ServiceException("停车场内没有车辆"));
			return jsonResult;
		} else {
			 /**自动停卡*/
		    parkingRecordService.checkCard(entity);
			/** 更新停车场正在使用的车位数量 */
			parkingLotEntity.setInuse(parkingLotEntity.getInuse() - 1);
			jsonResult = parkingLotService.updateParkingLotInuse(parkingLotEntity);

			/** 记录停车的记录 */
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
		ParkingCardEntity parkingCardEntity = parkingCardService.selectParkingCardByCardNum(entity.getCardNum(),0); //state 0 可用，1停用
		if (parkingLotEntity == null) {
			jsonResult = new JsonResult(new ServiceException("该停车场不存在"));
			return jsonResult;
		} else if (parkingCardEntity == null) {
			jsonResult = new JsonResult(new ServiceException("该卡不存在或已停用"));
			return jsonResult;
		}else if (parkingCardEntity.getParkingId() != parkingLotEntity.getId()) {
			jsonResult = new JsonResult(new ServiceException("该停车卡不能在此停车"));
			return jsonResult;
		}  else {
			UsersInfoEntity user = userService.selectUserInfoById(parkingCardEntity.getUserId());
			ParkingRecordEntity recordEntity = parkingRecordService.selectParkingRecord(entity.getCardNum());
			if (recordEntity != null) {
				jsonResult = new JsonResult(new ServiceException("您已停放辆车，不能停放其他车辆"));
				return jsonResult;
			}
			    /**自动停卡*/
			    parkingRecordService.checkCard(entity);
			    
				/** 更新停车场正在使用的车位数量 */
				parkingLotEntity.setInuse(parkingLotEntity.getInuse() + 1);
				jsonResult = parkingLotService.updateParkingLotInuse(parkingLotEntity);

				/** 记录停车的记录 */
				ParkingRecordEntity parkingRecordEntity = new ParkingRecordEntity();
				parkingRecordEntity.setCardId(parkingCardEntity.getId());
				parkingRecordEntity.setParkingId(parkingLotEntity.getId());
				parkingRecordEntity.setParkingNum(entity.getParkingNum());
				parkingRecordEntity.setCardNum(entity.getCardNum());
				parkingRecordEntity.setCheckinTime(new Date());
				parkingRecordEntity.setFlag(1);
				parkingRecordEntity.setPhone(user.getPhone());
				parkingRecordService.insertParkingRecord(parkingRecordEntity);
				parkingRecordEntity.setFlag(0);
				return jsonResult;
			
		}
	}

	

	
}
