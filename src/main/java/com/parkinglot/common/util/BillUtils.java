package com.parkinglot.common.util;

import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.parkinglot.admin.entity.ParkingBillEntity;
import com.parkinglot.admin.service.IParkingBillService;
import com.parkinglot.admin.service.IParkingLotService;

/**
@Description:  生成新的账单
@version:1.0
@author:MilyHuang
@Date:Jun 6, 20184:06:29 PM
@Email:Mily-ML.Huang@aia.com

*/
@Component("BillUtils")
public class BillUtils {
	
	@Autowired
	private  IParkingBillService parkingBillService;
	
	@Autowired
	private IParkingLotService parkingService;
	
	public  void generateBill(ParkingBillEntity billEntity) {
		System.out.println("bill:"+billEntity);
		int rand = new Random().nextInt(100000);
		billEntity.setBillNum(String.valueOf(rand));  //设置账单编号
		Calendar ca = Calendar.getInstance();
		ca.setTime(new Date());
		int nowDate = ca.get(Calendar.DAY_OF_MONTH);
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
		billEntity.setFirstDate(new Date());
		billEntity.setStatementDate(ca.getTime());  //设置时间
		billEntity.setFlag(2);   //设置账单状态
		// 获取当前月天数
		ca.set(Calendar.DATE, 1);// 把日期设置为当月第一天
		ca.roll(Calendar.DATE, -1);// 日期回滚一天，也就是最后一天
		int maxDate = ca.get(Calendar.DATE);
		double account = parkingService.selectParkingLotById(billEntity.getParkingId()).getPrice() * (maxDate - nowDate + 1)
				/ maxDate;
		System.out.println(account);
		DecimalFormat df = new DecimalFormat("#.00");
		billEntity.setAccount(Double.parseDouble(df.format(account)));
		billEntity.setPrice(parkingService.selectParkingLotById(billEntity.getParkingId()).getPrice());
		System.out.println(parkingService.selectParkingLotById(billEntity.getParkingId()));
		parkingBillService.insertParkingBill(billEntity);
	}
}
