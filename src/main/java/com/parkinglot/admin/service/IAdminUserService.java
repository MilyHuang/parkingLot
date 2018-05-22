package com.parkinglot.admin.service;

import java.util.List;

import com.parkinglot.admin.entity.AdminEntity;
import com.parkinglot.common.util.JsonResult;

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
	JsonResult insertAdminUser(AdminEntity entity);
	
	/**
	 * 登录
	 * @param entity  用户名，密码
	 * @return
	 */
	AdminEntity selectUserByLogin(AdminEntity entity);
	
	/**
	 * 查询内部用户
	 * @return
	 */
	List<AdminEntity> selectAdminForList();
	
	/**
	 * 通过ID查询用户信息
	 * @param id
	 * @return
	 */
	AdminEntity selectAdminUserById(Integer id);
	
	/**
	 * 修改密码
	 * @param id
	 * @return
	 */
	JsonResult updatePasswordById(AdminEntity entity);
	
	/**
	 * 删除用户
	 * @param id
	 * @return
	 */
	JsonResult deleteAdminUsers(Integer id);
	
}

















