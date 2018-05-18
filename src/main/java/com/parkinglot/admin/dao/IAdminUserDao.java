package com.parkinglot.admin.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.parkinglot.admin.entity.AdminEntity;

/**
@Description:  添加用户信息
@version:1.0
@author:MilyHuang
@Date:May 11, 201810:11:44 AM
@Email:Mily-ML.Huang@aia.com

*/
public interface IAdminUserDao {
	/**
	 * 添加用户账号
	 * @param entity
	 * @return
	 */
	int insertAdminUser(AdminEntity entity);
	
	/**
	 * 查询用户是否存在
	 * @param username  用户名
	 * @return  用户实体类
	 */
	AdminEntity selectUserByUsrename(String username);
	
	/**
	 * 登录
	 * @param entity
	 * @return
	 */
	AdminEntity selectUserByLogin(AdminEntity entity);
	
	/**
	 * 查询用户列表
	 * @return  内部用户列表
	 */
	List<AdminEntity> selectAdminForList();
	
	/**
	 * 修改密码
	 * @param entity 
	 * @return
	 */
	int  updatePasswordById(AdminEntity entity);
	
	/**
	 * 通过Id查询用户信息
	 * @param id
	 * @return
	 */
	AdminEntity selectAdminUserById(@Param("id") Integer id);
	
	
	
}
