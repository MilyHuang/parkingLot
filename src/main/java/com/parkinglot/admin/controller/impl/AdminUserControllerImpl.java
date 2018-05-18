package com.parkinglot.admin.controller.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.parkinglot.admin.controller.IAdminUserController;
import com.parkinglot.admin.entity.AdminEntity;
import com.parkinglot.admin.service.IAdminUserService;
import com.parkinglot.common.service.ServiceException;
import com.parkinglot.common.util.JsonResult;

/**
 * @Description:
 * @version:1.0
 * @author:MilyHuang
 * @Date:May 11, 20182:31:40 PM
 * @Email:Mily-ML.Huang@aia.com
 * 
 */
@Controller
@RequestMapping("/admin")
public class AdminUserControllerImpl implements IAdminUserController {

	@Autowired
	private IAdminUserService adminService;

	@RequestMapping(value = "/insertAdminUser" ,method = RequestMethod.POST)
	@ResponseBody
	public JsonResult insertAdminUser(@RequestBody AdminEntity entity) {
		JsonResult jsonResult = new JsonResult();
		if(entity == null)
			jsonResult = new JsonResult(new ServiceException("输入的用户信息不能为空！"));
		jsonResult = adminService.insertAdminUser(entity);
		return jsonResult;
	}

	@RequestMapping(value = "/selectUserByLogin",method = RequestMethod.POST)
	@ResponseBody
	@Override
	public JsonResult selectAdminUser(@RequestBody AdminEntity entity) {
		JsonResult jsonResult = new JsonResult();
		if(entity == null) {
			jsonResult = new JsonResult(new ServiceException("用户名或密码不能为空！"));
			return jsonResult;
		}
		//登录
		AdminEntity user = adminService.selectUserByLogin(entity);
		if(user != null) {
			jsonResult = new JsonResult(user);
			return jsonResult;
		}else {
			jsonResult = new JsonResult(new ServiceException("用户名或密码错误！"));
			return jsonResult;
		}
	}

	@RequestMapping(value = "/selectUserForList",method = RequestMethod.POST)
	@ResponseBody
	@Override
	public JsonResult selectAdminForList() {
		List<AdminEntity> users = adminService.selectAdminForList();
		JsonResult jsonResult = new JsonResult(users);
		return jsonResult;
	}


	
	
}
