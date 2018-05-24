package com.parkinglot.admin.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class ParkingPriceReportEntity {
	private Integer id;
	private String parkingNum;
	private Double price;
	@DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
	private Date datetime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getParkingNum() {
		return parkingNum;
	}

	public void setParkingNum(String parkingNum) {
		this.parkingNum = parkingNum;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Date getDatetime() {
		return datetime;
	}

	
	public void setDatetime(Date datetime) {
		this.datetime = datetime;
	}

	@Override
	public String toString() {
		return "ParkingPriceReportEntity [id=" + id + ", parkingNum=" + parkingNum + ", price=" + price + ", datetime="
				+ datetime + "]";
	}

	public ParkingPriceReportEntity(Integer id, String parkingNum, Double price, Date datetime) {
		this.id = id;
		this.parkingNum = parkingNum;
		this.price = price;
		this.datetime = datetime;
	}

	public ParkingPriceReportEntity() {
	}

}
