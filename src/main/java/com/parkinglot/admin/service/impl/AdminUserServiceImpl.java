package com.parkinglot.admin.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.parkinglot.admin.dao.IAdminUserDao;
import com.parkinglot.admin.entity.AdminEntity;
import com.parkinglot.admin.service.IAdminUserService;
import com.parkinglot.common.security.MD5Util;
import com.parkinglot.common.service.ServiceException;

/**
@Description:
@version:1.0
@author:MilyHuang
@Date:May 11, 201810:53:20 AM
@Email:Mily-ML.Huang@aia.com

*/
@Service("com.parkinglot.admin.service.impl.AdminUserServiceImpl")
//@Service
public class AdminUserServiceImpl implements IAdminUserService{

	@Autowired
	private IAdminUserDao adminDao;
	
	
	public void insertAdminUser(AdminEntity entity) {
		entity.setPassword(MD5Util.md5(entity.getPassword())); //瀵瑰瘑鐮佽繘琛屽姞瀵�
		int row = adminDao.insertAdminUser(entity);
		if(row <=0 )
			throw new ServiceException("娣诲姞鐢ㄦ埛澶辫触");
	}
	
	
	public AdminEntity selectUserByLogin(AdminEntity entity) {
		entity.setPassword(MD5Util.md5(entity.getPassword()));
		AdminEntity user = adminDao.selectUserByLogin(entity);
		if(user == null)
			throw new ServiceException("鐧诲綍澶辫触!");
		
		return user;
	}


	@Override
	public AdminEntity selectAdminUser(AdminEntity entity) {
		return null;
	}


	@Override
	public boolean deleteAdminUser(AdminEntity entity) {
		return false;
	}


	@Override
	public AdminEntity updateAdminUser(AdminEntity entity) {
		return null;
	}
	

}
