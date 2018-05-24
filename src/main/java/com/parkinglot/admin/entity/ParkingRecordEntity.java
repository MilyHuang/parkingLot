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
	 *ffffffffffffffff
	 */
	private static final long serialVersionUID = 1L;
	/**系统id*/
	private Integer id;
	/**用户表ID*/
	private Integer userId;
	/**停车场ID*/
	private Integer parkingId;
	/**停车卡ID*/
	private Integer cardId;
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
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getParkingId() {
		return parkingId;
	}
	public void setParkingId(Integer parkingId) {
		this.parkingId = parkingId;
	}
	public Integer getCardId() {
		return cardId;
	}
	public void setCardId(Integer cardId) {
		this.cardId = cardId;
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
		return "ParkingRecordEntity [id=" + id + ", userId=" + userId + ", parkingId=" + parkingId + ", cardId="
				+ cardId + ", checkinTime=" + checkinTime + ", checkoutTime=" + checkoutTime + ", flag=" + flag + "]";
	}
	
	
	
}
