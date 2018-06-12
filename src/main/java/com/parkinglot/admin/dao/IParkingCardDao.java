package com.parkinglot.admin.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.parkinglot.admin.entity.ParkingCardEntity;
import com.parkinglot.common.util.JsonResult;

/**
@Description: æ·»åŠ å�œè½¦å�¡ï¼ˆåŠžç�†å�œè½¦å�¡ï¼‰
@version:1.0
@author:MilyHuang
@Date:May 22, 201810:25:00 AM
@Email:Mily-ML.Huang@aia.com

*/
public interface IParkingCardDao {
	/**
	 * åŠžç�†å�œè½¦å�¡
	 * @param entity
	 * @return
	 */
	int insertParkingCard(ParkingCardEntity entity);
	
	/**
	 * é€šè¿‡å�œè½¦å�¡numæŸ¥è¯¢å�¡ä¿¡æ�¯
	 * @param cardNum
	 * @param state   0 å�¯ç”¨ï¼Œ1å�œç”¨
	 * @return
	 */
	ParkingCardEntity selectCardByCardNum(@Param("cardNum") String cardNum,@Param("state") Integer state);
	
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
	int updateParkingCard(ParkingCardEntity entity);
	
	
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
	 * æŸ¥è¯¢æŸ�ä¸ªç”¨æˆ·åŠžç�†å�œè½¦å�¡çš„æ€»æ•°
	 * @return
	 */
	int countCardsForUser(@Param("userId") Integer userId);
	
	/**
	 * æŸ¥è¯¢æœªå�œç”¨å�œè½¦å�¡
	 * @param id
	 * @return
	 */
	int updateCardsUseLimit(int id);
	
	 /**更新停车卡的状态
	  * 
	  * @param id
	  * @return
	  */
	ParkingCardEntity selectActiveCard(int id);
	
	 /**æ›´æ–°å�œè½¦å�¡çš„çŠ¶æ€�
	 * @param cardEntity
	 * @return
	 */
	int updateCardState(ParkingCardEntity cardEntity);
	
	
	/**
	 * æŸ¥æ‰¾å�œè½¦åœºä¸­å�¡çš„æ€»æ•°
	 * @return
	 */
	List<ParkingCardEntity> selectAllUsedCards(Integer state);
	
	/**
	 * Ã¦â€ºÂ´Ã¦ï¿½Â¢Ã¦â€“Â°Ã¥ï¿½Â¡
	 * @param 
	 * @return
	 */
	int updateCardNumById(ParkingCardEntity cardEntity);
	

	/**
	 * Ã¦Å¸Â¥Ã¨Â¯Â¢Ã¥ï¿½Â¡Ã§Å¡â€žÃ§Å Â¶Ã¦â‚¬ï¿½
	 * @param id
	 * @return
	 */
	int selectCardStateById(int id);
	
	/**
	 * Ã¦Å¸Â¥Ã¨Â¯Â¢Ã¦Å¸ï¿½Ã¥ï¿½Å“Ã¨Â½Â¦Ã¥ï¿½Â¡Ã¦ËœÂ¯Ã¥ï¿½Â¦Ã¥Â­ËœÃ¥Å“Â¨
	 * @param id
	 * @return
	 */
	ParkingCardEntity selectCardByCardNumber(String cardNum);
	
}
