package com.parkinglot.admin.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class UsageOfParkingCard {

	// 停车场id
	private Integer id;

	// 查询开始时间
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") 
	private Date firstDate;
	// 查询最后时间
		private Date lastDate;
	// 卡号
	private Integer cardId;
	// 卡使用次数
	private Integer count;
   //卡号
	private String cardNum;
	public String getCardNum() {
		return cardNum;
	}

	public void setCardNum(String cardNum) {
		this.cardNum = cardNum;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}


	public Integer getCardId() {
		return cardId;
	}

	public void setCardId(Integer cardId) {
		this.cardId = cardId;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	
	public Date getFirstDate() {
		return firstDate;
	}

	public void setFirstDate(Date firstDate) {
		this.firstDate = firstDate;
	}

	public Date getLastDate() {
		return lastDate;
	}

	public void setLastDate(Date lastDate) {
		this.lastDate = lastDate;
	}

	@Override
	public String toString() {
		return "UsageOfParkingCard [id=" + id + ", firstDate=" + firstDate + ", lastDate=" + lastDate + ", cardId="
				+ cardId + ", count=" + count + "]";
	}


}
