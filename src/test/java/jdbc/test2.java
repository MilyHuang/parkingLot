package jdbc;
/**
@Description:
@version:1.0
@author:MilyHuang
@Date:May 11, 20181:47:07 PM
@Email:Mily-ML.Huang@aia.com

*/

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.parkinglot.admin.entity.AdminEntity;
import com.parkinglot.admin.service.IAdminUserService;

public class test2 {

	private ApplicationContext ac = null;
	
	@Resource
	private IAdminUserService userService;
	
	
	@Before
	public void init() {
		ac = new ClassPathXmlApplicationContext("spring-mybatis.xml");  
        userService = (IAdminUserService) ac.getBean("com.parkinglot.admin.service.impl.AdminUserServiceImpl",IAdminUserService.class); 
	}
	
	@Test
	public void test() {
		AdminEntity entity = new AdminEntity();
		entity.setUsername("admin2");
		entity.setPassword("admin");
		entity.setRole(0);  //管理员
		System.out.println(entity);
		userService.insertAdminUser(entity);
		System.out.println("...");
	}
	
	@Test
	public void test2() {
		AdminEntity entity = new AdminEntity();
		entity.setUsername("admin");
		entity.setPassword("admin");
		entity.setRole(0);
		AdminEntity user = userService.selectUserByLogin(entity);
		System.out.println(user);
	}
}
