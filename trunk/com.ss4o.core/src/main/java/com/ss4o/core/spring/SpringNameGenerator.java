package com.ss4o.core.spring;

import java.io.IOException;
import java.util.Properties;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanNameGenerator;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.util.ClassUtils;

/**
 * @author kakusuke
 *
 */
public class SpringNameGenerator implements BeanNameGenerator {
	private static Properties properties;
	static {
		try {
			properties = PropertiesLoaderUtils.loadAllProperties("springbean.properties");
		} catch (IOException e) {
			properties = new Properties();
			properties.put("impl", ".*Impl$,Impl$,");
			properties.put("stub", ".*Stub$,Stub$,");
			properties.put("dao", ".*\\.impl\\..*Dao$");
		}
	}

	/* (非 Javadoc)
	 * @see org.springframework.beans.factory.support.BeanNameGenerator#generateBeanName(org.springframework.beans.factory.config.BeanDefinition, org.springframework.beans.factory.support.BeanDefinitionRegistry)
	 */
	@Override
	public String generateBeanName(BeanDefinition definition,
			BeanDefinitionRegistry registry) {
		try {
			String className = definition.getBeanClassName();
			String propertyName = ClassUtils.getShortNameAsProperty(Class.forName(className));
			for (Object key : properties.keySet()) {
				String val = properties.getProperty((String) key);
				String[] arr = val.split(",");
				if (arr.length >= 2) {
					if (propertyName.matches(arr[0])) {
						return propertyName.replaceAll(arr[1], arr[2]);
					}
				} else if (arr.length > 0) {
					if (className.matches(arr[0])) {
						return propertyName;
					}
				}
			}
			return propertyName;
		} catch (ClassNotFoundException e) {
			// No chance to reach here.
			throw new IllegalStateException("Class " + definition.getBeanClassName() + " not found.");
		}
	}

}
