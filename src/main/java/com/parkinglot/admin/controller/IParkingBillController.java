package com.parkinglot.admin.controller;

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
	
	
	/**
	 * 缴费
	 * @param billEntity
	 * @return
	 */
	JsonResult payBill(ParkingBillEntity billEntity);
	
	/**
	 * 判断停车场是否已经满了
	 * @param billEntity
	 * @return
	 */
	JsonResult isParkingLotFull(ParkingBillEntity billEntity);
	
	/**
	 * 管理员帮用户缴费
	 * @param billEntity
	 * @return
	 */
	JsonResult payBillByOperator(ParkingBillEntity billEntity);
	
}
