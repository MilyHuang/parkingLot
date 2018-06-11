package com.parkinglot.admin.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.parkinglot.admin.dao.IPayInfoDao;
import com.parkinglot.admin.entity.PayInfoEntity;
import com.parkinglot.admin.service.IPayInfoService;
import com.parkinglot.common.util.JsonResult;

/**
@Description:
@version:1.0
@author:MilyHuang
@Date:Jun 11, 20183:46:23 PM
@Email:Mily-ML.Huang@aia.com

*/
@Service
public class PayInfoServiceImpl implements IPayInfoService{

	@Autowired
	private IPayInfoDao payInfoDao;
	
	@Override
	public JsonResult insertPayInfo(PayInfoEntity entity) {
		JsonResult jsonResult = new JsonResult();
		int rows = payInfoDao.insertPayInfo(entity);
		if(rows <= 0) {
			PayInfoEntity payInfo = new PayInfoEntity();
			payInfo.setPhone(entity.getPhone());
			payInfo.setAccount(entity.getAccount());
			payInfo.setResult(false);
			payInfo.setErrorCode("");
			payInfo.setMessage("");
			payInfo.setReceiveCode("");
			payInfo.getPayNum();
			
		}
		return jsonResult;
	}

	@Override
	public PayInfoEntity selectPayInfoByBillId(Integer billId) {
		return payInfoDao.selectPayInfoByBillId(billId);
	}

	@Override
	public PayInfoEntity selectPayInfoById(Integer id) {
		return payInfoDao.selectPayInfoById(id);
	}

}
