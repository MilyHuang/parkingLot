package com.parkinglot.admin.entity;

import java.io.Serializable;

/**
@Description:
@version:1.0
@author:MilyHuang
@Date:May 10, 20183:04:22 PM
@Email:Mily-ML.Huang@aia.com

*/
public class AdminEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**系统ID*/
	private Integer id;
	/**用户名*/
	private String username;
	/**登录密码*/
	private String password;
	/**用户角色   0 admin , 1 manager ,2 operator */
	private Integer role;
	
	
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Integer getRole() {
		return role;
	}
	public void setRole(Integer role) {
		this.role = role;
	}
	@Override
	public String toString() {
		return "AdminEntity [id=" + id + ", username=" + username + ", password=" + password + ", role="
				+ role + "]";
	}
	
	
	
	
	
}
