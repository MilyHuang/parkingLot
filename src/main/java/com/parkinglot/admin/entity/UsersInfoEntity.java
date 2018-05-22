package com.parkinglot.admin.entity;

import java.io.Serializable;

/**
@Description:用户信息实体类
@version:1.0
@author:MilyHuang
@Date:May 21, 20183:09:28 PM
@Email:Mily-ML.Huang@aia.com

*/
public class UsersInfoEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**系统ID*/
	private Integer id;
	/**用户名*/
	private String username;
	/**电话号码*/
	private String phone;
	/**登录密码*/
	private String password;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "UsersInfoEntity [id=" + id + ", username=" + username + ", phone=" + phone + ", password=" + password
				+ "]";
	}
	
	
}
