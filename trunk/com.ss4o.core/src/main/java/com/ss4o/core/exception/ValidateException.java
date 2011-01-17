package com.ss4o.core.exception;

/**
 * @author kakusuke
 * 
 */
public class ValidateException extends BusinessException {

	/** serialVersionUID */
	private static final long serialVersionUID = 5746358089924214679L;

	/**
	 * 
	 */
	public ValidateException() {
	}

	/**
	 * @param message
	 * @param cause
	 */
	public ValidateException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 */
	public ValidateException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public ValidateException(Throwable cause) {
		super(cause);
	}

}
