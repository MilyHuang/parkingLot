package com.parkinglot.admin.service;


import com.parkinglot.admin.entity.UsersInfoEntity;
import com.parkinglot.common.util.JsonResult;

/**
@Description:用户管理
@version:1.0
@author:MilyHuang
@Date:May 22, 201810:41:18 AM
@Email:Mily-ML.Huang@aia.com

*/

public interface IUsersInfoService {
	
	/**
	 * 添加用户（新用户办卡）
	 * @param entity
	 * @return
	 */
	JsonResult insertUserInfo(UsersInfoEntity entity);
	
	/**
	 * 通过手机号查询用户信息
	 * @param phone 电话号码
	 * @return
	 */
	UsersInfoEntity selectUserInfoByPhone(String phone);
	
	/**
	 * 登录
	 * @param entity
	 * @return
	 */
	UsersInfoEntity selectUserInfoByLogin(UsersInfoEntity entity);
	
	/**
	 * 通过id查询用户
	 */
	UsersInfoEntity selectUserInfoById(Integer id);
}
