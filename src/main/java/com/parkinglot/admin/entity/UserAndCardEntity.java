package com.parkinglot.admin.entity;
/**
@Description:
@version:1.0
@author:MilyHuang
@Date:May 23, 201811:37:00 AM
@Email:Mily-ML.Huang@aia.com

*/
public class UserAndCardEntity {
	/**用户名*/
	private String username;
	/**电话号码*/
	private String phone;
	/**登录密码*/
	private String password;
	/**停车场编号*/
	private String parkingNum;
	/**停车卡号*/
	private String cardNum;
	/**用户ID*/
	private Integer userId;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
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
	
	
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	@Override
	public String toString() {
		return "UserAndCardEntity [username=" + username + ", phone=" + phone + ", password=" + password
				+ ", parkingNum=" + parkingNum + ", cardNum=" + cardNum + ", userId=" + userId + "]";
	}
	
	
}
