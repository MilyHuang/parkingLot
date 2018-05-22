package com.parkinglot.admin.controller.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.parkinglot.admin.controller.IParkingLotController;
import com.parkinglot.admin.entity.ParkingLotEntity;
import com.parkinglot.admin.service.IParkingLotService;
import com.parkinglot.common.service.ServiceException;
import com.parkinglot.common.util.JsonResult;

/**
@Description:
@version:1.0
@author:MilyHuang
@Date:May 22, 201811:16:20 AM
@Email:Mily-ML.Huang@aia.com

*/
@Controller
@RequestMapping("/parkinglot")
public class ParkingLotControllerImpl implements IParkingLotController{
	
	@Autowired
	private IParkingLotService parkingLotService;
	
	private static Logger logger = Logger.getLogger(ParkingLotControllerImpl.class);
	
	@RequestMapping("/selectParkinglot")
	@ResponseBody
	@Override
	public JsonResult selectParkingLotForList() {
		List<ParkingLotEntity> parkings = parkingLotService.selectParkingLotForList();
		logger.info(parkings);
		return new JsonResult(parkings);
	}

	@RequestMapping("/insertParkinglot")
	@ResponseBody
	@Override
	public JsonResult insertParkingLot(@RequestBody ParkingLotEntity entity) {
		JsonResult jsonResult = new JsonResult();
		if(entity == null) {
			jsonResult = new JsonResult(new ServiceException("输入的停车场信息不能为空"));
			return jsonResult;
		}else {
			ParkingLotEntity parkingLot = parkingLotService.selectParkingLotByNum(entity.getParkingNum());
			if(parkingLot != null) {
				jsonResult = new JsonResult(new ServiceException("该停车场编号已存在"));
				return jsonResult;
			}
			jsonResult = parkingLotService.insertParkingLot(entity);
			return jsonResult;
		}
	}

}
