package com.parkinglot.admin.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.parkinglot.admin.dao.IPayInfoDao;
import com.parkinglot.admin.entity.PayInfoEntity;
import com.parkinglot.admin.service.IPayInfoService;
import com.parkinglot.common.service.ServiceException;
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
		if(rows <=0 ) {
			return new JsonResult(new ServiceException("返回支付信息失败"));
		}
		return jsonResult;
	}

	@Override
	public PayInfoEntity selectPayInfoByBillId(Integer billId,Boolean result) {
		return payInfoDao.selectPayInfoByBillId(billId,result);
	}

	@Override
	public PayInfoEntity selectPayInfoById(Integer id) {
		return payInfoDao.selectPayInfoById(id);
	}

}
