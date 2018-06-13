package com.parkinglot.admin.entity;

import java.io.Serializable;

/**
@Description:  billNum和paymentCode
@version:1.0
@author:MilyHuang
@Date:Jun 13, 2018  4:02:17 PM
@Email:Mily-ML.Huang@aia.com

*/
public class BillAndPaymentEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		
	/**账单num*/
	private String billNum;
	/**支付code*/
	private String paymentCode;
	public String getBillNum() {
		return billNum;
	}
	public void setBillNum(String billNum) {
		this.billNum = billNum;
	}
	public String getPaymentCode() {
		return paymentCode;
	}
	public void setPaymentCode(String paymentCode) {
		this.paymentCode = paymentCode;
	}
	@Override
	public String toString() {
		return "BillAndPaymentEntity [billNum=" + billNum + ", paymentCode=" + paymentCode + "]";
	}
	
	
}
