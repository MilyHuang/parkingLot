package com.parkinglot.admin.controller.impl;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.parkinglot.admin.controller.IAdminUserController;
import com.parkinglot.admin.dao.Datas;
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

	private static Logger logger = Logger.getLogger(AdminUserControllerImpl.class);
	
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

	@RequestMapping(value="/selectAdminInfoById" ,method = RequestMethod.POST)
	@ResponseBody
	@Override
	public JsonResult selectAdminUserById(@RequestBody Integer id) {
		System.err.println(id);
		JsonResult jsonResult = new JsonResult();
		if(id == null || "".equals(id)) {
			jsonResult = new JsonResult(new ServiceException("查询的ID不能为空"));
			return jsonResult;
		}
		AdminEntity user = adminService.selectAdminUserById(id);
		if(user == null) {
			jsonResult = new JsonResult(new ServiceException("该用户不存在或已删除"));
			return jsonResult;
		}else {
			jsonResult = new JsonResult(user);
			return jsonResult;
		}
		
	}

	@RequestMapping(value="/updatePasswordById",method = RequestMethod.POST)
	@ResponseBody
	@Override
	public JsonResult updatePasswordById(@RequestBody AdminEntity entity) {
		logger.info(entity);
		JsonResult jsonResult = new JsonResult();
		if(entity == null || entity.getPassword() == null || "".equals(entity.getPassword())) {
			jsonResult = new JsonResult(new ServiceException("修改的密码不能为空"));
			return jsonResult;
		}
		AdminEntity user = adminService.selectAdminUserById(entity.getId());
		if(user == null) {
			jsonResult = new JsonResult(new ServiceException("该用户不存在或已删除"));
			return jsonResult;
		}
		jsonResult = adminService.updatePasswordById(entity);
		return jsonResult;
	}

	@RequestMapping(value="/deleteAdminUser",method = RequestMethod.POST)
	@ResponseBody
	@Override
	public JsonResult deleteAdminUser(@RequestBody Datas datas) {
		// {"datas":[2]}
		logger.info(datas);
		System.err.println(datas);
		return new JsonResult("delete");
	}

	/*@RequestMapping(value="/deleteAdminUser",method = RequestMethod.POST)
	@ResponseBody
	
	public JsonResult deleteAdminUserTest(@RequestParam(name="datas") Integer[] datas, HttpServletRequest request) {
		// {"datas":[2]}
		System.err.println(request);
		System.err.println(request.getParameterMap());
		System.err.println(datas);
		//logger.info(Arrays.toString(datas));
		//logger.info(datas);
		//System.err.println(datas);
		return new JsonResult("delete");
	}*/
	
	
	
}
