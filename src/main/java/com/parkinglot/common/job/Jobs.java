package com.parkinglot.common.job;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import com.parkinglot.admin.entity.ParkingBillEntity;
import com.parkinglot.admin.entity.ParkingCardEntity;
import com.parkinglot.admin.entity.ParkingLotEntity;
import com.parkinglot.admin.entity.ParkingRecordEntity;
import com.parkinglot.admin.entity.UserAndCardEntity;
import com.parkinglot.admin.service.IParkingBillService;
import com.parkinglot.admin.service.IParkingCardService;
import com.parkinglot.admin.service.IParkingLotService;
import com.parkinglot.admin.service.IUsersInfoService;

@Component
public class Jobs {

	@Autowired
	private IParkingLotService parkingLotService;

	@Autowired
	private IParkingCardService parkingCardService;

	@Autowired
	private IParkingBillService parkingBillService;

	@Autowired
	private IUsersInfoService userService;

	
	/**
	 * 每个季度的最后一天出账单
	 */
	public void createBill() {

		List<ParkingBillEntity> list = parkingBillService.selectAllParkingBillEntitys();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getStatementDate().compareTo(new Date()) == 0 && list.get(i).getFlag() == 2) {
				Calendar ca = Calendar.getInstance();
				ca.add(ca.MONTH, 1);
				ca.set(Calendar.DAY_OF_MONTH, ca.getActualMaximum(Calendar.DAY_OF_MONTH));
				String last = format.format(ca.getTime());
				list.get(i).setTis("請在" + last + "前繳費");
				list.get(i).setFlag(1);
				parkingBillService.updateParkingBill(list.get(i));
			}
		}
	}

	public void job2() {
		System.out.println("job2 ..." + new Date());
	}

	/**
	 * 判断停车季度，生成新的账单
	 */
	private void checkParkingRecord(ParkingRecordEntity entity) {
		System.out.println(entity);
		ParkingCardEntity parkingCardEntity = parkingCardService.selectParkingCardByCardNum(entity.getCardNum());
		System.out.println(parkingCardEntity);
		ParkingBillEntity parkingBillEntity = parkingBillService
				.selectAllParkingBillEntityByCardId(parkingCardEntity.getId());
		System.out.println(parkingBillEntity);
		if (parkingBillEntity.getStatementDate().compareTo(new Date()) == -1) {
			UserAndCardEntity en = new UserAndCardEntity();
			en.setCardNum(entity.getCardNum());
			en.setParkingNum(entity.getParkingNum());
			en.setPhone(userService.selectUserInfoById(parkingCardEntity.getUserId()).getPhone());
			System.out.println(en);
			generateBill(en);
		}

	}

	/**
	 * 生成新账单
	 * 
	 * @param entity
	 */
	public void generateBill(UserAndCardEntity entity) {
		ParkingCardEntity parkingCardEntity = parkingCardService.selectParkingCardByCardNum(entity.getCardNum());
		ParkingLotEntity parkinglotEntity = parkingLotService.selectParkingLotByNum(entity.getParkingNum());
		ParkingBillEntity parkingBillEntity = new ParkingBillEntity();
		int rand = new Random().nextInt(100000);
		parkingBillEntity.setBillNum(String.valueOf(rand));
		parkingBillEntity.setAccount(parkingLotService.selectParkingLotByNum(entity.getParkingNum()).getPrice() * 3);
		parkingBillEntity.setPrice(parkingLotService.selectParkingLotByNum(entity.getParkingNum()).getPrice());
		parkingBillEntity.setCardId(parkingCardEntity.getId());
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
		parkingBillEntity.setParkingId(parkinglotEntity.getId());
		parkingBillEntity
				.setParkingName(parkingLotService.selectParkingLotByNum(entity.getParkingNum()).getParkingName());
		parkingBillEntity.setPhone(entity.getPhone());
		parkingBillService.insertParkingBill(parkingBillEntity);
	}

}
