package com.parkinglot.admin.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.parkinglot.admin.dao.IParkingBillDao;
import com.parkinglot.admin.dao.IParkingLotDao;
import com.parkinglot.admin.entity.ParkingBillEntity;
import com.parkinglot.admin.entity.ParkingCardEntity;
import com.parkinglot.admin.entity.ParkingLotEntity;
import com.parkinglot.admin.service.IParkingBillService;

@Service("com.parkinglot.admin.service.impl.ParkingBillServiceImpl")
public class ParkingBillServiceImpl implements IParkingBillService {

	@Autowired
	private IParkingBillDao parkingBillDao;

	@Autowired
	private IParkingLotDao parkingDao;

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
//		ParkingLotEntity parkingLotEntity = parkingDao.selectParkingLotByNum(entity.getParkingNum());
//		entity.setPrice(parkingLotEntity.getPrice());
//		entity.setAccount(parkingLotEntity.get);
//		entity.setFirstDate(new Date());
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
	public ParkingBillEntity selectAllParkingBillEntityByCardId(Integer cardId) {
		return parkingBillDao.selectAllParkingBillEntityByCardId(cardId);
	}

	@Override
	public List<ParkingBillEntity> selectAllParkingBillEntitys() {
		return parkingBillDao.selectAllParkingBillEntitys();
	}

	@Override
	public int updateParkingBill(ParkingBillEntity entity) {
		return parkingBillDao.updateParkingBill(entity);
	}

}
