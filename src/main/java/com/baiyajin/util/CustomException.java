/**
 * 
 */
package com.baiyajin.util;

public class CustomException extends RuntimeException {

	/**
	 * 自定义异常
	 */
	private static final long serialVersionUID = 1L;
	private String code;
	
	public CustomException() {
		super();
	}
	
	public CustomException(String message) {
		super(message);
	}
	
	public CustomException(String message,String code) {
		super(message);
		this.code = code;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	
}
