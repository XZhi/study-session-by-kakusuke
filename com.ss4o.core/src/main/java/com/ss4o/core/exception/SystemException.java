package com.ss4o.core.exception;

/**
 * @author kakusuke
 * 
 */
public class SystemException extends RuntimeException {

	/** serialVersionUID */
	private static final long serialVersionUID = 7210551876917145932L;

	/**
	 * 
	 */
	public SystemException() {
		super();
	}

	/**
	 * @param message
	 * @param cause
	 */
	public SystemException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 */
	public SystemException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public SystemException(Throwable cause) {
		super(cause);
	}

}
