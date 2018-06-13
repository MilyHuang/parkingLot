package com.parkinglot.admin.entity;

import java.io.Serializable;

/**
@Description:
@version:1.0
@author:MilyHuang
@Date:Jun 13, 2018  3:55:38 PM
@Email:Mily-ML.Huang@aia.com

*/
public class PaymentEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * {"paymentCode":1234, "payeeCode":"simParkingCompany", 
	 * "payeePassword":"1234", "payerPhone":"13333333333","amount":400}
	 * */
	private String paymentCode;
	private String payeeCode;
	private String payeePassword;
	private String payerPhone;
	private Double amount;
	public String getPaymentCode() {
		return paymentCode;
	}
	public void setPaymentCode(String paymentCode) {
		this.paymentCode = paymentCode;
	}
	public String getPayeeCode() {
		return payeeCode;
	}
	public void setPayeeCode(String payeeCode) {
		this.payeeCode = payeeCode;
	}
	public String getPayeePassword() {
		return payeePassword;
	}
	public void setPayeePassword(String payeePassword) {
		this.payeePassword = payeePassword;
	}
	public String getPayerPhone() {
		return payerPhone;
	}
	public void setPayerPhone(String payerPhone) {
		this.payerPhone = payerPhone;
	}
	
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	@Override
	public String toString() {
		return "PaymentEntity [paymentCode=" + paymentCode + ", payeeCode=" + payeeCode + ", payeePassword="
				+ payeePassword + ", payerPhone=" + payerPhone + ", amount=" + amount + "]";
	}
	
	
	
}
