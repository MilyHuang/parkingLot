package com.parkinglot.admin.service;

import java.util.List;

import com.parkinglot.admin.entity.ParkingBillEntity;
import com.parkinglot.admin.entity.ParkingCardEntity;

public interface IParkingBillService {
	/**
	 * 通過電話查詢所有賬單
	 * @param entity
	 * @return
	 */
	List<ParkingBillEntity> selectAllParkingBillEntity(String  phone);
	
	/**
	 * 通過billnum查詢賬單
	 * @param billNum
	 * @return
	 */
	ParkingBillEntity selectParkingBillByBillNum(String billNum);
	
	/**
	 * 生成賬單
	 */
	int insertParkingBill(ParkingBillEntity entity);
	
	/**
	 * 查找每张停车卡的账单
	 */
	List<ParkingBillEntity> selectAllParkingBillEntityByCard(ParkingCardEntity  entity);
	
}
