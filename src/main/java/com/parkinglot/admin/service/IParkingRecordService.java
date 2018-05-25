package com.parkinglot.admin.service;
/**
@Description:  用户停车取车功能
@version:1.0
@author:MilyHuang
@Date:May 24, 201811:18:08 AM
@Email:Mily-ML.Huang@aia.com

*/

import com.parkinglot.admin.entity.ParkingRecordEntity;
import com.parkinglot.common.util.JsonResult;

public interface IParkingRecordService {
	/**
	 * 用户取车
	 * @param entity
	 * @return
	 */
	JsonResult updateParkingRecord(ParkingRecordEntity entity);
}
