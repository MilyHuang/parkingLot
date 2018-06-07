package com.parkinglot.admin.service;
/**
@Description: 停车卡管理
@version:1.0
@author:MilyHuang
@Date:May 22, 201811:48:43 AM
@Email:Mily-ML.Huang@aia.com

*/

import java.util.List;

import org.apache.ibatis.annotations.Param;

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
	ParkingCardEntity selectParkingCardByCardNum(String cardNum);
	
	/**
	 * 查找停车场中能用的卡的总数
	 * @return
	 */
	int selectCards(@Param("parkingId") Integer parkingId);
	
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
	List<ParkingCardEntity> selectUserCards(@Param("userId") Integer userId);
	
	
	/**
	 * 查找停车场中卡的总数
	 * @return
	 */
	List<ParkingCardEntity> selectAllCards();
	
	/**
	 * 通过停车卡id查询卡信息
	 * @param cardNum
	 * @return
	 */
	ParkingCardEntity selectCardByCardId(@Param("cardId") Integer cardId);
	
	/**
	 * 用户办理卡的总数
	 * @param userId
	 * @return
	 */
	int countCardsForUser(Integer userId);
	
	/**
	 * 查询未停用停车卡
	 * @param id
	 * @return
	 */
	ParkingCardEntity selectActiveCard(int id);

	/**
	 * 更新停车卡的状态
	 * @param cardEntity
	 * @return
	 */
	JsonResult updateCardState(ParkingCardEntity cardEntity);
	
}
