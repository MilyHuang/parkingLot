package com.parkinglot.admin.controller.impl;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

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
import com.parkinglot.admin.service.IParkingLotService;
import com.parkinglot.common.service.ServiceException;
import com.parkinglot.common.util.BillUtils;
import com.parkinglot.common.util.JsonResult;

@Controller
@RequestMapping("/parkingBill")
public class ParkingBillControllerImpl implements IParkingBillController {

	@Autowired
	private IParkingBillService parkingBillSerivice;

	@Autowired
	private IParkingCardService parkingCardService;

	@Autowired
	private IParkingLotService parkingService;
	
	@Autowired
	private BillUtils billUtils;
	
	@RequestMapping(value = "/selectAllParkingBillEntity", method = RequestMethod.POST)
	@ResponseBody
	@Override
	public JsonResult selectAllParkingBillEntity(@RequestBody UsersInfoEntity user) {
		JsonResult jsonResult = new JsonResult();
		List<ParkingBillEntity> list = parkingBillSerivice.selectAllParkingBillEntity(user.getPhone());
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
				list.get(i).setTis("请在" + last + "前缴费");
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
		JsonResult jsonResult = new JsonResult();
		ParkingBillEntity parkingBillEntity = parkingBillSerivice.selectParkingBillByBillNum(entity.getBillNum());
		String cardNum = parkingCardService.selectCardByCardId(parkingBillEntity.getCardId()).getCardNum();
		parkingBillEntity.setCardNum(cardNum);
		if (parkingBillEntity == null) {
			jsonResult = new JsonResult(new ServiceException("沒有賬單信息！"));
			return jsonResult;
		}
		return new JsonResult(parkingBillEntity);
	}

	@RequestMapping(value="/payBill",method=RequestMethod.POST)
	@ResponseBody
	@Override
	public JsonResult payBill(@RequestBody ParkingBillEntity billEntity) {
		JsonResult jsonResult = new JsonResult();
		System.out.println(billEntity);
		//通过账单编号查询账单信息
		ParkingBillEntity bill = parkingBillSerivice.selectParkingBillByBillNum(billEntity.getBillNum());
		if(bill == null) {
			return  new JsonResult(new ServiceException("该账单不存在"));
		}
		//0 未缴费，1 已缴费 2未出账  3 逾期欠费
		if(bill.getFlag() == 0) {  //未缴费
			billEntity.setFlag(1);
			billEntity.setId(bill.getId());
			billEntity.setTis("");
			parkingBillSerivice.updateBillInfo(billEntity);
			return jsonResult;
		}else if(bill.getFlag() == 3) {  //逾期缴费
			//缴费
			billEntity.setFlag(1);
			billEntity.setId(bill.getId());
			billEntity.setTis("");
			parkingBillSerivice.updateBillInfo(billEntity);
			//更改用户停车卡的状态，重新启用
			ParkingCardEntity cardEntity = new ParkingCardEntity();
			cardEntity.setState(0); //启用      状态：0 可用 ，1 不可用
			cardEntity.setId(bill.getCardId());  //停车卡id
			parkingCardService.updateCardState(cardEntity);
			//重新生成新的账单
			ParkingBillEntity billEntity2 = new ParkingBillEntity();
			billEntity2.setCardId(bill.getCardId());
			billEntity2.setParkingId(bill.getParkingId());
			billEntity2.setParkingName(bill.getParkingName());
			billEntity2.setPhone(bill.getPhone());
			billUtils.generateBill(billEntity2);
			return jsonResult;
		}else if(bill.getFlag() == 1) {
			return new JsonResult(new ServiceException("该账单已缴费"));
		}else if(bill.getFlag() == 2) {
			return new JsonResult(new ServiceException("该账单未出账"));
		}
		return jsonResult;
	}

	
	
	
	
	
	
	
	
}
