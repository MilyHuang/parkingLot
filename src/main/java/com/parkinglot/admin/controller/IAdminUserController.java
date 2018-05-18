package com.parkinglot.admin.controller;
/**
@Description:
@version:1.0
@author:MilyHuang
@Date:May 11, 20182:29:05 PM
@Email:Mily-ML.Huang@aia.com

*/

import java.util.List;

import com.parkinglot.admin.dao.Datas;
import com.parkinglot.admin.entity.AdminEntity;
import com.parkinglot.common.util.JsonResult;

public interface IAdminUserController {
	/**
	 * 添加用户账号
	 * @param entity
	 * @return
	 */
	JsonResult insertAdminUser(AdminEntity entity);
	
	/**
	 * 内部用户登录
	 * @param entity
	 * @return
	 */
	JsonResult selectAdminUser(AdminEntity entity);
	
	/**
	 * 查询内部用户的列表
	 * @return
	 */
	JsonResult selectAdminForList();
	
	/**
	 * 通过ID查询用户信息
	 */
	JsonResult selectAdminUserById(Integer id);
	
	/**
	 * 修改密码
	 * @param entity
	 * @return
	 */
	JsonResult updatePasswordById(AdminEntity entity);
	
	/**
	 * 删除用户
	 * @param datas
	 * @return
	 */
	JsonResult deleteAdminUser(Datas datas);
	
}
