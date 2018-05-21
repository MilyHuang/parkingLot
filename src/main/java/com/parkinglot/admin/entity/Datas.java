package com.parkinglot.admin.entity;

import java.util.Arrays;

/**
@Description:
@version:1.0
@author:MilyHuang
@Date:May 18, 20185:59:26 PM
@Email:Mily-ML.Huang@aia.com

*/
public class Datas {
	private Integer[] datas;

	public Integer[] getDatas() {
		return datas;
	}

	public void setDatas(Integer[] datas) {
		this.datas = datas;
	}

	@Override
	public String toString() {
		return "Datas [datas=" + Arrays.toString(datas) + "]";
	}
	
	
	
}
