package com.parkinglot.common.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.parkinglot.admin.entity.ParkingBillEntity;
import com.parkinglot.admin.entity.ParkingLotEntity;
import com.parkinglot.admin.entity.PayInfoEntity;
import com.parkinglot.admin.service.IParkingBillService;
import com.parkinglot.admin.service.IParkingLotService;
import com.parkinglot.admin.service.IPayInfoService;

/**
@Description:模拟支付网关
@version:1.0
@author:MilyHuang
@Date:Jun 11, 20185:51:44 PM
@Email:Mily-ML.Huang@aia.com

*/
@Component("payUtil")
public class PayUtil {
	@Autowired
	private IPayInfoService payInfoService;
	
	@Autowired
	private IParkingBillService parkingBillService;
	
	@Autowired
	private IParkingLotService parkingLotService;
	
	public PayInfoEntity payInfoUtil(PayInfoEntity entity) {
		
		ParkingBillEntity parkingBill = parkingBillService.selectBillById(entity.getBillId());
		ParkingLotEntity parkingLot = parkingLotService.selectParkingLotById(parkingBill.getParkingId());
	//	ParkingLotEntity parkingLot = parkingLotService.selectParkingLotById(111111);
		//生成支付编号
		String payNum = "";
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		payNum = sdf.format(date);
		System.out.println(entity);
		//如果该停车场存在，则返回success information
		if(parkingLot != null) {
			PayInfoEntity payInfoEntity = new PayInfoEntity();
			payInfoEntity.setPayNum(payNum);
			payInfoEntity.setAccount(entity.getAccount());
			payInfoEntity.setBillId(entity.getBillId());
			payInfoEntity.setPhone(entity.getPhone());
			payInfoEntity.setResult(true);
			payInfoEntity.setReceiveCode(entity.getReceiveCode());
			payInfoService.insertPayInfo(payInfoEntity);
			
			PayInfoEntity payInfo = payInfoService.selectPayInfoById(payInfoEntity.getId());
			return payInfo;
			
		}else{   //如果该停车场不存在，则返回fail information
			PayInfoEntity payInfoEntity = new PayInfoEntity();
			payInfoEntity.setPayNum(payNum);
			payInfoEntity.setBillId(entity.getBillId());
			payInfoEntity.setPhone(entity.getPhone());
			payInfoEntity.setErrorCode("ER00001");
			payInfoEntity.setMessage("收款方代码错误");
			payInfoEntity.setResult(false);
			//payInfoEntity.setReceiveCode(entity.getReceiveCode());
			payInfoService.insertPayInfo(payInfoEntity);
			PayInfoEntity payInfo = payInfoService.selectPayInfoById(payInfoEntity.getId());
			return payInfo;
		}
		
		
	}
}
