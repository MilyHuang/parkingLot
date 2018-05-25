package com.parkinglot.admin.dao;

import com.parkinglot.admin.entity.ParkingRecordEntity;

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
}
