package com.parkinglot.admin.service;

import java.sql.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

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
<<<<<<< HEAD
	 * 查询某停车场未缴，未出账，欠费账单
	 * @param id
	 * @return
	 */
	ParkingBillEntity selectUnPayBill(int id);

	/* 查找停车卡的最新账单时间
	 */
	ParkingBillEntity selectAllParkingBillEntityByCardId(Integer cardId);
	
	
	/**
	 * 查找所有的账单
	 */
	List<ParkingBillEntity> selectAllParkingBillEntitys(Integer flag);
	
	/**
	 * 更新账单
	 */
	int updateParkingBill(ParkingBillEntity entity);
	
	/** 用户缴费
	 * @param billEntity
	 * @return
	 */
	JsonResult updateBillInfo(ParkingBillEntity billEntity);
	
	
	
	/**
	 * 停車場价格改变后，账单价格跟随改变；
	 */
	JsonResult updateOldBill(ParkingBillEntity billEntity);
	
	/**
	 * 獲取某停車場的所有要修改价格的账单
	 */
	List<ParkingBillEntity> selectAllParkingBillEntityByParkingLot(Integer parkingId);
	
	/**
	 * 查询某张停车卡是否还有逾期缴费的账单
	 * @param cardId
	 * @param flag
	 * @return
	 */
	ParkingBillEntity selectBillByCardIdAndFlag(Integer cardId,Integer flag);
	
}
