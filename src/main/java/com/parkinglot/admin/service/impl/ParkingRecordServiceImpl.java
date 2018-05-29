package com.parkinglot.admin.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.parkinglot.admin.dao.IParkingRecordDao;
import com.parkinglot.admin.entity.ParkingRecordEntity;
import com.parkinglot.admin.service.IParkingRecordService;
import com.parkinglot.common.service.ServiceException;
import com.parkinglot.common.util.JsonResult;

/**
@Description:
@version:1.0
@author:MilyHuang
@Date:May 24, 201811:18:32 AM
@Email:Mily-ML.Huang@aia.com

*/
@Service("com.parkinglot.admin.service.impl.ParkingRecordServiceImpl")
public class ParkingRecordServiceImpl implements IParkingRecordService{

	@Autowired
	private IParkingRecordDao recordDao;
	
	@Override
	public JsonResult updateParkingRecord(ParkingRecordEntity entity) {
		JsonResult jsonResult = new JsonResult();
		int rows  = recordDao.updateParkingRecord(entity);
		if(rows <= 0) {
			jsonResult = new JsonResult(new ServiceException("取车失败"));
			return jsonResult;
		}
		return jsonResult;
	}

	@Override
	public JsonResult insertParkingRecord(ParkingRecordEntity entity) {
		JsonResult jsonResult = new JsonResult();
		int rows  = recordDao.insertParkingRecord(entity);
		if(rows <= 0) {
			jsonResult = new JsonResult(new ServiceException("停车失败"));
			return jsonResult;
		}
		return jsonResult;
	}
	
	

}
