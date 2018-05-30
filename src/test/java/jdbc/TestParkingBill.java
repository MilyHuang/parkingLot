package jdbc;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.parkinglot.admin.entity.ParkingBillEntity;
import com.parkinglot.admin.entity.ParkingPriceReportEntity;
import com.parkinglot.admin.service.IParkingBillService;
import com.parkinglot.admin.service.IParkingPriceReportService;

public class TestParkingBill {
	private ApplicationContext ac = null;

	@Resource
	private IParkingBillService ps;

	@Before
	public void init() {
		ac = new ClassPathXmlApplicationContext("spring-mybatis.xml");
		ps = (IParkingBillService) ac.getBean("com.parkinglot.admin.service.impl.ParkingBillServiceImpl",
				IParkingBillService.class);
	}

	@Test
	public void test() {
	ParkingBillEntity list=	ps.selectParkingBillByBillNum("123456");
		System.out.println(list);
	}
	
	@Test
	public void test1() {
	List<ParkingBillEntity> list=	ps.selectAllParkingBillEntity("12345678902");
		System.out.println(list);
	}
}
