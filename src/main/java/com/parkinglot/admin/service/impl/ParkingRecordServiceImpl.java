package com.parkinglot.admin.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
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
import com.parkinglot.admin.entity.UsageOfParkingCard;
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
		ParkingCardEntity entity = parkingCardDao.selectCardByCardNum(cardNum, 0);
		if (entity != null) {
			return recordDao.selectParkingRecord(entity.getId());
		}
		return null;
	}

	@Override
	public ParkingRecordEntity isHasCarInTheParking(int id) {
		System.out.println("step in isHasCarInTheParking service");
		ParkingRecordEntity entity = recordDao.isHasCarInTheParking(id);
		// System.out.println("step in isHasCarInTheParking service
		// result"+entity.toString());
		return entity;
	}

	@Override
	public List<UsageOfParkingCard> selectUsageOfParkingCard(UsageOfParkingCard entity) {
		System.out.println(entity);
		UsageOfParkingCard usageOfParkingCard = setDate(entity);
		System.out.println(usageOfParkingCard);
		List<UsageOfParkingCard> list = recordDao.selectUsageOfParkingCard(usageOfParkingCard);
		ParkingCardEntity parkingCardEntity = new ParkingCardEntity();
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i).getCardId());
			parkingCardEntity = parkingCardDao.selectCardByCardId(list.get(i).getCardId());
			System.out.println(parkingCardEntity);
			list.get(i).setCardNum(parkingCardEntity.getCardNum());
		}
		return list;
	}

	public UsageOfParkingCard setDate(UsageOfParkingCard entity) {
		if (entity.getFirstDate() != null) {
			Calendar cal = Calendar.getInstance();
			cal.setTime(entity.getFirstDate());
			cal.set(Calendar.DAY_OF_MONTH, 1);
			entity.setFirstDate(cal.getTime());
			cal.roll(Calendar.DAY_OF_MONTH, -1);
			entity.setLastDate(cal.getTime());
		}
		return entity;
	}

}
