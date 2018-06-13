package com.parkinglot.common.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import org.aspectj.util.LangUtil.ProcessController.Thrown;
import org.springframework.beans.factory.annotation.Value;

import com.parkinglot.common.service.ServiceException;

/**
@Description: 调用第三方接口
@version:1.0
@author:MilyHuang
@Date:Jun 13, 2018 2:12:04 PM
@Email:Mily-ML.Huang@aia.com

*/
public class HttpURLConnectionUtils {
	
	
	/***
	 * 测试调用第三方银行接口
	 * @param path  路径
	 * @param data  参数
	 * @return
	 */
	public static String sendPost(String path,String data) {
		StringBuffer sb = new StringBuffer();
		try {
			/*
			    post content example
				{"paymentCode":1234, "payeeCode":"simParkingCompany", "payeePassword":"1234", "payerPhone":"13333333333","amount":400}
				
				result example:
				{"redeemResultCode":"0"}

			 */
			/**
			 *  TODO  
			 *  第三方接口连接处理
			 */
			
			String str = "{\"redeemResultCode\":\"0\"}";
			sb.append(str);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("连接异常");
		}
		return sb.toString();
	}
	
	
	
	/**
	 * 调用第三方接口
	 */
	 public static String sentPostUtil(String path,String data) {
		 	StringBuffer sb = new StringBuffer();
	        try {
	            URL url = new URL(path);
	            //打开和url之间的连接
	            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	            OutputStreamWriter out = null;
	            //请求方式
               conn.setRequestMethod("POST");
	           //设置通用的请求属性
	            conn.setRequestProperty("accept", "*/*");
	            conn.setRequestProperty("connection", "Keep-Alive");
	            conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)"); 
	            conn.setRequestProperty("Content-Type", "application/json");
	            //设置是否向httpUrlConnection输出，设置是否从httpUrlConnection读入，此外发送post请求必须设置这两个
	            conn.setDoOutput(true);
	            conn.setDoInput(true);
	            //获取URLConnection对象对应的输出流
	            out = new OutputStreamWriter(conn.getOutputStream(), "UTF-8");
	            //发送请求参数即数据
	            out.write(data);
	            //缓冲数据
	            out.flush();
	            //获取URLConnection对象对应的输入流
	            InputStream is = conn.getInputStream();
	            //构造一个字符流缓存
	            BufferedReader br = new BufferedReader(new InputStreamReader(is));
	            String str = "";
	            while ((str = br.readLine()) != null) {
	               sb.append(str);
	            }
	            //关闭流
	            is.close();
	            //断开连接
	            conn.disconnect();
	        } catch (Exception e) {
	            e.printStackTrace();
	            throw new ServiceException("连接异常");
	        }
	        return sb.toString();
	    }
	 
	
}
