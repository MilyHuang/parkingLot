package com.parkinglot.admin.entity;

import java.io.Serializable;

/**
@Description:停车场实体类
@version:1.0
@author:MilyHuang
@Date:May 21, 20181:42:54 PM
@Email:Mily-ML.Huang@aia.com

*/
public class ParkingLotEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**系统Id*/
	private Integer id;
	/**停车场编号*/
	private String parkingNum;
	/**停车场名字*/
	private String parkingName;
	/**停车场地址*/
	private String address;
	/**停车场容量*/
	private Integer total;
	/**已使用的车位*/
	private Integer inuse;
	/**价格*/
	private Double price;
	/**租金*/
	private Double rent;
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
	public String getParkingName() {
		return parkingName;
	}
	public void setParkingName(String parkingName) {
		this.parkingName = parkingName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	public Integer getInuse() {
		return inuse;
	}
	public void setInuse(Integer inuse) {
		this.inuse = inuse;
	}
	
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Double getRent() {
		return rent;
	}
	public void setRent(Double rent) {
		this.rent = rent;
	}
	@Override
	public String toString() {
		return "ParkingLotEntity [id=" + id + ", parkingNum=" + parkingNum + ", parkingName=" + parkingName
				+ ", address=" + address + ", total=" + total + ", inuse=" + inuse + ", price=" + price + ", rent="
				+ rent + "]";
	}
	
	
}
