package com.parkinglot.admin.controller.impl;

import java.util.PrimitiveIterator.OfDouble;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.parkinglot.admin.controller.IParkingCardController;
import com.parkinglot.admin.entity.ParkingCardEntity;
import com.parkinglot.admin.entity.ParkingLotEntity;
import com.parkinglot.admin.entity.UserAndCardEntity;
import com.parkinglot.admin.entity.UsersInfoEntity;
import com.parkinglot.admin.service.IParkingCardService;
import com.parkinglot.admin.service.IParkingLotService;
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

	@Autowired
	private IParkingLotService parkingService;
	
	
	/**
	 * 新用户办理停车卡
	 */
	@RequestMapping(value = "/createNewParkingCard" ,method = RequestMethod.POST)
	@ResponseBody
	@Override
	public JsonResult createNewParkingCard(@RequestBody UserAndCardEntity entity) {
		UsersInfoEntity userEntity = new UsersInfoEntity();
		userEntity.setPhone(entity.getPhone());
		userEntity.setUsername(entity.getUsername());
		userEntity.setPassword(entity.getPassword());
		ParkingCardEntity cardEntity = new ParkingCardEntity();
		cardEntity.setParkingNum(entity.getParkingNum());
		cardEntity.setCardNum(entity.getCardNum());
		JsonResult jsonResult = new JsonResult();
		//判断输入的信息是否为null
		if(userEntity == null || cardEntity == null) {
			jsonResult = new JsonResult(new ServiceException("输入的信息不能为空"));
			return jsonResult;
		}else {
			//查询该用户是否存在，如果存在则反显用户信息
				UsersInfoEntity user = userService.selectUserInfoByPhone(userEntity.getPhone());
				//查询停车卡号是否存在
				ParkingCardEntity card = cardService.selectParkingCardByCardNum(cardEntity.getCardNum(), cardEntity.getParkingNum());
				//如果该用户存在
				if(user != null) {
					jsonResult = new JsonResult(user);
					return jsonResult;
				}else if(card != null) {
					jsonResult = new JsonResult(new ServiceException("该卡号已存在"));
					return jsonResult;
				}else if(isHasParkingLot(cardEntity.getCardNum())) {
					jsonResult = new JsonResult(new ServiceException("不存在该编号的停车场"));
					return jsonResult;
				}else {
					//注册新用户
					userService.insertUserInfo(userEntity);
					cardEntity.setUserId(userEntity.getId());
					//添加停车卡
					cardService.insertParkingCard(cardEntity);
					return jsonResult;
				}
		}
	}

	/**
	 * 老用户办卡
	 */
	@RequestMapping("/createNewParkingByOldUser")
	@ResponseBody
	@Override
	public JsonResult createNewParkingCardByOldUser(@RequestBody ParkingCardEntity cardEntity) {
		JsonResult jsonResult = new JsonResult();
		//判断输入的信息是否为空
		if(cardEntity == null) {
			jsonResult = new JsonResult(new ServiceException("输入的信息不能为空"));
			return jsonResult;
		}
		//查询停车卡号是否存在
		ParkingCardEntity card = cardService.selectParkingCardByCardNum(cardEntity.getCardNum(), cardEntity.getParkingNum());
		if(card != null) {
			jsonResult = new JsonResult(new ServiceException("该卡号已存在"));
			return jsonResult;
		}else if(isHasParkingLot(cardEntity.getCardNum())) {
			jsonResult = new JsonResult(new ServiceException("不存在该编号的停车场"));
			return jsonResult;
		}else {
			//添加停车卡
			cardService.insertParkingCard(cardEntity);
			return jsonResult;
		}
		}

	@RequestMapping("/selectUserInfoByPhone")
	@ResponseBody
	@Override
	public JsonResult selectUserByPhone(@RequestBody UsersInfoEntity userEntity) {
		System.out.println(userEntity.getPhone());
		JsonResult jsonResult = new JsonResult();
		if(userEntity.getPhone() == null) {
			jsonResult = new JsonResult(new ServiceException("查询的手机号不能为空"));
			return jsonResult;
		}
		UsersInfoEntity user = userService.selectUserInfoByPhone(userEntity.getPhone());
		System.out.println(user);
		//查询不到该用户
		if(user == null) {
			jsonResult = new JsonResult(new ServiceException("该用户不存在"));
			return jsonResult;
		}else {
			jsonResult = new JsonResult(user);
			return jsonResult;
		}
	}
	
	/***
	 * 判断该停车场是否存在
	 * @param parkingNum  停车场编号
	 * @return  不存在则返回false ，存在则返回true
	 */
	private boolean isHasParkingLot(String parkingNum) {
		boolean flag = false;
		ParkingLotEntity parking = parkingService.selectParkingLotByNum(parkingNum);
		//如果为Null,则不存在该编号的停车场
		if(parking!=null) {
			flag = true;
			return flag;
		}
		return flag; 
	}
	

}
