package com.parkinglot.admin.dao;

import org.apache.ibatis.annotations.Param;

import com.parkinglot.admin.entity.UsersInfoEntity;

/**
@Description:  用户信息管理
@version:1.0
@author:MilyHuang
@Date:May 22, 201810:05:42 AM
@Email:Mily-ML.Huang@aia.com

*/
public interface IUserInfoDao {
	/**
	 * 添加用户信息（首次办卡）
	 * @param entity
	 * @return
	 */
	int insertUserInfo(UsersInfoEntity entity);
	
	/**
	 * 通过手机号码查询用户信息
	 * @param phone  手机号码
	 * @return
	 */
	UsersInfoEntity selectUserInfoByPhone(@Param("phone") String phone);
}
