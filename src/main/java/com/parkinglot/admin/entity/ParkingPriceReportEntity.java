package com.parkinglot.admin.entity;

import java.util.Date;

public class ParkingPriceReportEntity {
	private int id;
	private String  parkingNum;
	private Double price;
	private String datetime;

	public String getDatetime() {
		return datetime;
	}

	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}

	public ParkingPriceReportEntity() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getParkingNum() {
		return parkingNum;
	}

	public void setParkingNum(String parkingNum) {
		this.parkingNum = parkingNum;
	}

	@Override
	public String toString() {
		return "ParkingPriceReportEntity [id=" + id + ", parkingNum=" + parkingNum + ", price=" + price + ", datetime="
				+ datetime + "]";
	}

	public ParkingPriceReportEntity(int id, String parkingNum, Double price, String datetime) {
		super();
		this.id = id;
		this.parkingNum = parkingNum;
		this.price = price;
		this.datetime = datetime;
	}


	

	
}
