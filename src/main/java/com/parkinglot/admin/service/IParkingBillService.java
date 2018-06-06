package com.parkinglot.admin.service;

import java.sql.Date;
import java.util.List;

import com.parkinglot.admin.entity.ParkingBillEntity;
import com.parkinglot.admin.entity.ParkingCardEntity;
import com.parkinglot.common.util.JsonResult;

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
	 * 查找停车卡的最新账单时间
	 */
	ParkingBillEntity selectAllParkingBillEntityByCardId(Integer cardId);
	
	
	/**
	 * 查找所有的账单
	 */
	List<ParkingBillEntity> selectAllParkingBillEntitys();
	
	/**
	 * 更新账单
	 */
	int updateParkingBill(ParkingBillEntity entity);
}
