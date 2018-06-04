package com.parkinglot.admin.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class ParkingPriceReportEntity {
	private Integer id;
	private Integer parkingId;
	private Double price;
	@DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
	private Date datetime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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


	public ParkingPriceReportEntity(Integer id, Integer parkingId, Double price, Date datetime) {
		super();
		this.id = id;
		this.parkingId = parkingId;
		this.price = price;
		this.datetime = datetime;
	}

	@Override
	public String toString() {
		return "ParkingPriceReportEntity [id=" + id + ", parkingId=" + parkingId + ", price=" + price + ", datetime="
				+ datetime + "]";
	}

	public Integer getParkingId() {
		return parkingId;
	}

	public void setParkingId(Integer parkingId) {
		this.parkingId = parkingId;
	}

	public ParkingPriceReportEntity() {
	}

}
