/**
 * 
 */
package com.ss4o.nucleus.sca.maven.plugin;

import org.springframework.beans.factory.config.BeanDefinition;

import com.ss4o.nucleus.sca.maven.plugin.ClassScanner.Visitor;

/**
 * @author tadasuke-win7
 *
 */
public class JavaFileGenerator implements Visitor {
	// TODO Implementation
	/* (non-Javadoc)
	 * @see com.ss4o.nucleus.sca.maven.plugin.ClassScanner.Visitor#initialize()
	 */
	@Override
	public void initialize() {

	}

	/* (non-Javadoc)
	 * @see com.ss4o.nucleus.sca.maven.plugin.ClassScanner.Visitor#check(org.springframework.beans.factory.config.BeanDefinition)
	 */
	@Override
	public boolean check(BeanDefinition beanDefinition) {
		return false;
	}

	/* (non-Javadoc)
	 * @see com.ss4o.nucleus.sca.maven.plugin.ClassScanner.Visitor#visit(org.springframework.beans.factory.config.BeanDefinition)
	 */
	@Override
	public void visit(BeanDefinition beanDefinition) {

	}

	/* (non-Javadoc)
	 * @see com.ss4o.nucleus.sca.maven.plugin.ClassScanner.Visitor#terminate()
	 */
	@Override
	public void terminate() {

	}

}
