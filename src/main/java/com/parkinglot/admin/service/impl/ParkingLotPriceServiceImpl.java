package com.parkinglot.admin.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.parkinglot.admin.dao.IParkingLotPriceDao;
import com.parkinglot.admin.entity.ParkingLotPriceEntity;
import com.parkinglot.admin.service.IParkingLotPriceService;
import com.parkinglot.common.service.ServiceException;
import com.parkinglot.common.util.JsonResult;
@Transactional
@Service("com.parkinglot.admin.service.impl.ParkingLotPriceServiceImpl")
public class ParkingLotPriceServiceImpl implements IParkingLotPriceService {

	@Autowired
	private IParkingLotPriceDao parkingLotpriceDao;
	
	@Override
	public JsonResult updatePrice(ParkingLotPriceEntity entity) {
		JsonResult jsonResult = new JsonResult();
		int rows = parkingLotpriceDao.updatePrice(entity);
		if(rows <= 0) {
			jsonResult = new JsonResult(new ServiceException("修改每月停车位價格失敗"));
			return jsonResult;
		}
		return jsonResult;
	}

}
