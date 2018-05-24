package com.parkinglot.admin.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.parkinglot.admin.controller.IUsersInfoController;
import com.parkinglot.admin.entity.UsersInfoEntity;
import com.parkinglot.admin.service.IUsersInfoService;
import com.parkinglot.common.service.ServiceException;
import com.parkinglot.common.util.JsonResult;

/**
@Description:
@version:1.0
@author:MilyHuang
@Date:May 24, 20182:27:09 PM
@Email:Mily-ML.Huang@aia.com

*/
@RequestMapping("/userinfo")
@Controller
public class UsersInfoControllerImpl implements IUsersInfoController{

	@Autowired
	private IUsersInfoService userService;
	
	@RequestMapping(value = "/login",method = RequestMethod.POST)
	@ResponseBody
	@Override
	public JsonResult selectUserInfoByLogin(@RequestBody UsersInfoEntity entity) {
		System.out.println(entity);
		JsonResult jsonResult = new JsonResult();
		if(entity == null) {
			jsonResult = new JsonResult(new ServiceException("输入的登录信息不能为空"));
			return jsonResult;
		}
		//登录
		UsersInfoEntity user = userService.selectUserInfoByLogin(entity);
		//如果查询的结果为null
		if(user == null) {
			jsonResult = new JsonResult(new ServiceException("用户名或密码错误"));
			return jsonResult;
		}
		return new JsonResult(user);
	}

}
