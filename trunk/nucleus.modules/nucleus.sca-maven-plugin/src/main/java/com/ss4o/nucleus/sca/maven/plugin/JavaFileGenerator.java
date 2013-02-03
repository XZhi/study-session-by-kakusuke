/**
 * 
 */
package com.ss4o.nucleus.sca.maven.plugin;

import java.lang.annotation.Annotation;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.core.annotation.AnnotationUtils;

import com.ss4o.nucleus.sca.maven.plugin.ClassScanner.Visitor;

/**
 * @author tadasuke-win7
 *
 */
public class JavaFileGenerator implements Visitor {
	private Class<? extends Annotation> annotationType;

	@SuppressWarnings("unchecked")
	public JavaFileGenerator(String annotationClassName) {
		try {
			annotationType = (Class<? extends Annotation>) Class.forName(annotationClassName);
		} catch (ClassNotFoundException e) {
		}
	}

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
		try {
			return AnnotationUtils.findAnnotationDeclaringClass(annotationType, Class.forName(beanDefinition.getBeanClassName())) != null;
		} catch (ClassNotFoundException e) {
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see com.ss4o.nucleus.sca.maven.plugin.ClassScanner.Visitor#visit(org.springframework.beans.factory.config.BeanDefinition)
	 */
	@Override
	public void visit(BeanDefinition beanDefinition) {
		// TODO Implementation
	}

	/* (non-Javadoc)
	 * @see com.ss4o.nucleus.sca.maven.plugin.ClassScanner.Visitor#terminate()
	 */
	@Override
	public void terminate() {

	}

}
