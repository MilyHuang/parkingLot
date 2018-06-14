package com.parkinglot.admin.service;
/**
@Description:  用户停车取车功能
@version:1.0
@author:MilyHuang
@Date:May 24, 201811:18:08 AM
@Email:Mily-ML.Huang@aia.com

*/

import java.util.List;

import com.parkinglot.admin.entity.ParkingRecordEntity;
import com.parkinglot.admin.entity.UsageOfParkingCard;
import com.parkinglot.common.util.JsonResult;

public interface IParkingRecordService {
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
	 * 通过停车场num获取停车信息
	 * @return
	 */
	ParkingRecordEntity selectParkingRecord(String cardNum);
	
	/**
	 * 查询停车场内是否有车
	 * @return
	 */
	ParkingRecordEntity isHasCarInTheParking(int id);
	/**
	 * 查看停车场使用情况
	 */
	List<UsageOfParkingCard> selectUsageOfParkingCard(UsageOfParkingCard entity);
}
