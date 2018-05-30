package com.parkinglot.admin.entity;

import java.util.Date;

public class ParkingBillEntity {
	/* 账单Id */
	private Integer id;
	/* 账单编号Id */
	private String billNum;
	/* 停车场编号 */
	private String parkingNum;
	/* 停车卡编号 */
	private String cardNum;
	/* 停车场价格 */
	private Double price;
	/* 账单纵总价 */
	private Double account;
	/* 开始计费时间 */
	private Date firstDate;
	/* 出账时间 */
	private Date statementDate;
	/* 是否缴费 */
	private Integer flag;
	/* 缴费提示 */
	private String tis;
	/* 停车场名称 */
	private String parkingName;

	/* 賬單電話 */
	private String phone;

	public String getParkingName() {
		return parkingName;
	}

	public void setParkingName(String parkingName) {
		this.parkingName = parkingName;
	}

	public ParkingBillEntity() {
	}

	@Override
	public String toString() {
		return "ParkingBillEntity [id=" + id + ", billNum=" + billNum + ", parkingNum=" + parkingNum + ", cardNum="
				+ cardNum + ", price=" + price + ", account=" + account + ", firstDate=" + firstDate
				+ ", statementDate=" + statementDate + ", flag=" + flag + ", tis=" + tis + ", parkingName="
				+ parkingName + ", phone=" + phone + "]";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public ParkingBillEntity(Integer id, String billNum, String parkingNum, String cardNum, Double price,
			Double account, Date firstDate, Date statementDate, Integer flag, String tis, String parkingName,
			String phone) {
		super();
		this.id = id;
		this.billNum = billNum;
		this.parkingNum = parkingNum;
		this.cardNum = cardNum;
		this.price = price;
		this.account = account;
		this.firstDate = firstDate;
		this.statementDate = statementDate;
		this.flag = flag;
		this.tis = tis;
		this.parkingName = parkingName;
		this.phone = phone;
	}

	public String getBillNum() {
		return billNum;
	}

	public void setBillNum(String billNum) {
		this.billNum = billNum;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
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

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Double getAccount() {
		return account;
	}

	public void setAccount(Double account) {
		this.account = account;
	}

	public Date getFirstDate() {
		return firstDate;
	}

	public void setFirstDate(Date firstDate) {
		this.firstDate = firstDate;
	}

	public Date getStatementDate() {
		return statementDate;
	}

	public void setStatementDate(Date statementDate) {
		this.statementDate = statementDate;
	}

	public Integer getFlag() {
		return flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}

	public String getTis() {
		return tis;
	}

	public void setTis(String tis) {
		this.tis = tis;
	}

}
