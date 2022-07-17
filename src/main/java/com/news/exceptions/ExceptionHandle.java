package com.news.exceptions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandle {
	/**
	 * Method xử lý exception khi được ném ra ở controller
	 * @param request
	 * @param response
	 * @param e
	 * @return
	 */
	@ExceptionHandler(Exception.class)
	public String exceptionHandle(HttpServletRequest request, HttpServletResponse response, Exception e) {
		return e.getMessage();
	}
	
	/**
	 * Method xử lý business exception khi được ném ra ở controller
	 * @param request
	 * @param response
	 * @param e
	 * @return
	 */
	@ExceptionHandler(BusinessException.class) 
	public String businessHandle(HttpServletRequest request, HttpServletResponse response, BusinessException e) {
		return e.getMessage();
	}
	
	/**
	 * Method xử lý system exception khi được ném ra ở controller
	 * @param request
	 * @param response
	 * @param e
	 * @return
	 */
	@ExceptionHandler(SystemException.class) 
	public String systemHandle(HttpServletRequest request, HttpServletResponse response, BusinessException e) {
		return e.getMessage();
	}
}
