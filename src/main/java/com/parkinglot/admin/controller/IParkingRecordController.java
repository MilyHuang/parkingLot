package com.parkinglot.admin.controller;

import com.parkinglot.admin.entity.ParkingRecordEntity;
import com.parkinglot.admin.entity.UsageOfParkingCard;
import com.parkinglot.common.util.JsonResult;

public interface IParkingRecordController {
	/**
	 * 用户取车
	 * @param entity
	 * @return
	 */
	JsonResult updateParkingRecord(ParkingRecordEntity entity);
	
	/**
	 * 停车
	 * @param entity 停车卡号，停车场编号，手机号
	 * @return
	 */
	JsonResult insertParkingRecord(ParkingRecordEntity entity);
	
	/**
	 * 查看停车场使用情况
	 * @param entity
	 * @return
	 */
   JsonResult usageOfParkingCard(UsageOfParkingCard entity);
}
