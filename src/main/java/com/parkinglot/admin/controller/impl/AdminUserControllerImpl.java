package com.parkinglot.admin.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.parkinglot.admin.controller.IAdminUserController;
import com.parkinglot.admin.entity.AdminEntity;
import com.parkinglot.admin.service.IAdminUserService;
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

	@RequestMapping("/insertAdminUser")
	@ResponseBody
	public JsonResult insertAdminUser(AdminEntity entity) {

		adminService.insertAdminUser(entity);
		return new JsonResult("添加成功！");
	}

	@RequestMapping("/selectUserByLogin")
	@ResponseBody
	@Override
	public JsonResult selectAdminUser(AdminEntity entity) {
		return new  JsonResult(adminService.selectUserByLogin(entity));
	}

	@RequestMapping("/deleteAdmin")
	@ResponseBody
	@Override
	public JsonResult deleteAdminUser(AdminEntity entity) {
		return null;
	}

	@RequestMapping("/updateAdmin")
	@ResponseBody
	@Override
	public JsonResult updateAdminUser(AdminEntity entity) {
		return null;
	}

}
