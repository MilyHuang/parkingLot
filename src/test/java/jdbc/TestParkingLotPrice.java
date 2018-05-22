package jdbc;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.parkinglot.admin.entity.ParkingLotPriceEntity;
import com.parkinglot.admin.service.IParkingLotPriceService;

public class TestParkingLotPrice {
private ApplicationContext ac = null;
	
	@Resource
	private IParkingLotPriceService ipp;
	
	@Before
	public void init() {
		ac = new ClassPathXmlApplicationContext("spring-mybatis.xml");  
		ipp = (IParkingLotPriceService) ac.getBean("com.parkinglot.admin.service.impl.ParkingLotPriceServiceImpl",IParkingLotPriceService.class); 
	}
	
	
	/**
	 * 测试修改车位价格
	 */
	@Test
	public void test4() {
		ParkingLotPriceEntity plpe = new ParkingLotPriceEntity();
		plpe.setMonth(5);
		plpe.setP_id(1);
		plpe.setPrice(600);
		System.out.println(ipp.updatePrice(plpe));
		
	}
	
	
}
