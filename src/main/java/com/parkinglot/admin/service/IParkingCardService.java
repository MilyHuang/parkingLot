package com.parkinglot.admin.service;
/**
@Description: å�œè½¦å�¡ç®¡ç�†
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
	 * åŠžç�†å�œè½¦å�¡
	 * @param entity
	 * @return
	 */
	JsonResult insertParkingCard(ParkingCardEntity entity);
	
	/**
	 * æŸ¥è¯¢å�œè½¦å�¡æ˜¯å�¦å­˜åœ¨
	 * @param cardNum å�œè½¦å�¡å�·
	 * @param state  0 å�¯ç”¨ï¼Œ1å�œç”¨
	 * @return
	 */
	ParkingCardEntity selectParkingCardByCardNum(String cardNum,Integer state);
	
	/**
	 * æŸ¥æ‰¾å�œè½¦åœºä¸­èƒ½ç”¨çš„å�¡çš„æ€»æ•°
	 * @return
	 */
	int selectCards(@Param("parkingId") Integer parkingId);
	
	/**
	 * å�œè½¦å�Žæ›´æ–°å�œè½¦å�¡ä¿¡æ�¯
	 * @param entity
	 * @return
	 */
	JsonResult updateParkingCard(ParkingCardEntity entity);
	
	/**
	 * æŸ¥æ‰¾æŸ�ç”¨æˆ·åœ¨æŸ�å�œè½¦åœºä¸­å�¡çš„æ€»æ•°
	 * @return
	 */
	List<ParkingCardEntity> selectUserCards(@Param("userId") Integer userId);
	
	
	/**
	 * æŸ¥æ‰¾å�œè½¦åœºä¸­å�¡çš„æ€»æ•°
	 * @return
	 */
	List<ParkingCardEntity> selectAllCards();
	
	/**
	 * é€šè¿‡å�œè½¦å�¡idæŸ¥è¯¢å�¡ä¿¡æ�¯
	 * @param cardNum
	 * @return
	 */
	ParkingCardEntity selectCardByCardId(@Param("cardId") Integer cardId);
	
	/**
	 * ç”¨æˆ·åŠžç�†å�¡çš„æ€»æ•°
	 * @param userId
	 * @return
	 */
	int countCardsForUser(Integer userId);
	
	/**
	 * æŸ¥è¯¢æœªå�œç”¨å�œè½¦å�¡
	 * @param id
	 * @return
	 */
	ParkingCardEntity selectActiveCard(int id);

	/**
	 * æ›´æ–°å�œè½¦å�¡çš„çŠ¶æ€�
	 * @param cardEntity
	 * @return
	 */
	JsonResult updateCardState(ParkingCardEntity cardEntity);
	/**
	 * æŸ¥æ‰¾å�œè½¦åœºä¸­å�¡çš„æ€»æ•°
	 * @return
	 */
	List<ParkingCardEntity> selectAllUsedCards(Integer state);

	int updateCardNumById(int id, String cardNum);

	int selectCardStateById(int id);

	ParkingCardEntity selectCardByCardNumber(String cardNum);
}
