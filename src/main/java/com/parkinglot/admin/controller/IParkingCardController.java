package com.parkinglot.admin.controller;
/**
@Description:
@version:1.0
@author:MilyHuang
@Date:May 22, 20182:33:27 PM
@Email:Mily-ML.Huang@aia.com

*/

import com.parkinglot.admin.entity.ParkingCardEntity;
import com.parkinglot.admin.entity.UserAndCardEntity;
import com.parkinglot.admin.entity.UsersInfoEntity;
import com.parkinglot.common.util.JsonResult;

public interface IParkingCardController {
	/**
	 * 新用户办理停车卡
	 * @param userEntity  用户信息
	 * @param cardEntity 停车卡信息
	 * @return
	 */
	JsonResult createNewParkingCard(UserAndCardEntity entity);
	
	/**
	 * 老用户办卡
	 * @param cardEntity  停车卡信息
	 * @return
	 */
	JsonResult createNewParkingCardByOldUser(UserAndCardEntity cardEntity);
	
	/**
	 * 通过手机号码查询用户信息
	 * @param phone 手机号
	 * @return
	 */
	JsonResult selectUserByPhone(UsersInfoEntity userEntity);
	
	/**
	 * 查询用户的卡信息
	 * @param entity  电话号码
	 * @return
	 */
	JsonResult selectUserCardsList(UsersInfoEntity entity);
	
}
