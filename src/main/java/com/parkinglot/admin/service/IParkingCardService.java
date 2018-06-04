package com.parkinglot.admin.service;
/**
@Description: 停车卡管理
@version:1.0
@author:MilyHuang
@Date:May 22, 201811:48:43 AM
@Email:Mily-ML.Huang@aia.com

*/

import java.util.List;


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
	 * @param parkingNum 停车场编号
	 * @return
	 */
	ParkingCardEntity selectParkingCardByCardNumAndParkingNum(String cardNum,Integer parkingId);
	
	/**
	 * 查找停车场中能用的卡的总数
	 * @return
	 */
	int selectCards(Integer parkingId);
	
	/**
	 * 停车后更新停车卡信息
	 * @param entity
	 * @return
	 */
	JsonResult updateParkingCard(ParkingCardEntity entity);
	
	/**
	 * 查找某用户在某停车场中卡的总数
	 * @return
	 */
	List<ParkingCardEntity> selectUserCards(Integer parkingId, Integer userId);
	
	
	/**
	 * 查找停车场中卡的总数
	 * @return
	 */
	List<ParkingCardEntity> selectAllCards();
	
	/**
	 * 查询卡号是否
	 * @param cardNum
	 * @return
	 */
	ParkingCardEntity selectCardByCardNum(String cardNum);
}
