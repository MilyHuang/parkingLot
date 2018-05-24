package com.parkinglot.admin.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.parkinglot.admin.dao.IParkingPriceReportDao;
import com.parkinglot.admin.entity.ParkingPriceReportEntity;
import com.parkinglot.admin.service.IParkingPriceReportService;
import com.parkinglot.common.service.ServiceException;
import com.parkinglot.common.util.JsonResult;

@Transactional
@Service("com.parkinglot.admin.service.impl.ParkingPriceReportServiceImpl")
public class ParkingPriceReportServiceImpl implements IParkingPriceReportService {

	@Autowired
	private IParkingPriceReportDao parkingPriceReportDao;
	
	@Override
	public JsonResult insertParkingPriceReport(ParkingPriceReportEntity entity) {
		JsonResult jsonResult = new JsonResult();
		//办理停车卡
		int rows = parkingPriceReportDao.insertParkingPriceReport(entity);
		if(rows <=0 ) {
			jsonResult = new JsonResult(new ServiceException("添加报表失败"));
			return jsonResult;
		}
		return jsonResult;
	}


}
