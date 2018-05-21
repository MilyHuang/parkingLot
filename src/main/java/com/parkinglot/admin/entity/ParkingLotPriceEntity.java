package com.parkinglot.admin.entity;

/**
 * 停车场每月车位价格
 * @author ASNPHXH
 *
 */

public class ParkingLotPriceEntity {
	/**系统id*/
	private Integer id;
	/**月份*/
	private Integer month;
	/**车位价格*/
	private float price;
	/**停车场ID（外键）*/
	private Integer p_id;

	public ParkingLotPriceEntity(Integer id, Integer month, float price, Integer p_id) {
		this.id = id;
		this.month = month;
		this.price = price;
		this.p_id = p_id;
	}

	public Integer getP_id() {
		return p_id;
	}

	public void setP_id(Integer p_id) {
		this.p_id = p_id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getMonth() {
		return month;
	}

	public void setMonth(Integer month) {
		this.month = month;
	}

	public float getPrice() {
		return price;
	}

	public ParkingLotPriceEntity() {
	}


	@Override
	public String toString() {
		return "ParkingLotPriceEntity [id=" + id + ", month=" + month + ", price=" + price + ", p_id=" + p_id + "]";
	}

	public void setPrice(float price) {
		this.price = price;
	}
}
