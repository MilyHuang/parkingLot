package com.parkinglot.common.security;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
@Description:  MD5加密
@version:1.0
@author:MilyHuang
@Date:May 11, 201810:47:07 AM
@Email:Mily-ML.Huang@aia.com

*/
public class MD5Util {
	/**
	 *  md5加密
	 * @param str
	 * @return  加密后的字符串
	 */
	public static String md5(String str) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(str.getBytes());
			byte[] byteDigest = md.digest();
			int i;
			StringBuffer buf = new StringBuffer("");
			for (byte element : byteDigest) {
				i = element;
				if (i < 0) {
					i += 256;
				}
				if (i < 16) {
					buf.append("0");
				}
				buf.append(Integer.toHexString(i));
			}
			// 32位加密
			return buf.toString();
			// 16位的加密
			// return buf.toString().substring(8, 24);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	public static void main(String[] args) {
		String str = "admin";
		System.out.println(md5(str));
	}
}
