package jdbc;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.parkinglot.admin.entity.ParkingCardEntity;
import com.parkinglot.admin.entity.UsersInfoEntity;
import com.parkinglot.admin.service.IParkingCardService;
import com.parkinglot.admin.service.IUsersInfoService;

/**
@Description:
@version:1.0
@author:MilyHuang
@Date:May 22, 201812:00:01 PM
@Email:Mily-ML.Huang@aia.com

*/
public class TestParkingCard {
	private ApplicationContext ac = null;
	
	@Resource
	private IParkingCardService cardService;
	
	
	@Before
	public void init() {
		ac = new ClassPathXmlApplicationContext("spring-mybatis.xml");  
		cardService = (IParkingCardService) ac.getBean("com.parkinglot.admin.service.impl.ParkingCardServiceImpl",IParkingCardService.class); 
	}
	
	/**
	 * 测试添加新用户
	 */
//	@Test
//	public void test1() {
//		ParkingCardEntity entity = new ParkingCardEntity();
//		entity.setParkingNum("001");
//		entity.setUserId(1);
//		entity.setCardNum("2018052205");
//		ParkingCardEntity card = cardService.selectParkingCardByCardNumAndParkingNum(entity.getCardNum(),entity.getParkingNum());
//		System.err.println(card);
//		cardService.insertParkingCard(entity);
//		System.err.println("添加成功");
//	}
//	
	
	@Test
	public void test() {
		ParkingCardEntity entity = new ParkingCardEntity();
		System.err.println( cardService.selectCards("002"));
	}
}
