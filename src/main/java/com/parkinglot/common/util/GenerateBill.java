package com.parkinglot.common.util;

import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import com.parkinglot.admin.entity.ParkingBillEntity;
import com.parkinglot.admin.entity.UserAndCardEntity;
import com.parkinglot.admin.service.impl.ParkingBillServiceImpl;
import com.parkinglot.admin.service.impl.ParkingLotServiceImpl;

public class GenerateBill {

	private ParkingLotServiceImpl parkingService;
	
	private ParkingBillServiceImpl parkingBillService;
	/**
	 * 生成賬單
	 */
	public void generateBill(UserAndCardEntity entity) {
		ParkingBillEntity parkingBillEntity = new ParkingBillEntity();
		int rand = new Random().nextInt(100000);
		parkingBillEntity.setBillNum(String.valueOf(rand));
		parkingBillEntity.setAccount(parkingService.selectParkingLotByNum(entity.getParkingNum()).getPrice() * 3);
		parkingBillEntity.setPrice(parkingService.selectParkingLotByNum(entity.getParkingNum()).getPrice());
		parkingBillEntity.setCardNum(entity.getCardNum());
		Calendar ca = Calendar.getInstance();
		ca.setTime(new Date());
		Integer year = ca.get(Calendar.YEAR);
		Integer month = ca.get(Calendar.MONTH) + 1;
		switch (month) {
		case 1:
			;
		case 2:
			;
		case 3:
			ca.set(year, 2, 31);
			break;
		case 4:
			;
		case 5:
			;
		case 6:
			ca.set(year, 5, 30);
			break;
		case 7:
			;
		case 8:
			;
		case 9:
			ca.set(year, 8, 30);
			break;
		case 10:
			;
		case 11:
			;
		case 12:
			ca.set(year, 11, 31);
			break;
		}
		System.out.println(ca.getTime());
		parkingBillEntity.setFirstDate(new Date());
		parkingBillEntity.setStatementDate(ca.getTime());
		parkingBillEntity.setFlag(2);
		parkingBillEntity.setParkingNum(entity.getParkingNum());
		parkingBillEntity.setParkingName(parkingService.selectParkingLotByNum(entity.getParkingNum()).getParkingName());
		parkingBillEntity.setPhone(entity.getPhone());
		parkingBillService.insertParkingBill(parkingBillEntity);
	}
	
}
