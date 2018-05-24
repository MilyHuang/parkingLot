package com.parkinglot.admin.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.parkinglot.admin.dao.IAdminUserDao;
import com.parkinglot.admin.entity.AdminEntity;
import com.parkinglot.admin.service.IAdminUserService;
import com.parkinglot.common.security.MD5Util;
import com.parkinglot.common.service.ServiceException;
import com.parkinglot.common.util.JsonResult;

/**
@Description:
@version:1.0
@author:MilyHuang
@Date:May 11, 201810:53:20 AM
@Email:Mily-ML.Huang@aia.com

*/
//@Transactional
//@Service("com.parkinglot.admin.service.impl.AdminUserServiceImpl")
@Service
public class AdminUserServiceImpl implements IAdminUserService{

	 private static Logger logger = Logger.getLogger(AdminUserServiceImpl.class);
	
	@Autowired
	private IAdminUserDao adminDao;
	
	public JsonResult insertAdminUser(AdminEntity entity) {
		JsonResult jsonResult = new JsonResult();
		//查询输入的用户名是否已存在
		AdminEntity isHas = adminDao.selectUserByUsrename(entity.getUsername());
		if(isHas != null) {
			jsonResult = new JsonResult(new ServiceException("该用户名已存在！"));
			return jsonResult;
		}else {
			entity.setPassword(MD5Util.md5(entity.getPassword())); //MD5加密
			int row = adminDao.insertAdminUser(entity);
			if(row <=0 ) {
				jsonResult  = new JsonResult(new ServiceException("添加用户失败！"));
				return jsonResult;
			}
			return jsonResult;
		}
	}
	
	
	public AdminEntity selectUserByLogin(AdminEntity entity) {
		entity.setPassword(MD5Util.md5(entity.getPassword()));
		AdminEntity user = adminDao.selectUserByLogin(entity);
		return user;
	}


	@Override
	public List<AdminEntity> selectAdminForList() {
		List<AdminEntity> users = adminDao.selectAdminForList();
		return users;
	}


	@Override
	public AdminEntity selectAdminUserById(Integer id) {
		AdminEntity user = adminDao.selectAdminUserById(id);
		return user;
	}

	@Override
	public JsonResult updatePasswordById(AdminEntity entity) {
		JsonResult jsonResult = new JsonResult();
		entity.setPassword(MD5Util.md5(entity.getPassword()));
		int row = adminDao.updatePasswordById(entity);
		if(row <= 0) {
			logger.info("修改密码失败");
			jsonResult = new JsonResult(new ServiceException("修改密码失败！"));
			return jsonResult;
		}
		return jsonResult;
	}


	@Override
	public JsonResult deleteAdminUsers(Integer id) {
		JsonResult jsonResult = new JsonResult();
		int rows = adminDao.deleteAdminUsers(id);
		if(rows <=0 ) {
			jsonResult = new JsonResult(new ServiceException("删除用户失败"));
			return jsonResult;
		}
		return jsonResult;
	}


	

}
