package com.parkinglot.admin.service;
/**
@Description:停车场管理service层
@version:1.0
@author:MilyHuang
@Date:May 21, 20182:30:51 PM
@Email:Mily-ML.Huang@aia.com

*/

import java.util.List;

import com.parkinglot.admin.entity.ParkingLotEntity;
import com.parkinglot.common.util.JsonResult;

public interface IParkingLotService {
	/**
	 * 添加停车场
	 * @param entity
	 * @return
	 */
	JsonResult insertParkingLot(ParkingLotEntity entity);
	
	/**
	 * 查询停车场列表
	 * @return
	 */
	List<ParkingLotEntity> selectParkingLotForList();
	
	/**
	 * 通过停车场编号查询停车场信息
	 * @param parkingNum
	 * @return
	 */
	ParkingLotEntity selectParkingLotByNum(String parkingNum);
	
	/**
	 * 更新停车场价格
	 * @param entity
	 * @return
	 */
	JsonResult updateParkingLotPrice(ParkingLotEntity entity);	
}
