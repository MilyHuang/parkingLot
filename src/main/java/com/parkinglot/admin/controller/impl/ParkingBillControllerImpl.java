package com.parkinglot.admin.controller.impl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.parkinglot.admin.controller.IParkingBillController;
import com.parkinglot.admin.entity.ParkingBillEntity;
import com.parkinglot.admin.entity.ParkingCardEntity;
import com.parkinglot.admin.entity.UsersInfoEntity;
import com.parkinglot.admin.service.IParkingBillService;
import com.parkinglot.admin.service.IParkingCardService;
import com.parkinglot.common.service.ServiceException;
import com.parkinglot.common.util.JsonResult;

@Controller
@RequestMapping("/parkingBill")
public class ParkingBillControllerImpl implements IParkingBillController {

	@Autowired
	private IParkingBillService parkingBillSerivce;

	@Autowired
	private IParkingCardService parkingCardService;

	@RequestMapping(value = "/selectAllParkingBillEntity", method = RequestMethod.POST)
	@ResponseBody
	@Override
	public JsonResult selectAllParkingBillEntity(@RequestBody UsersInfoEntity user) {
		JsonResult jsonResult = new JsonResult();
		List<ParkingBillEntity> list = parkingBillSerivce.selectAllParkingBillEntity(user.getPhone());
		if (list.size() == 0) {
			jsonResult = new JsonResult(new ServiceException("用户還沒有賬單信息！"));
			return jsonResult;
		}
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		for (int i = 0; i < list.size(); i++) {
			//获取cardNum 
			String cardNum = parkingCardService.selectCardByCardId(list.get(i).getCardId()).getCardNum();
			list.get(i).setCardNum(cardNum);
			if (list.get(i).getStatementDate().compareTo(new Date()) == -1
					&& parkingCardService.selectCardByCardId(list.get(i).getCardId()).getState() == 0) {
				Calendar ca = Calendar.getInstance();
				ca.set(Calendar.DAY_OF_MONTH, ca.getActualMaximum(Calendar.DAY_OF_MONTH));
				String last = format.format(ca.getTime());
				list.get(i).setTis("請在" + last + "前繳費");
			}
			if (list.get(i).getStatementDate().compareTo(new Date()) == -1
					&& parkingCardService.selectCardByCardId(list.get(i).getCardId()).getState() == 1) {
				list.get(i).setTis("请缴费恢复停车服务");
			}
		}
		return new JsonResult(list);
	}

	@RequestMapping(value = "/selectParkingBillByBillNum", method = RequestMethod.POST)
	@ResponseBody
	@Override
	public JsonResult selectParkingBillByBillNum(@RequestBody ParkingBillEntity entity) {
		System.out.println(entity);
		JsonResult jsonResult = new JsonResult();
		ParkingBillEntity parkingBillEntity = parkingBillSerivce.selectParkingBillByBillNum(entity.getBillNum());
		String cardNum = parkingCardService.selectCardByCardId(parkingBillEntity.getCardId()).getCardNum();
		parkingBillEntity.setCardNum(cardNum);
		if (parkingBillEntity == null) {
			jsonResult = new JsonResult(new ServiceException("沒有賬單信息！"));
			return jsonResult;
		}
		return new JsonResult(parkingBillEntity);
	}

}
