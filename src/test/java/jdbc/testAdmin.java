package jdbc;
/**
@Description:
@version:1.0
@author:MilyHuang
@Date:May 11, 201810:56:20 AM
@Email:Mily-ML.Huang@aia.com

*/


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.parkinglot.admin.entity.AdminEntity;
import com.parkinglot.admin.service.IAdminUserService;

@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration(locations = {"classpath:/spring-mybatis.xml"})
public class testAdmin {

	@Autowired
	@Qualifier("com.parkinglot.admin.service.impl.AdminUserServiceImpl")
	private IAdminUserService adminService;
	
	@Test
	public void test1() {
		
		
		AdminEntity entity = new AdminEntity();
		entity.setUsername("admin");
		entity.setPassword("admin");
		entity.setRole(0);  //管理员
		System.out.println(entity);
		adminService.insertAdminUser(entity);
		System.out.println("...");
	}
}
