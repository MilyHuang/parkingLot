package com.parkinglot.admin.entity;

import java.io.Serializable;
import java.util.Date;

/**
@Description:停车卡实体类
@version:1.0
@author:MilyHuang
@Date:May 21, 20183:13:03 PM
@Email:Mily-ML.Huang@aia.com

*/
public class ParkingCardEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**系统ID*/
	private Integer id;
	/**用户信息表ID*/
	private Integer userId;
	/**停车场编号*/
	private String parkingNum;
	/**停车场ID*/
	private Integer parkingId;
	/**停车卡号*/
	private String cardNum;
	/**创建时间*/
	private Date createdTime;
	/**卡的状态 0可用，1不可用*/
	private Integer state ;
	/**0为未停车，1为已停车*/
	private Integer flag;
	public Integer getFlag() {
		return flag;
	}
	public void setFlag(Integer flag) {
		this.flag = flag;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getCardNum() {
		return cardNum;
	}
	public void setCardNum(String cardNum) {
		this.cardNum = cardNum;
	}
	
	public String getParkingNum() {
		return parkingNum;
	}
	public void setParkingNum(String parkingNum) {
		this.parkingNum = parkingNum;
	}
	
	
	public Date getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	
	
	public Integer getParkingId() {
		return parkingId;
	}
	public void setParkingId(Integer parkingId) {
		this.parkingId = parkingId;
	}
	@Override
	public String toString() {
		return "ParkingCardEntity [id=" + id + ", userId=" + userId + ", parkingNum=" + parkingNum + ", parkingId="
				+ parkingId + ", cardNum=" + cardNum + ", createdTime=" + createdTime + ", state=" + state + ", flag="
				+ flag + "]";
	}
	
	
	
}
