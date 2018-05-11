package com.parkinglot.admin.controller;
/**
@Description:
@version:1.0
@author:MilyHuang
@Date:May 11, 20182:29:05 PM
@Email:Mily-ML.Huang@aia.com

*/

import com.parkinglot.admin.entity.AdminEntity;
import com.parkinglot.common.util.JsonResult;

public interface IAdminUserController {
	/**
	 * 添加用户账号
	 * @param entity
	 * @return
	 */
	JsonResult insertAdminUser(AdminEntity entity);
}
