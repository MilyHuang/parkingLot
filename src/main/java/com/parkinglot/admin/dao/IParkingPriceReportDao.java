package com.parkinglot.admin.dao;

import java.util.List;

import com.parkinglot.admin.entity.ParkingPriceReportEntity;

public interface IParkingPriceReportDao {
	/**
	 * 添加报表
	 * @param entity
	 * @return
	 */
	int insertParkingPriceReport(ParkingPriceReportEntity entity);
	
	/**
	 * 修改停车场价格
	 * @param entity
	 * @return
	 */
	int updateParkingPrice(ParkingPriceReportEntity entity);
}
