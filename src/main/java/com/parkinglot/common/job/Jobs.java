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
import com.parkinglot.admin.entity.ParkingLotEntity;
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
			list.get(i).setStatementDate(new Date());/* 用于测试 */
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
			list.get(i).setAccount(list.get(i).getPrice());
			// 禁用停车卡
			ParkingCardEntity parkingCardEntity = parkingCardService.selectCardByCardId(list.get(i).getCardId());
			parkingCardEntity.setState(1);
			parkingCardService.updateCardState(parkingCardEntity);
			// 更新旧bill
			parkingBillService.updateOldBill(list.get(i));

			// 更改停车卡新季度账单
			ParkingBillEntity parkingBillEntity = parkingBillService.selectBillByCardIdAndFlag(list.get(i).getCardId(),
					2);
			parkingBillEntity.setStatementDate(new Date());
			System.out.println(parkingBillEntity);
			parkingBillService.updateOldBill(parkingBillEntity);
		}
		System.out.println("已更新账单");
	}

	/**
	 * 每年4月1， 7月1， 10月1， 1月1生成新季度账单
	 */
	public void createNewBill() {
		List<ParkingCardEntity> list = parkingCardService.selectAllUsedCards(0);
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i).toString());
			ParkingBillEntity parkingBillEntity = parkingBillService.selectAllParkingBillEntityByCardId(list.get(i).getId());
			System.out.println(parkingBillEntity);
			if(parkingBillEntity!=null) {
				 billUtils.generateBill(parkingBillEntity);
			}
		}
		System.out.println("已生成新季度账单");
	}

}
