package com.parkinglot.admin.entity;

import java.io.Serializable;

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
	private Integer usersId;
	/**停车卡号*/
	private String cardNum;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getUsersId() {
		return usersId;
	}
	public void setUsersId(Integer usersId) {
		this.usersId = usersId;
	}
	public String getCardNum() {
		return cardNum;
	}
	public void setCardNum(String cardNum) {
		this.cardNum = cardNum;
	}
	@Override
	public String toString() {
		return "ParkingCardEntity [id=" + id + ", usersId=" + usersId + ", cardNum=" + cardNum + "]";
	}
	
	
	
}
