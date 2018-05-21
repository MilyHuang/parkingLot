package com.parkinglot.admin.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.parkinglot.admin.dao.IParkingLotDao;
import com.parkinglot.admin.entity.ParkingLotEntity;
import com.parkinglot.admin.service.IParkingLotService;
import com.parkinglot.common.service.ServiceException;
import com.parkinglot.common.util.JsonResult;

/**
@Description:
@version:1.0
@author:MilyHuang
@Date:May 21, 20182:32:19 PM
@Email:Mily-ML.Huang@aia.com

*/
@Service("com.parkinglot.admin.service.impl.ParkingLotServiceImpl")
public class ParkingLotServiceImpl implements IParkingLotService{

	@Autowired
	private IParkingLotDao parkingLotDao;
	
	@Override
	public JsonResult insertParkingLot(ParkingLotEntity entity) {
		JsonResult jsonResult = new JsonResult();
		int rows = parkingLotDao.insertParkingLot(entity);
		if(rows <= 0) {
			jsonResult = new JsonResult(new ServiceException("添加停车场失败"));
			return jsonResult;
		}
		return jsonResult;
	}

}
