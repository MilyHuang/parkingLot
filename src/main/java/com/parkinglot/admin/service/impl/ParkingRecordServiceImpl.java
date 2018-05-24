package com.parkinglot.admin.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.parkinglot.admin.dao.IParkingRecordDao;
import com.parkinglot.admin.service.IParkingRecordService;

/**
@Description:
@version:1.0
@author:MilyHuang
@Date:May 24, 201811:18:32 AM
@Email:Mily-ML.Huang@aia.com

*/
@Service
public class ParkingRecordServiceImpl implements IParkingRecordService{
	
	@Autowired
	private IParkingRecordDao recodeDao;

}
