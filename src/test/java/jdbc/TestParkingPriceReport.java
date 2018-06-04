package jdbc;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.parkinglot.admin.entity.ParkingPriceReportEntity;
import com.parkinglot.admin.service.IParkingPriceReportService;

public class TestParkingPriceReport {
	private ApplicationContext ac = null;

	@Resource
	private IParkingPriceReportService ps;

	@Before
	public void init() {
		ac = new ClassPathXmlApplicationContext("spring-mybatis.xml");
		ps = (IParkingPriceReportService) ac.getBean("com.parkinglot.admin.service.impl.ParkingPriceReportServiceImpl",
				IParkingPriceReportService.class);
	}

	/**
	 * 添加
	 */
//	@Test
//	public void test() {
//		ParkingPriceReportEntity entity = new ParkingPriceReportEntity();
//		entity.setDatetime(new Date());
//		entity.setParkingNum("001");
//		entity.setPrice(800.0);
//		ps.insertParkingPriceReport(entity);
//	}
}
