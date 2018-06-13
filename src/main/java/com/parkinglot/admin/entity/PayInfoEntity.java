package com.parkinglot.admin.entity;

import java.io.Serializable;
import java.util.Date;

/**
@Description:  支付网关信息
@version:1.0
@author:MilyHuang
@Date:Jun 11, 20182:29:29 PM
@Email:Mily-ML.Huang@aia.com

*/
public class PayInfoEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**系统ID*/
	private Integer id;
	/**账单Id*/
	private Integer billId;
	/**支付编号*/
	private String payNum;
	/**手机号*/
	private String phone;
	/**支付金额*/
	private Double account ;
	/**收款方code*/
	private String receiveCode;
	/**支付时间*/
	private Date payTime;
	/**返回结果  true:成功  false：失败*/
	private Boolean result;
	/**错误代码*/
	private String errorCode;
	/**错误信息*/
	private String message;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getBillId() {
		return billId;
	}
	public void setBillId(Integer billId) {
		this.billId = billId;
	}
	public String getPayNum() {
		return payNum;
	}
	public void setPayNum(String payNum) {
		this.payNum = payNum;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Double getAccount() {
		return account;
	}
	public void setAccount(Double account) {
		this.account = account;
	}
	public String getReceiveCode() {
		return receiveCode;
	}
	public void setReceiveCode(String receiveCode) {
		this.receiveCode = receiveCode;
	}
	public Date getPayTime() {
		return payTime;
	}
	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}
	public Boolean getResult() {
		return result;
	}
	public void setResult(Boolean result) {
		this.result = result;
	}
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	@Override
	public String toString() {
		return "PayBillEntity [id=" + id + ", billId=" + billId + ", payNum=" + payNum + ", phone=" + phone
				+ ", account=" + account + ", receiveCode=" + receiveCode + ", payTime=" + payTime + ", result="
				+ result + ", errorCode=" + errorCode + ", message=" + message + "]";
	}
	
	
	
}
