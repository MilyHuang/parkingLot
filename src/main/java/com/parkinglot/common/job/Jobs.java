package com.parkinglot.common.job;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.parkinglot.admin.entity.ParkingBillEntity;
import com.parkinglot.admin.entity.ParkingCardEntity;
import com.parkinglot.admin.service.IParkingBillService;
import com.parkinglot.admin.service.IParkingCardService;
import com.parkinglot.admin.service.IParkingLotService;
import com.parkinglot.common.util.BillUtils;

@Component
public class Jobs {

	@Autowired
	private IParkingCardService parkingCardService;

	@Autowired
	private IParkingBillService parkingBillService;

	@Autowired
	private IParkingLotService parkinglotService;
	
	@Autowired
	private BillUtils billUtils;

	/**
	 * 每个季度的最后一天出账单
	 */
	public void createBill() {

		List<ParkingBillEntity> list = parkingBillService.selectAllParkingBillEntitys(2);/* 0 未缴费，1 已缴费 2未出账 3 逾期欠费 */
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		for (int i = 0; i < list.size(); i++) {
			Calendar ca = Calendar.getInstance();
			ca.add(ca.MONTH, 1);
			ca.set(Calendar.DAY_OF_MONTH, ca.getActualMaximum(Calendar.DAY_OF_MONTH));
			String last = format.format(ca.getTime());
			list.get(i).setTis("請在" + last + "前繳費");
			list.get(i).setFlag(0);
			parkingBillService.updateParkingBill(list.get(i));
		}
		System.out.println("季末，已出账单");
	}

	/**
	 * 每年4月30， 7月31， 10月30， 1月31更新账单
	 */
	public void updateBill() {

		List<ParkingBillEntity> list = parkingBillService.selectAllParkingBillEntitys(0);/* 0 未缴费，1 已缴费 2未出账 3 逾期欠费 */
		for (int i = 0; i < list.size(); i++) {
			list.get(i).setTis("请缴费激活停车服务");
			list.get(i).setFlag(3);
			ParkingCardEntity parkingCardEntity = parkingCardService.selectCardByCardId(list.get(i).getCardId());
			parkingCardEntity.setState(1);
			parkingCardService.updateCardState(parkingCardEntity);
			parkingBillService.updateParkingBill(list.get(i));
			createOverdueBill(list.get(i));
		}
		System.out.println("已更新账单");
	}

	/**
	 * 每年4月1， 7月1， 10月1， 1月1生成新季度账单
	 */
	public void createNewBill() {
		List<ParkingBillEntity> list0 = parkingBillService.selectAllParkingBillEntitys(0);
		List<ParkingBillEntity> list1 = parkingBillService.selectAllParkingBillEntitys(1);
		List<ParkingBillEntity> list2 = new ArrayList<ParkingBillEntity>();
		list2.addAll(list0);
		list2.addAll(list1);
		for (int i = 0; i < list2.size(); i++) {
			ParkingCardEntity parkingCardEntity = parkingCardService.selectCardByCardId(list2.get(i).getCardId());
			ParkingBillEntity parkingBillEntity = parkingBillService
					.selectAllParkingBillEntityByCardId(parkingCardEntity.getId());
			if (parkingCardEntity.getState() == 0) {
				billUtils.generateBill(parkingBillEntity);
			}
		}
		System.out.println("已生成新季度账单");
	}

	
	/**
	 * 逾期不交费，生成新季度第一个月的账单
	 */
	public void createOverdueBill(ParkingBillEntity entity) {
		int rand = new Random().nextInt(100000);
		entity.setBillNum(String.valueOf(rand));  //设置账单编号
		Calendar ca = Calendar.getInstance();
		ca.setTime(new Date());
		Integer year = ca.get(Calendar.YEAR);
		Integer month = ca.get(Calendar.MONTH) + 1;
		ca.set(year, month, 1);
		entity.setFirstDate(ca.getTime());
		entity.setStatementDate(new Date());  //设置时间
		// 获取当前月天数
		double account = parkinglotService.selectParkingLotById(entity.getParkingId()).getPrice();
		DecimalFormat df = new DecimalFormat("#.00");
		entity.setAccount(Double.parseDouble(df.format(account)));
		entity.setPrice(parkinglotService.selectParkingLotById(entity.getParkingId()).getPrice());
		System.out.println(parkinglotService.selectParkingLotById(entity.getParkingId()));
		parkingBillService.insertParkingBill(entity);
	}
	
	
	
}
