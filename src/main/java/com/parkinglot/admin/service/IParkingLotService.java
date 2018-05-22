package com.parkinglot.admin.service;
/**
@Description:停车场管理service层
@version:1.0
@author:MilyHuang
@Date:May 21, 20182:30:51 PM
@Email:Mily-ML.Huang@aia.com

*/

import com.parkinglot.admin.entity.ParkingLotEntity;
import com.parkinglot.common.util.JsonResult;

public interface IParkingLotService {
	/**
	 * 添加停车场
	 * @param entity
	 * @return
	 */
	JsonResult insertParkingLot(ParkingLotEntity entity);
}
