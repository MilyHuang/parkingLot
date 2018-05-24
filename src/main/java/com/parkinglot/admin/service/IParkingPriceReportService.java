package com.parkinglot.admin.service;

import java.util.List;

import com.parkinglot.admin.entity.ParkingPriceReportEntity;
import com.parkinglot.common.util.JsonResult;

public interface IParkingPriceReportService {
	/**
	 * 添加报表
	 * @param entity
	 * @return
	 */
	JsonResult insertParkingPriceReport(ParkingPriceReportEntity entity);
	
}
