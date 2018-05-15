package com.parkinglot.admin.service;

import com.parkinglot.admin.entity.AdminEntity;

/**
@Description:
@version:1.0
@author:MilyHuang
@Date:May 11, 201810:50:42 AM
@Email:Mily-ML.Huang@aia.com

*/
public interface IAdminUserService {
	/**
	 * 添加用户
	 * @param entity
	 */
	void insertAdminUser(AdminEntity entity);
	
	/**
	 * 登录
	 * @param entity
	 * @return
	 */
	AdminEntity selectAdminUser(AdminEntity entity);
	
	/**
	 * 
	 */
	boolean deleteAdminUser(AdminEntity entity);
	
	/**
	 * 
	 */
	AdminEntity updateAdminUser(AdminEntity entity);
}
