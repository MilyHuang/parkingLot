package com.parkinglot.common.service;
/**
@Description:  
   Service层公用的Exception.
      继承自RuntimeException, 从由Spring管理事务的函数中抛出时会触发事务回滚.
@version:1.0
@author:MilyHuang
@Date:May 11, 201811:00:12 AM
@Email:Mily-ML.Huang@aia.com

*/
public class ServiceException extends RuntimeException{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5803534010892975695L;

	public ServiceException() {
		super();
	}

	public ServiceException(String message) {
		super(message);
	}

	public ServiceException(Throwable cause) {
		super(cause);
	}

	public ServiceException(String message, Throwable cause) {
		super(message, cause);
	}
}
