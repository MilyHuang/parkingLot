package com.parkinglot.admin.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.parkinglot.admin.dao.IParkingBillDao;
import com.parkinglot.admin.dao.IParkingCardDao;
import com.parkinglot.admin.dao.IParkingRecordDao;
import com.parkinglot.admin.entity.ParkingBillEntity;
import com.parkinglot.admin.entity.ParkingCardEntity;
import com.parkinglot.admin.entity.ParkingRecordEntity;
import com.parkinglot.admin.entity.UserAndCardEntity;
import com.parkinglot.admin.service.IParkingRecordService;
import com.parkinglot.common.service.ServiceException;
import com.parkinglot.common.util.JsonResult;

/**
 * @Description:
 * @version:1.0
 * @author:MilyHuang
 * @Date:May 24, 201811:18:32 AM
 * @Email:Mily-ML.Huang@aia.com
 * 
 */
@Service("com.parkinglot.admin.service.impl.ParkingRecordServiceImpl")
public class ParkingRecordServiceImpl implements IParkingRecordService {

	@Autowired
	private IParkingRecordDao recordDao;

	@Autowired
	private IParkingCardDao parkingCardDao;

	@Autowired
	private IParkingBillDao parkingBillDao;

	@Override
	public JsonResult updateParkingRecord(ParkingRecordEntity entity) {
		JsonResult jsonResult = new JsonResult();
		int rows = recordDao.updateParkingRecord(entity);
		if (rows <= 0) {
			jsonResult = new JsonResult(new ServiceException("取车失败"));
			return jsonResult;
		}
		return jsonResult;
	}

	@Override
	public JsonResult insertParkingRecord(ParkingRecordEntity entity) {
		JsonResult jsonResult = new JsonResult();
		int rows = recordDao.insertParkingRecord(entity);
		if (rows <= 0) {
			jsonResult = new JsonResult(new ServiceException("停车失败"));
			return jsonResult;
		}
		return jsonResult;
	}

	@Override
	public ParkingRecordEntity selectParkingRecord(String cardNum) {
		ParkingCardEntity entity = parkingCardDao.selectCardByCardNum(cardNum,0);
		if(entity == null) {
			return null;
		}
		return recordDao.selectParkingRecord(entity.getId());
	}

	@Override
	public ParkingRecordEntity isHasCarInTheParking(int id) {
		System.out.println("step in isHasCarInTheParking service");
		ParkingRecordEntity entity = recordDao.isHasCarInTheParking(id);
	//	System.out.println("step in isHasCarInTheParking service result"+entity.toString());
		return entity;
	}

	public JsonResult checkCard(ParkingRecordEntity entity) {
		// TODO Auto-generated method stub
		return null;
	}

//	/**
//	 * 卡自动过期
//	 */
//	public JsonResult checkCard(ParkingRecordEntity entity) {
//
//		ParkingCardEntity parkingCardEntity = parkingCardDao.selectCardByCardNum(entity.getCardNum());
//		ParkingBillEntity parkingBillEntity = parkingBillDao
//				.selectAllParkingBillEntityByCardId(parkingCardEntity.getId());
//		JsonResult jsonResult = new JsonResult();
//		if (parkingBillEntity != null) {
//			if (parkingBillEntity.getStatementDate().compareTo(new Date()) == -1 && parkingBillEntity.getFlag() == 0) {
//				parkingCardEntity.setState(1);
//				parkingCardDao.updateParkingCard(parkingCardEntity);
//				jsonResult = new JsonResult(new ServiceException("卡被禁，请缴费激活"));
//			}
//		}
//		return jsonResult;
//	}

}
