/**
 * 
 */
package com.ss4o.nucleus.core;

import org.springframework.context.ApplicationContext;

/**
 * @author tadasuke-win7
 *
 */
public abstract class NucleusContext {
	private static ApplicationContext applicationContext;

	public static void setApplicationContext(ApplicationContext applicationContext) {
		NucleusContext.applicationContext = applicationContext;
	}

	protected static ApplicationContext getApplicationContext() {
		return applicationContext;
	}
	
	protected static RuntimeException transformException(Exception ex) {
		//TODO Implementation
		return new RuntimeException(ex);
	}
}
