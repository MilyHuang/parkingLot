package jdbc;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.parkinglot.admin.entity.ParkingLotEntity;
import com.parkinglot.admin.service.IParkingLotService;
import com.parkinglot.admin.service.impl.ParkingLotServiceImpl;
import com.parkinglot.common.util.JsonResult;

/**
@Description:
@version:1.0
@author:MilyHuang
@Date:May 21, 20182:35:58 PM
@Email:Mily-ML.Huang@aia.com

*/
public class TestParkingLotFunction {
	private ApplicationContext ac = null;
	
	@Resource
	private IParkingLotService parkingLotService;
	
	
	@Before
	public void init() {
		ac = new ClassPathXmlApplicationContext("spring-mybatis.xml");  
		parkingLotService = (IParkingLotService) ac.getBean("com.parkinglot.admin.service.impl.ParkingLotServiceImpl",IParkingLotService.class); 
	}
	
	/**
	 * 测试添加功能
	 */
	@Test
	public void test1() {
		ParkingLotEntity entity= new ParkingLotEntity();
		entity.setParkingNum("002");
		entity.setParkingName("yuexiuPark");
		entity.setAddress("guangdongguangzhouyuexiu");
		entity.setTotal(300);
		entity.setInuse(0);
		entity.setRent(8000.00);
		JsonResult jsonResult = new JsonResult();
		jsonResult = parkingLotService.insertParkingLot(entity);
		System.err.println(jsonResult);
	}
	
	/**
	 * 测试查询停车场列表功能
	 */
	@Test
	public void test2(){
		List<ParkingLotEntity> parkings = parkingLotService.selectParkingLotForList();
		System.err.println(parkings);
	}
	
	@Test
	public void test3() {
		ParkingLotEntity parking = parkingLotService.selectParkingLotByNum("002");
		System.err.println(parking);
	}
	
}
