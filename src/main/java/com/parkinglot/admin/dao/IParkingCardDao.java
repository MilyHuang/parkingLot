package com.parkinglot.admin.dao;

import org.apache.ibatis.annotations.Param;

import com.parkinglot.admin.entity.ParkingCardEntity;
import com.parkinglot.common.util.JsonResult;

/**
@Description: 添加停车卡（办理停车卡）
@version:1.0
@author:MilyHuang
@Date:May 22, 201810:25:00 AM
@Email:Mily-ML.Huang@aia.com

*/
public interface IParkingCardDao {
	/**
	 * 办理停车卡
	 * @param entity
	 * @return
	 */
	int insertParkingCard(ParkingCardEntity entity);
	
	/**
	 * 通过停车卡查询卡信息
	 * @param cardNum
	 * @return
	 */
	ParkingCardEntity selectCardByCardNum(@Param("cardNum") String cardNum);
}
