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
	 * æ–°ç”¨æˆ·åŠžç�†å�œè½¦å�¡
	 * @param userEntity  ç”¨æˆ·ä¿¡æ�¯
	 * @param cardEntity å�œè½¦å�¡ä¿¡æ�¯
	 * @return
	 */
	JsonResult createNewParkingCard(UserAndCardEntity entity);
	
	/**
	 * è€�ç”¨æˆ·åŠžå�¡
	 * @param cardEntity  å�œè½¦å�¡ä¿¡æ�¯
	 * @return
	 */
	JsonResult createNewParkingCardByOldUser(UserAndCardEntity cardEntity);
	
	/**
	 * é€šè¿‡æ‰‹æœºå�·ç �æŸ¥è¯¢ç”¨æˆ·ä¿¡æ�¯
	 * @param phone æ‰‹æœºå�·
	 * @return
	 */
	JsonResult selectUserByPhone(UsersInfoEntity userEntity);
	
	/**
	 * æŸ¥è¯¢ç”¨æˆ·çš„å�¡ä¿¡æ�¯
	 * @param entity  ç”µè¯�å�·ç �
	 * @return
	 */
	JsonResult selectUserCardsList(UsersInfoEntity entity);

	JsonResult createNewCardReplaceOldOne(ParkingCardEntity cardEntity);
	
}
