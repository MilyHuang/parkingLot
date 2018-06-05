package com.parkinglot.admin.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description: 停车取车记录实体类
 * @version:1.0
 * @author:MilyHuang
 * @Date:May 24, 201810:54:02 AM
 * @Email:Mily-ML.Huang@aia.com
 * 
 */
public class ParkingRecordEntity implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	/** 系统id */
	private Integer id;
	 /**用户手机号*/
	 private String phone;
	/** 停车场编号 */
	private String parkingNum;
	/** 停车卡编号 */
	private String cardNum;
	/** 停车时间 */
	private Date checkinTime;
	/** 取车时间 */
	private Date checkoutTime;
	/** 停车取车标志 0停车，1取车 */
	private Integer flag;

	private Integer parkingId;
	
	private Integer cardId;
	
	public String getPhone() {
		return phone;
	}






	public void setPhone(String phone) {
		this.phone = phone;
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






	public Integer getId() {
		return id;
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






	@Override
	public String toString() {
		return "ParkingRecordEntity [id=" + id + ", phone=" + phone + ", parkingNum=" + parkingNum + ", cardNum="
				+ cardNum + ", checkinTime=" + checkinTime + ", checkoutTime=" + checkoutTime + ", flag=" + flag
				+ ", parkingId=" + parkingId + ", cardId=" + cardId + "]";
	}






	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public void setId(Integer id) {
		this.id = id;
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

	
	public ParkingRecordEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

}
