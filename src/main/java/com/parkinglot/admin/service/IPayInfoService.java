package com.parkinglot.admin.service;
/**
@Description:
@version:1.0
@author:MilyHuang
@Date:Jun 11, 20183:43:51 PM
@Email:Mily-ML.Huang@aia.com

*/

import com.parkinglot.admin.entity.PayInfoEntity;
import com.parkinglot.common.util.JsonResult;

public interface IPayInfoService {
	
	/**
	 * 返回支付信息
	 * @param entity
	 * @return
	 */
	JsonResult insertPayInfo(PayInfoEntity entity);
	
	/**
	 * 通过账单Id查询支付信息
	 * @param billId
	 * @return
	 */
	PayInfoEntity selectPayInfoByBillId(Integer billId);
	
	/**
	 * 通过Id查询支付信息
	 * @param id
	 * @return
	 */
	PayInfoEntity selectPayInfoById(Integer id);
}
