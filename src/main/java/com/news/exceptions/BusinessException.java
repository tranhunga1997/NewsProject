package com.news.exceptions;
/**
 * Thông báo về lỗi logic
 * @author hung
 *
 */
public class BusinessException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public BusinessException(String message) {
		super(message);
	}

}
