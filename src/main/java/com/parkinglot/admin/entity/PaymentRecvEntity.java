package com.parkinglot.admin.entity;

import java.io.Serializable;

/**
@Description:第三方银行接口返回的参数
@version:1.0
@author:MilyHuang
@Date:Jun 13, 2018  4:24:00 PM
@Email:Mily-ML.Huang@aia.com

*/
public class PaymentRecvEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer redeemResultCode;

	public Integer getRedeemResultCode() {
		return redeemResultCode;
	}

	public void setRedeemResultCode(Integer redeemResultCode) {
		this.redeemResultCode = redeemResultCode;
	}

	@Override
	public String toString() {
		return "PaymentRecvEntity [redeemResultCode=" + redeemResultCode + "]";
	}
	
	
}
