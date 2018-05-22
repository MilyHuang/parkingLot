package com.parkinglot.admin.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.parkinglot.admin.dao.IUserInfoDao;
import com.parkinglot.admin.entity.UsersInfoEntity;
import com.parkinglot.admin.service.IUsersInfoService;
import com.parkinglot.common.service.ServiceException;
import com.parkinglot.common.util.JsonResult;

/**
@Description:
@version:1.0
@author:MilyHuang
@Date:May 22, 201810:57:34 AM
@Email:Mily-ML.Huang@aia.com

*/
@Service("com.parkinglot.admin.service.impl.UsersInfoServiceImpl")
public class UsersInfoServiceImpl implements IUsersInfoService{

	@Autowired
	private IUserInfoDao userDao;
	
	@Override
	public JsonResult insertUserInfo(UsersInfoEntity entity) {
		JsonResult jsonResult = new JsonResult();
		int rows = userDao.insertUserInfo(entity);
		if(rows <=0 ) {
			jsonResult = new JsonResult(new ServiceException("添加新用户失败"));
			return jsonResult;
		}
		return jsonResult;
	}

	@Override
	public UsersInfoEntity selectUserInfoByPhone(String phone) {
		// TODO Auto-generated method stub
		return null;
	}

}
