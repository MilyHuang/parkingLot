package com.parkinglot.admin.service;
/**
@Description: 停车卡管理
@version:1.0
@author:MilyHuang
@Date:May 22, 201811:48:43 AM
@Email:Mily-ML.Huang@aia.com

*/

import com.parkinglot.admin.entity.ParkingCardEntity;
import com.parkinglot.common.util.JsonResult;

public interface IParkingCardService {
	/**
	 * 办理停车卡
	 * @param entity
	 * @return
	 */
	JsonResult insertParkingCard(ParkingCardEntity entity);
	
	/**
	 * 查询停车卡是否存在
	 * @param cardNum 停车卡号
	 * @return
	 */
	ParkingCardEntity selectParkingCardByCardNum(String cardNum);
}
