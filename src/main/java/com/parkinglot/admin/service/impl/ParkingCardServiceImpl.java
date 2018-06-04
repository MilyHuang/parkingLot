package com.parkinglot.admin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.parkinglot.admin.dao.IParkingCardDao;
import com.parkinglot.admin.entity.ParkingCardEntity;
import com.parkinglot.admin.service.IParkingCardService;
import com.parkinglot.common.service.ServiceException;
import com.parkinglot.common.util.JsonResult;

/**
@Description:
@version:1.0
@author:MilyHuang
@Date:May 22, 201811:53:19 AM
@Email:Mily-ML.Huang@aia.com

*/
//@Transactional
@Service("com.parkinglot.admin.service.impl.ParkingCardServiceImpl")
public class ParkingCardServiceImpl implements IParkingCardService{
	
	@Autowired
	private IParkingCardDao cardDao;
	
	@Override
	public JsonResult insertParkingCard(ParkingCardEntity entity) {
		JsonResult jsonResult = new JsonResult();
		entity.setState(0);
		//办理停车卡
		int rows = cardDao.insertParkingCard(entity);
		if(rows <=0 ) {
			jsonResult = new JsonResult(new ServiceException("添加停车卡失败"));
			return jsonResult;
		}
		return jsonResult;
	}

	@Override
	public ParkingCardEntity selectParkingCardByCardNum(String cardNum) {
		return cardDao.selectCardByCardNum(cardNum);
	}

	@Override
	public int selectCards(String parkingNum) {
		return cardDao.selectCards(parkingNum);
	}

	@Override
	public JsonResult updateParkingCard(ParkingCardEntity entity) {
		JsonResult jsonResult = new JsonResult();
		int rows = cardDao.updateParkingCard(entity);
		if(rows <=0 ) {
			jsonResult = new JsonResult(new ServiceException("更新停车卡失败"));
			return jsonResult;
		}
		return jsonResult;
	}

	@Override
	public List<ParkingCardEntity> selectUserCards(String parkingNum, Integer userId) {
		return cardDao.selectUserCards(parkingNum, userId);
	}

	@Override
	public List<ParkingCardEntity> selectAllCards() {
		return cardDao.selectAllCards();
	}

	@Override
	public ParkingCardEntity selectCardByCardId(Integer cardId) {
		return cardDao.selectCardByCardId(cardId);
	}

}
