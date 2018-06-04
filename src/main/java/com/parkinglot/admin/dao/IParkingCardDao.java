package com.parkinglot.admin.dao;

import java.util.List;

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
	 * 通过停车卡num查询卡信息
	 * @param cardNum
	 * @return
	 */
	ParkingCardEntity selectCardByCardNum(@Param("cardNum") String cardNum);
	
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
	int updateParkingCard(ParkingCardEntity entity);
	
	
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
	
}
