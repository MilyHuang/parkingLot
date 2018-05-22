package com.parkinglot.admin.controller.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.parkinglot.admin.controller.IParkingCardController;
import com.parkinglot.admin.entity.ParkingCardEntity;
import com.parkinglot.admin.entity.UsersInfoEntity;
import com.parkinglot.admin.service.IParkingCardService;
import com.parkinglot.admin.service.IUsersInfoService;
import com.parkinglot.common.service.ServiceException;
import com.parkinglot.common.util.JsonResult;
import com.sun.tools.doclint.Entity;

/**
@Description:
@version:1.0
@author:MilyHuang
@Date:May 22, 20182:36:05 PM
@Email:Mily-ML.Huang@aia.com

*/
@Controller
@RequestMapping("/parkingCard")
public class ParkingCardControllerImpl implements IParkingCardController{
	
	private static Logger logger = Logger.getLogger(ParkingCardControllerImpl.class);
	
	@Autowired
	private IUsersInfoService userService;
	
	@Autowired
	private IParkingCardService cardService;

	@RequestMapping("/createParkingCard")
	@ResponseBody
	@Override
	public JsonResult createNewParkingCard(@RequestBody UsersInfoEntity userEntity,  @RequestBody ParkingCardEntity cardEntity) {
		logger.info(userEntity);
		logger.info(cardEntity);
		JsonResult jsonResult = new JsonResult();
		if(userEntity == null || cardEntity == null) {
			jsonResult = new JsonResult(new ServiceException("输入的信息不能为空"));
			return jsonResult;
		}else {
			UsersInfoEntity user = userService.selectUserInfoByPhone(userEntity.getPhone());
			System.err.println(user);
			if(user != null) {
				jsonResult = new JsonResult(user);
				return jsonResult;
			}else {
				
			}
		}
		return null;
	}

}
