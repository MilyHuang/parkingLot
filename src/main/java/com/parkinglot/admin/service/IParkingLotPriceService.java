package com.parkinglot.admin.service;

import com.parkinglot.admin.entity.ParkingLotPriceEntity;
import com.parkinglot.common.util.JsonResult;

public interface IParkingLotPriceService {
	/**
	 * 修改每個月停车位租金
	 * @param entity
	 * @return
	 */
	JsonResult updatePrice(ParkingLotPriceEntity entity);
}
