package com.parkinglot.admin.controller;

import java.util.List;

import com.parkinglot.admin.entity.ParkingBillEntity;
import com.parkinglot.admin.entity.UsersInfoEntity;
import com.parkinglot.common.util.JsonResult;

public interface IParkingBillController {
	/**
	 * 通過電話查詢所有賬單
	 * @param entity
	 * @return
	 */
	JsonResult selectAllParkingBillEntity(UsersInfoEntity user);
	
	/**
	 * 通過billnum查詢賬單
	 * @param billNum
	 * @return
	 */
	JsonResult selectParkingBillByBillNum(ParkingBillEntity entity);
}
