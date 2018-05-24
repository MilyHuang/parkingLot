package com.parkinglot.admin.controller;
/**
@Description:用户管理controller
@version:1.0
@author:MilyHuang
@Date:May 24, 20182:23:10 PM
@Email:Mily-ML.Huang@aia.com

*/

import com.parkinglot.admin.entity.UsersInfoEntity;
import com.parkinglot.common.util.JsonResult;

public interface IUsersInfoController {
	/**
	 * 用户登录
	 * @param entity
	 * @return
	 */
	JsonResult selectUserInfoByLogin(UsersInfoEntity entity);
}
