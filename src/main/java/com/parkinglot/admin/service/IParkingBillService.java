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
	
	/**
	 * 通过手机号和缴费状态查询账单信息
	 * @param phone
	 * @param flag
	 * @return
	 */
	List<ParkingBillEntity> selectBillsByPhoneAndFlag(String phone,Integer flag);
	
	/**
	 * 查询某停车场未缴，未出账，欠费账单
	 * @param id
	 * @return
	 */
	ParkingBillEntity selectUnPayBill(int id);
	
}
