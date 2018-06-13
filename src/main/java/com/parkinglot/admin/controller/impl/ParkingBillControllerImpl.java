package com.parkinglot.admin.controller.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.parkinglot.admin.controller.IParkingBillController;
import com.parkinglot.admin.entity.BillAndPaymentEntity;
import com.parkinglot.admin.entity.ParkingBillEntity;
import com.parkinglot.admin.entity.ParkingCardEntity;
import com.parkinglot.admin.entity.ParkingLotEntity;
import com.parkinglot.admin.entity.PaymentEntity;
import com.parkinglot.admin.entity.PaymentRecvEntity;
import com.parkinglot.admin.entity.UsersInfoEntity;
import com.parkinglot.admin.service.IParkingBillService;
import com.parkinglot.admin.service.IParkingCardService;
import com.parkinglot.admin.service.IParkingLotService;
import com.parkinglot.admin.service.IUsersInfoService;
import com.parkinglot.common.service.ServiceException;
import com.parkinglot.common.util.BillUtils;
import com.parkinglot.common.util.HttpURLConnectionUtils;
import com.parkinglot.common.util.JsonResult;

@Controller
@RequestMapping("/parkingBill")
public class ParkingBillControllerImpl implements IParkingBillController {
	
	/**访问第三方接口的url*/
	private static final String path=" https://simbank/redeem";
	

	@Autowired
	private IParkingBillService parkingBillSerivice;

	@Autowired
	private IParkingCardService parkingCardService;

	@Autowired 
	private IParkingLotService parkingLotService;
	
	@Autowired
	private BillUtils billUtils;

	@Autowired
	private IUsersInfoService usersInfoService;
	
	@RequestMapping(value = "/selectAllParkingBillEntity", method = RequestMethod.POST)
	@ResponseBody
	@Override
	public JsonResult selectAllParkingBillEntity(@RequestBody UsersInfoEntity user) {
		JsonResult jsonResult = new JsonResult();
		UsersInfoEntity userInfo = usersInfoService.selectUserInfoByPhone(user.getPhone());
		//判断该手机号是否存在
		if(userInfo == null) {
			return new JsonResult(new ServiceException("该账号不存在"));
		}
		//判断是否有账单信息
		List<ParkingBillEntity> list = parkingBillSerivice.selectAllParkingBillEntity(user.getPhone());
		if (list.size() == 0) {
			jsonResult = new JsonResult(new ServiceException("用户还没有账单信息！"));
			return jsonResult;
		}
		//把账单信息返回给前端
		for(int i=0;i<list.size();i++) {
			String cardNum = parkingCardService.selectCardByCardId(list.get(i).getCardId()).getCardNum();
			list.get(i).setCardNum(cardNum);
		}
		return new JsonResult(list);
	}

	@RequestMapping(value = "/selectParkingBillByBillNum", method = RequestMethod.POST)
	@ResponseBody
	@Override
	public JsonResult selectParkingBillByBillNum(@RequestBody ParkingBillEntity entity) {
		ParkingBillEntity parkingBill = parkingBillSerivice.selectParkingBillByBillNum(entity.getBillNum());
		String cardNum = parkingCardService.selectCardByCardId(parkingBill.getCardId()).getCardNum();
		parkingBill.setCardNum(cardNum);
		return new JsonResult(parkingBill);
	}

	@RequestMapping(value = "/payBill", method = RequestMethod.POST)
	@ResponseBody
	@Override
	public JsonResult payBill(@RequestBody BillAndPaymentEntity entity) {
		System.out.println("paybill:"+entity);
		JsonResult jsonResult = new JsonResult();
		// 通过账单编号查询账单信息
		ParkingBillEntity bill = parkingBillSerivice.selectParkingBillByBillNum(entity.getBillNum());
		if (bill == null) {
			return new JsonResult(new ServiceException("该账单不存在"));
		}
		// 0 未缴费，1 已缴费 2未出账 3 逾期欠费
		ParkingBillEntity billEntity = new ParkingBillEntity();
		//设置第三方接口传入参数
		PaymentEntity paymentEntity = new PaymentEntity();
		paymentEntity.setPaymentCode(entity.getPaymentCode());
		paymentEntity.setPayeeCode("simParkingCompany");
		paymentEntity.setPayeePassword("1234");
		paymentEntity.setPayerPhone(bill.getPhone());
		paymentEntity.setAmount(bill.getAccount());
		//把payment对象转为JSON字符串
		String data = JSONObject.toJSONString(paymentEntity);
		
		if (bill.getFlag() == 0) {
			String str = HttpURLConnectionUtils.sendPost(path, data);  //调用接口
			//获取接口返回的参数
			PaymentRecvEntity recv = new PaymentRecvEntity();
			recv=JSONObject.parseObject(str, PaymentRecvEntity.class);
			System.out.println("recv:"+recv);
			if(recv.getRedeemResultCode() == 0) {
				//缴费成功
				billEntity.setFlag(1);
				billEntity.setId(bill.getId());
				billEntity.setTis("");
				parkingBillSerivice.updateBillInfo(billEntity);
			}
			return new JsonResult(recv);
		} else if (bill.getFlag() == 3) { // 逾期缴费
				//停车场信息
				ParkingLotEntity parkingLot = parkingLotService.selectParkingLotById(bill.getParkingId());
				//判断停车场是否已满
				if (parkingCardService.selectCards(parkingLot.getId()) >= parkingLotService.selectParkingLotById(parkingLot.getId()).getTotal()) {
					return new JsonResult(new ServiceException("该停车场已满，不能缴费激活该停车卡"));
				}else { //如果停车场未满
					String str = HttpURLConnectionUtils.sendPost(path, data);  //调用接口
					//获取接口返回的参数
					PaymentRecvEntity recv = new PaymentRecvEntity();
					recv=JSONObject.parseObject(str, PaymentRecvEntity.class);
					System.out.println("recv:"+recv);
					if(recv.getRedeemResultCode() == 0) {
						//缴费成功
						billEntity.setFlag(1);
						billEntity.setId(bill.getId());
						billEntity.setTis("");
						parkingBillSerivice.updateBillInfo(billEntity);
					}
					//判断逾期缴费的账单是否全部付清
					List<ParkingBillEntity> bills = parkingBillSerivice.selectBillByCardIdAndFlagForList(bill.getCardId(), 3);  //flag = 3
					if(bills.size()!=0) {
						return new JsonResult(recv);
					}	
					// 更改用户停车卡的状态，重新启用
					ParkingCardEntity cardEntity = new ParkingCardEntity();
					cardEntity.setState(0); // 启用 状态：0 可用 ，1 不可用
					cardEntity.setId(bill.getCardId()); // 停车卡id
					parkingCardService.updateCardState(cardEntity);
					// 重新生成新的账单
					ParkingBillEntity billEntity2 = new ParkingBillEntity();
					billEntity2.setCardId(bill.getCardId());
					billEntity2.setParkingId(bill.getParkingId());
					billEntity2.setParkingName(bill.getParkingName());
					billEntity2.setPhone(bill.getPhone());
					billUtils.generateBill(billEntity2);
					return new JsonResult(recv);
				}

		}else if (bill.getFlag() == 1) {
			return new JsonResult(new ServiceException("该账单已缴费"));
		} else if (bill.getFlag() == 2) {
			return new JsonResult(new ServiceException("该账单未出账"));
		}
		return jsonResult;
	}

	@RequestMapping("/isParkingLotFull")
	@ResponseBody
	@Override
	public JsonResult isParkingLotFull(@RequestBody ParkingBillEntity billEntity) {
		JsonResult jsonResult = new JsonResult();
		ParkingBillEntity bill = parkingBillSerivice.selectParkingBillByBillNum(billEntity.getBillNum());
		ParkingLotEntity parkingLot = parkingLotService.selectParkingLotById(bill.getParkingId());
		//判断停车场是否已满
		if (parkingCardService.selectCards(parkingLot.getId()) >= parkingLotService.selectParkingLotById(parkingLot.getId()).getTotal()) {
			jsonResult = new JsonResult("该停车场已满");
			return jsonResult;
		}else {
			return jsonResult;
		}
	}

	@RequestMapping("/payBillByOperator")
	@ResponseBody
	@Override
	public JsonResult payBillByOperator(@RequestBody ParkingBillEntity billEntity) {
		JsonResult jsonResult = new JsonResult();
		//通过账单编号查询账单
		ParkingBillEntity bill = parkingBillSerivice.selectParkingBillByBillNum(billEntity.getBillNum());
		if(bill == null) {
			return new JsonResult(new ServiceException("该账单不存在"));
		}
		// 0 未缴费，1 已缴费 2未出账 3 逾期欠费
		if(bill.getFlag() == 0) {  //正常未缴费
			//缴费成功
			billEntity.setFlag(1);
			billEntity.setId(bill.getId());
			billEntity.setTis("");
			parkingBillSerivice.updateBillInfo(billEntity);
			return jsonResult;
		}else if(bill.getFlag() == 3) {//逾期缴费
			
			ParkingLotEntity parkingLot = parkingLotService.selectParkingLotById(bill.getParkingId());
			//判断停车场是否已满
			if (parkingCardService.selectCards(parkingLot.getId()) >= parkingLotService.selectParkingLotById(parkingLot.getId()).getTotal()) {
				return new JsonResult(new ServiceException("该停车场已满，不能缴费激活该停车卡"));
			}else {
				//缴费成功
				billEntity.setFlag(1);
				billEntity.setId(bill.getId());
				billEntity.setTis("");
				parkingBillSerivice.updateBillInfo(billEntity);
				//判断逾期缴费的账单是否全部付清
				List<ParkingBillEntity> bills = parkingBillSerivice.selectBillByCardIdAndFlagForList(bill.getCardId(), 3);  //flag = 3
				if(bills.size()!=0) {
					return jsonResult;
				}				
				// 更改用户停车卡的状态，重新启用
				ParkingCardEntity cardEntity = new ParkingCardEntity();
				cardEntity.setState(0); // 启用 状态：0 可用 ，1 不可用
				cardEntity.setId(bill.getCardId()); // 停车卡id
				parkingCardService.updateCardState(cardEntity);
				// 重新生成新的账单
				ParkingBillEntity billEntity2 = new ParkingBillEntity();
				billEntity2.setCardId(bill.getCardId());
				billEntity2.setParkingId(bill.getParkingId());
				billEntity2.setParkingName(bill.getParkingName());
				billEntity2.setPhone(bill.getPhone());
				billUtils.generateBill(billEntity2);
				return jsonResult;
			}
		}else if(bill.getFlag() == 1) {  //已缴费
			return new JsonResult(new ServiceException("该账单已缴费"));
		}else if (bill.getFlag() == 2) {  //未出账
			return new JsonResult(new ServiceException("该账单未出账"));
		}
		return jsonResult;
	}

	
	
	
	
}
