package com.parkinglot.admin.dao;

import java.util.List;

import com.parkinglot.admin.entity.ParkingLotEntity;

/**
@Description:停车场管理
@version:1.0
@author:MilyHuang
@Date:May 21, 20182:04:57 PM
@Email:Mily-ML.Huang@aia.com

*/
public interface IParkingLotDao {
	/**
	 * 添加停车场
	 * @param entity
	 * @return
	 */
	int insertParkingLot(ParkingLotEntity entity);
	
	/**
	 * 查询停车场列表
	 * @return 停车场列表集合
	 */
	List<ParkingLotEntity> selectParkingLotForList();
	
}
