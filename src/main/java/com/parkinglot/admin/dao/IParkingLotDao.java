package com.parkinglot.admin.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.parkinglot.admin.entity.ParkingLotEntity;

/**
@Description:停车场管理
@version:1.0
@author:MilyHuang
@Date:May 21, 20182:04:57 PM
@Email:Mily-ML.Huang@aia.com

*/
public interface IParkingLotDao {
	/**
	 * 添加停车场
	 * @param entity
	 * @return
	 */
	int insertParkingLot(ParkingLotEntity entity);
	
	/**
	 * 查询停车场列表
	 * @return 停车场列表集合
	 */
	List<ParkingLotEntity> selectParkingLotForList();
	
	/**
	 * 通过停车场编号查询停车场是否存在
	 * @param parkingNum
	 * @return
	 */
	ParkingLotEntity selectParkingLotByNum(@Param("parkingNum") String parkingNum);
	
	/**
	 * 更新停车场价格
	 * @param entity
	 * @return
	 */
    int updateParkingLotPrice(ParkingLotEntity entity);	
    
   /**
    * 更改停车场使用情况
    * @param parkingNum  停车场编号
    * @param inuse  已使用车位
    * @return
    */
    int updateParkingLotInuse(ParkingLotEntity entity);
}
