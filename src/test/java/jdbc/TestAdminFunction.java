package jdbc;
/**
@Description:
@version:1.0
@author:MilyHuang
@Date:May 11, 20181:47:07 PM
@Email:Mily-ML.Huang@aia.com

*/

import java.util.List;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.parkinglot.admin.entity.AdminEntity;
import com.parkinglot.admin.service.IAdminUserService;
import com.parkinglot.common.util.JsonResult;

public class TestAdminFunction {

	private ApplicationContext ac = null;
	
	@Resource
	private IAdminUserService userService;
	
	
	@Before
	public void init() {
		ac = new ClassPathXmlApplicationContext("spring-mybatis.xml");  
        userService = (IAdminUserService) ac.getBean("com.parkinglot.admin.service.impl.AdminUserServiceImpl",IAdminUserService.class); 
	}
	
	/**
	 * 添加
	 */
	@Test
	public void test() {
		AdminEntity entity = new AdminEntity();
		entity.setUsername("test");
		entity.setPassword("test");
		entity.setRole(0);  //管理员
		//userService.insertAdminUser(entity);
		System.err.println(userService.insertAdminUser(entity));
	}
	
	/**
	 * 登录
	 */
	@Test
	public void test2() {
		AdminEntity entity = new AdminEntity();
		entity.setUsername("test");
		entity.setPassword("12345");
		//entity.setRole(0);
		AdminEntity user = userService.selectUserByLogin(entity);
		JsonResult jsonResult = new JsonResult(user);
		System.out.println(jsonResult);
	}
	
	@Test
	public void test3() {
		List<AdminEntity> users = userService.selectAdminForList();
		System.err.println(users);
	}
	
	/**
	 * 测试修改密码
	 */
	@Test
	public void test4() {
		AdminEntity user = userService.selectAdminUserById(1);
		System.err.println(user);
		AdminEntity entity = new AdminEntity();
		entity.setPassword("12345");
		entity.setId(3);
		userService.updatePasswordById(entity);
		System.err.println("....");
	}
	
}
