package jdbc;

import org.junit.Test;

import com.alibaba.fastjson.JSONObject;

/**
@Description:
@version:1.0
@author:MilyHuang
@Date:May 18, 20183:59:40 PM
@Email:Mily-ML.Huang@aia.com

*/
public class CommonTest {
	@Test
	public void test1() {
		String string = "{\"datas\":[2,4,5,6,7,8,9,10]}";
		JSONObject jsonObject = new  JSONObject();
		Object str = jsonObject.parse(string);
		Integer[] arr = (Integer[]) jsonObject.parseObject(string).get("datas");
		System.err.println(arr);
	}
	
}
