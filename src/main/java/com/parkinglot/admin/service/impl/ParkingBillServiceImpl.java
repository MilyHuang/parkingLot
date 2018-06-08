package com.parkinglot.admin.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.parkinglot.admin.dao.IParkingBillDao;
import com.parkinglot.admin.entity.ParkingBillEntity;
import com.parkinglot.admin.entity.ParkingCardEntity;
import com.parkinglot.admin.service.IParkingBillService;
import com.parkinglot.common.service.ServiceException;
import com.parkinglot.common.util.JsonResult;

@Service("com.parkinglot.admin.service.impl.ParkingBillServiceImpl")
public class ParkingBillServiceImpl implements IParkingBillService {

	@Autowired
	private IParkingBillDao parkingBillDao;

	@Override
	public List<ParkingBillEntity> selectAllParkingBillEntity(String phone) {
		return parkingBillDao.selectAllParkingBillEntity(phone);
	}

	@Override
	public ParkingBillEntity selectParkingBillByBillNum(String billNum) {
		return parkingBillDao.selectParkingBillByBillNum(billNum);
	}

	@Override
	public int insertParkingBill(ParkingBillEntity entity) {
		// ParkingLotEntity parkingLotEntity =
		// parkingDao.selectParkingLotByNum(entity.getParkingNum());
		// entity.setPrice(parkingLotEntity.getPrice());
		// entity.setAccount(parkingLotEntity.get);
		// entity.setFirstDate(new Date());
		return parkingBillDao.insertParkingBill(entity);
	}

	@Override
	public List<ParkingBillEntity> selectAllParkingBillEntityByCard(ParkingCardEntity entity) {
		return parkingBillDao.selectAllParkingBillEntityByCard(entity.getId());
	}

	@Override
	public List<ParkingBillEntity> selectBillsByPhoneAndFlag(String phone, Integer flag) {
		return parkingBillDao.selectBillsByPhoneAndFlag(phone, flag);
	}

	@Override
	public ParkingBillEntity selectUnPayBill(int id) {
		System.out.println("selectUnPayBill" + id);
		ParkingBillEntity unPayBill = parkingBillDao.selectUnPayBill(id);
		return unPayBill;
	}

	public ParkingBillEntity selectAllParkingBillEntityByCardId(Integer cardId) {
		return parkingBillDao.selectAllParkingBillEntityByCardId(cardId);
	}

	@Override
	public List<ParkingBillEntity> selectAllParkingBillEntitys(Integer flag) {
		return parkingBillDao.selectAllParkingBillEntitys(flag);
	}

	@Override
	public int updateParkingBill(ParkingBillEntity entity) {
		return parkingBillDao.updateParkingBill(entity);
	}

	public JsonResult updateBillInfo(ParkingBillEntity billEntity) {
		JsonResult jsonResult = new JsonResult();
		int rows = parkingBillDao.updateBillInfo(billEntity);
		if (rows <= 0) {
			return new JsonResult(new ServiceException("缴费失败"));
		}
		return jsonResult;
	}

	@Override
	public ParkingBillEntity selectBillByCardIdAndFlag(Integer cardId, Integer flag) {
		return parkingBillDao.selectBillByCardIdAndFlag(cardId, flag);
	}
	public JsonResult updateOldBill(ParkingBillEntity billEntity) {
		return parkingBillDao.updateOldBill(billEntity);
	}

	@Override
	public List<ParkingBillEntity> selectAllParkingBillEntityByParkingLot(Integer parkingId) {
		return parkingBillDao.selectAllParkingBillEntityByParkingLot(parkingId);
	}

}
