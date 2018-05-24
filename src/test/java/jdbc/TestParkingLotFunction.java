package jdbc;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.parkinglot.admin.entity.ParkingLotEntity;
import com.parkinglot.admin.service.IParkingLotService;
import com.parkinglot.admin.service.IParkingPriceReportService;
import com.parkinglot.admin.service.impl.ParkingLotServiceImpl;
import com.parkinglot.common.util.JsonResult;

/**
 * @Description:
 * @version:1.0
 * @author:MilyHuang
 * @Date:May 21, 20182:35:58 PM
 * @Email:Mily-ML.Huang@aia.com
 * 
 */
public class TestParkingLotFunction {
	private ApplicationContext ac = null;

	@Resource
	private IParkingLotService parkingLotService;


	
	@Before
	public void init() {
		ac = new ClassPathXmlApplicationContext("spring-mybatis.xml");
		parkingLotService = (IParkingLotService) ac.getBean("com.parkinglot.admin.service.impl.ParkingLotServiceImpl",
				IParkingLotService.class);
	}

	/**
	 * 测试添加功能
	 */
//	@Test
//	public void test1() {
//		ParkingLotEntity entity = new ParkingLotEntity();
//		entity.setParkingNum("003");
//		entity.setParkingName("TianHePareee");
//		entity.setAddress("guangdongguangzhoutianheeee");
//		entity.setTotal(500);
//		// entity.setInuse(0);
//		// entity.setPrice(0.0);
//		entity.setRent(10000.00);
//		JsonResult jsonResult = new JsonResult();
//		jsonResult = parkingLotService.insertParkingLot(entity);
//		System.err.println(jsonResult);
//	}

	/**
	 * 测试查询停车场列表功能
	 */
	@Test
	public void test2() {
		List<ParkingLotEntity> parkings = parkingLotService.selectParkingLotForList();
		System.err.println(parkings);
	}

	@Test
	public void test3() {
		ParkingLotEntity parking = parkingLotService.selectParkingLotByNum("003");
		System.err.println(parking);
	}

	@Test
	public void test4() {
		ParkingLotEntity entity = parkingLotService.selectParkingLotByNum("007");
		System.out.println(entity);
		entity.setPrice(700.0);
		System.out.println(entity);
		parkingLotService.updateParkingLotPrice(entity);
	}
	
}
