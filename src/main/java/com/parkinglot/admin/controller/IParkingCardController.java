package com.parkinglot.admin.controller;
/**
@Description:
@version:1.0
@author:MilyHuang
@Date:May 22, 20182:33:27 PM
@Email:Mily-ML.Huang@aia.com

*/

import com.parkinglot.admin.entity.ParkingCardEntity;
import com.parkinglot.admin.entity.UsersInfoEntity;
import com.parkinglot.common.util.JsonResult;

public interface IParkingCardController {
	/**
	 * 办理停车卡
	 * @param userEntity  用户信息
	 * @param cardEntity 停车卡信息
	 * @return
	 */
	JsonResult createNewParkingCard(UsersInfoEntity userEntity,ParkingCardEntity cardEntity);
}
