package com.parkinglot.admin.controller;
/**
@Description:
@version:1.0
@author:MilyHuang
@Date:May 22, 201811:13:10 AM
@Email:Mily-ML.Huang@aia.com

*/


import com.parkinglot.admin.entity.ParkingLotEntity;
import com.parkinglot.common.util.JsonResult;

public interface IParkingLotController {
	/**
	 * 查询停车场列表
	 * @return
	 */
	JsonResult selectParkingLotForList();
	
	/**
	 * 添加停车场
	 * @param entity
	 * @return
	 */
	JsonResult insertParkingLot(ParkingLotEntity entity);
}
