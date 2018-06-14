package com.parkinglot.admin.dao;

import java.util.List;

import com.parkinglot.admin.entity.ParkingRecordEntity;
import com.parkinglot.admin.entity.UsageOfParkingCard;

/**
@Description:  用户停车取车dao类
@version:1.0
@author:MilyHuang
@Date:May 24, 201811:01:26 AM
@Email:Mily-ML.Huang@aia.com

*/
public interface IParkingRecordDao {
	/**
	 * 取车
	 * @param entity 停车卡号，停车场编号，手机号
	 * @return
	 */
	int updateParkingRecord(ParkingRecordEntity entity);
	
	/**
	 * 停车
	 * @param entity 停车卡号，停车场编号，手机号
	 * @return
	 */
	int insertParkingRecord(ParkingRecordEntity entity);
	
	/**
	 * 通过停车场编号获取停车信息
	 * @return
	 */
	ParkingRecordEntity selectParkingRecord(Integer cardId);
	
	/**
	 * 查询停车场内是否有车
	 * @return
	 */
	ParkingRecordEntity isHasCarInTheParking(Integer id);
	
	
	/**
	 * 查看停车场使用情况
	 */
	List<UsageOfParkingCard> selectUsageOfParkingCard(UsageOfParkingCard entity);
}
