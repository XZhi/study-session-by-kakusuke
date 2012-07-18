/**
 * 
 */
package com.ss4o.nucleus.core;

import org.springframework.context.ApplicationContext;

/**
 * @author tadasuke-win7
 *
 */
public abstract class AbstractNucleusSCAProxy<T> {
	private static ApplicationContext applicationContext;
	public static void setApplicationContext(ApplicationContext applicationContext) {
		AbstractNucleusSCAProxy.applicationContext = applicationContext;
	}

	@SuppressWarnings("unchecked")
	protected T getBean() {
		return (T)applicationContext.getBean(this.getClass());
	}
}
