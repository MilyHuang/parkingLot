package jdbc;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.parkinglot.admin.entity.UsersInfoEntity;
import com.parkinglot.admin.service.IUsersInfoService;

/**
@Description:
@version:1.0
@author:MilyHuang
@Date:May 22, 201812:00:01 PM
@Email:Mily-ML.Huang@aia.com

*/
public class TestUserInfo {
	private ApplicationContext ac = null;
	
	@Resource
	private IUsersInfoService userService;
	
	
	@Before
	public void init() {
		ac = new ClassPathXmlApplicationContext("spring-mybatis.xml");  
		userService = (IUsersInfoService) ac.getBean("com.parkinglot.admin.service.impl.UsersInfoServiceImpl",IUsersInfoService.class); 
	}
	
	/**
	 * 测试添加新用户
	 */
	@Test
	public void test1() {
		UsersInfoEntity entity = new UsersInfoEntity();
		entity.setUsername("aaa");
		entity.setPhone("13012341235");
		entity.setPassword("123456");
		UsersInfoEntity user = userService.selectUserInfoByPhone(entity.getPhone());
		if(user != null) {
			System.err.println(user);
			System.err.println("该用户已存在");
		}else {
			userService.insertUserInfo(entity);
			System.err.println(entity.getId());
			System.err.println("添加成功");
		}
	}
	
	/**
	 * 测试登录
	 */
	@Test
	public void test2() {
		
		UsersInfoEntity entity = new UsersInfoEntity();
		entity.setPhone("13012341234");
		entity.setPassword("1234561");
		UsersInfoEntity user = userService.selectUserInfoByLogin(entity);
		System.out.println(user);
	}
	
}
