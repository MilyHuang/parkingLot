package com.parkinglot.admin.dao;

import org.apache.ibatis.annotations.Param;

import com.parkinglot.admin.entity.PayInfoEntity;

/**
@Description:
@version:1.0
@author:MilyHuang
@Date:Jun 11, 20182:41:31 PM
@Email:Mily-ML.Huang@aia.com

*/
public interface IPayInfoDao {
	/**
	 * 返回支付信息
	 * @param entity
	 * @return
	 */
	int insertPayInfo(PayInfoEntity entity);
	
	/**
	 * 通过账单Id查询支付信息
	 * @param billId
	 * @return
	 */
	PayInfoEntity selectPayInfoByBillId(@Param("billId") Integer billId,@Param("result") Boolean result);
	
	/**
	 * 通过id查询支付信息
	 * @param id
	 * @return
	 */
	PayInfoEntity selectPayInfoById(@Param("id") Integer id);
}
