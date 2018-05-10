package jdbc;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class test {
	
	//@SuppressWarnings("dataSource")
	@Test
	public void test1() {
		ApplicationContext ac =null;
		
		try {
			ac = new ClassPathXmlApplicationContext("classpath:spring-mybatis.xml");
			DataSource dataSource=ac.getBean(DataSource.class);
			System.out.println(dataSource.getConnection());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
	}
}
