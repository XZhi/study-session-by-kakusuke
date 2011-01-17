package com.ss4o.core.security;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanNameGenerator;
import org.springframework.util.ClassUtils;

/**
 * @author kakusuke
 *
 */
public class UserAttributeLoaderGenerator implements BeanNameGenerator {

	/* (非 Javadoc)
	 * @see org.springframework.beans.factory.support.BeanNameGenerator#generateBeanName(org.springframework.beans.factory.config.BeanDefinition, org.springframework.beans.factory.support.BeanDefinitionRegistry)
	 */
	@Override
	public String generateBeanName(BeanDefinition definition, BeanDefinitionRegistry registry) {
		String className = definition.getBeanClassName();
		String beanName = ClassUtils.getShortName(className);

		try {
			Class<?> clazz = Class.forName(className);
			if (clazz.isInstance(UserAttributeLoader.class)) {
				UserAttributeLoaderManager.addLoader(beanName);
				return beanName;
			}
		} catch (ClassNotFoundException e) {
		}
		return className;
	}

}
