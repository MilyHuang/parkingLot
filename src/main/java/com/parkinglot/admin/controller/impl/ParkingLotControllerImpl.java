package com.parkinglot.admin.controller.impl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.parkinglot.admin.controller.IParkingLotController;
import com.parkinglot.admin.entity.ParkingBillEntity;
import com.parkinglot.admin.entity.ParkingCardEntity;
import com.parkinglot.admin.entity.ParkingLotEntity;
import com.parkinglot.admin.entity.ParkingPriceReportEntity;
import com.parkinglot.admin.entity.ParkingRecordEntity;
import com.parkinglot.admin.service.IParkingBillService;
import com.parkinglot.admin.service.IParkingCardService;
import com.parkinglot.admin.service.IParkingLotService;
import com.parkinglot.admin.service.IParkingPriceReportService;
import com.parkinglot.admin.service.IParkingRecordService;
import com.parkinglot.common.service.ServiceException;
import com.parkinglot.common.util.JsonResult;

/**
 * @Description:
 * @version:1.0
 * @author:MilyHuang
 * @Date:May 22, 201811:16:20 AM
 * @Email:Mily-ML.Huang@aia.com
 * 
 */
@Controller
@RequestMapping("/parkinglot")
public class ParkingLotControllerImpl implements IParkingLotController {

	@Autowired
	private IParkingLotService parkingLotService;

	@Autowired
	private IParkingPriceReportService parkingPriceReportService;

	@Autowired
	private IParkingRecordService parkingRecordService;

	@Autowired
	private IParkingCardService parkingCardService;

	@Autowired
	private IParkingBillService parkingBillService;

	private ParkingPriceReportEntity pentity = new ParkingPriceReportEntity();

	private static Logger logger = Logger.getLogger(ParkingLotControllerImpl.class);

	@RequestMapping("/selectParkinglot")
	@ResponseBody
	@Override
	public JsonResult selectParkingLotForList() {
		List<ParkingLotEntity> parkings = parkingLotService.selectParkingLotForList();
		logger.info(parkings);
		return new JsonResult(parkings);
	}

	@RequestMapping("/insertParkinglot")
	@ResponseBody
	@Override
	public JsonResult insertParkingLot(@RequestBody ParkingLotEntity entity) {
		System.out.println(entity);
		JsonResult jsonResult = new JsonResult();

		ParkingLotEntity parkingLot = parkingLotService.selectParkingLotByNum(entity.getParkingNum());

		if (parkingLot != null) {
			System.out.println(parkingLot);
			jsonResult = new JsonResult(new ServiceException("该停车场编号已存在"));
			return jsonResult;
		}
		jsonResult = parkingLotService.insertParkingLot(entity);
		return jsonResult;

	}

	@RequestMapping(value = "/updateParkingLotPrice", method = RequestMethod.POST)
	@ResponseBody
	@Override
	public JsonResult updateParkingLotPrice(@RequestBody ParkingLotEntity entity) {
		JsonResult jsonResult = new JsonResult();
		System.out.println(entity.getPrice());
		if ("".equals(entity.getPrice()) || entity.getPrice() == 0) {
			logger.info("输入价格不能为空");
			jsonResult = new JsonResult(new ServiceException("輸入的價格不能為空"));
			return jsonResult;
		}
		jsonResult = parkingLotService.updateParkingLotPrice(entity);
		List<ParkingBillEntity> list = parkingBillService.selectAllParkingBillEntityByParkingLot(entity.getId());
		for (int i = 0; i < list.size(); i++) {
			double account = entity.getPrice() / list.get(i).getPrice() * list.get(i).getAccount(); // 计算改变价格后的account的
			list.get(i).setAccount(account);
			list.get(i).setPrice(entity.getPrice());
			parkingBillService.updateOldBill(list.get(i));
		}
		return jsonResult;
	}

	@RequestMapping(value = "/deleteParkinglot", method = RequestMethod.POST)
	@ResponseBody
	@Override
	public JsonResult deleteParkingLot(@RequestBody ParkingLotEntity entity) {
		// 删除停车场
		int id = entity.getId();
		System.out.println("deleteParkingLotById" + id);
		JsonResult jsonResult = new JsonResult();
		int inuse = parkingLotService.selectInUseParkingLot(id);
		ParkingBillEntity unPayBill = parkingBillService.selectUnPayBill(id);
		ParkingCardEntity activeCard = parkingCardService.selectActiveCard(id);
		// 场内不能有车
		if (inuse != 0) {
			jsonResult = new JsonResult(new ServiceException("该停车场内有车未移出，无法删除"));
		}
		// 不能有未缴账单
		else if (unPayBill != null) {
			jsonResult = new JsonResult(new ServiceException("该停车场存在未缴费账单，无法删除"));
		}
		
		// 该停车场所有卡已被禁用
		else if (activeCard != null) {
			jsonResult = new JsonResult(new ServiceException("该停车场存在可用停车卡，无法删除"));
		}
		// 删除停车场
		else {
			parkingLotService.deleteParkingLotById(id);
		}

		return jsonResult;
	}

}
