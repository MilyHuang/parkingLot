package com.parkinglot.admin.dao;

import java.sql.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.parkinglot.admin.entity.ParkingBillEntity;
import com.parkinglot.admin.entity.ParkingCardEntity;

public interface IParkingBillDao {
      
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
	List<ParkingBillEntity> selectAllParkingBillEntityByCard(Integer cardId);
	
	/**
	 * 通过手机号和缴费状态查询用户的账单
	 * @param phone 电话
	 * @param flag  缴费状态
	 * @return
	 */
	List<ParkingBillEntity> selectBillsByPhoneAndFlag(@Param("phone") String phone,@Param("flag") Integer flag);
	
	
	/**
	 * 查找停车卡的最新账单时间
	 */
	ParkingBillEntity selectAllParkingBillEntityByCardId(@Param("cardId")Integer cardId);
	
	
	/**
	 * 通过flag查找所有的账单
	 */
	List<ParkingBillEntity> selectAllParkingBillEntitys(@Param("flag")Integer flag);
	
	
	/**
	 * 更新账单
	 */
	int updateParkingBill(ParkingBillEntity entity);
	
	/**
	 * 用户缴费
	 * @param billEntity
	 * @return
	 */
	int updateBillInfo(ParkingBillEntity billEntity);
}
