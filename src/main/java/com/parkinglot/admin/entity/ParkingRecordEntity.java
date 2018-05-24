package com.parkinglot.admin.entity;

import java.io.Serializable;
import java.util.Date;

/**
@Description: 停车取车记录实体类
@version:1.0
@author:MilyHuang
@Date:May 24, 201810:54:02 AM
@Email:Mily-ML.Huang@aia.com

*/
public class ParkingRecordEntity implements Serializable{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	/**系统id*/
	private Integer id;
	/**用户手机号*/
	private String phone;
	/**停车场编号*/
	private String parkingNum;
	/**停车卡编号*/
	private String cardNum;
	/**停车时间*/
	private Date checkinTime;
	/**取车时间*/
	private Date checkoutTime;
	/**停车取车标志  0停车，1取车*/
	private Integer flag;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getParkingNum() {
		return parkingNum;
	}
	public void setParkingNum(String parkingNum) {
		this.parkingNum = parkingNum;
	}
	public String getCardNum() {
		return cardNum;
	}
	public void setCardNum(String cardNum) {
		this.cardNum = cardNum;
	}
	public Date getCheckinTime() {
		return checkinTime;
	}
	public void setCheckinTime(Date checkinTime) {
		this.checkinTime = checkinTime;
	}
	public Date getCheckoutTime() {
		return checkoutTime;
	}
	public void setCheckoutTime(Date checkoutTime) {
		this.checkoutTime = checkoutTime;
	}
	public Integer getFlag() {
		return flag;
	}
	public void setFlag(Integer flag) {
		this.flag = flag;
	}
	@Override
	public String toString() {
		return "ParkingRecordEntity [id=" + id + ", phone=" + phone + ", parkingNum=" + parkingNum + ", cardNum="
				+ cardNum + ", checkinTime=" + checkinTime + ", checkoutTime=" + checkoutTime + ", flag=" + flag + "]";
	}
	
	
	
}
