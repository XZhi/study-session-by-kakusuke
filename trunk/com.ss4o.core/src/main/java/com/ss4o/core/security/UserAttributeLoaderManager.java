/**
 * 
 */
package com.ss4o.core.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.BeanFactory;

/**
 * {@link UserAttributeLoader}用管理クラス
 * 
 * @author kakusuke
 * 
 */
public class UserAttributeLoaderManager {
	private static Object lock;
	private static List<UserAttributeLoader> LOADERS = null;
	private static List<String> BEANNAMES = new ArrayList<String>();

	private BeanFactory beanFactory;

	public void setBeanFactory(BeanFactory beanFactory) {
		this.beanFactory = beanFactory;
	}

	/**
	 * 使用する{@link UserAttributeLoader}beanを追加します。
	 * 
	 * @param beanName
	 *            {@link UserAttributeLoader}bean名称
	 */
	public static void addLoader(String beanName) {
		BEANNAMES.add(beanName);
	}

	/**
	 * 使用する{@link UserAttributeLoader}のリストを取得します。
	 * 
	 * @return 使用する{@link UserAttributeLoader}のリスト
	 */
	public List<UserAttributeLoader> getUserAttributeLoaders() {
		synchronized (lock) {
			if (LOADERS == null) {
				LOADERS = new ArrayList<UserAttributeLoader>();
				for (String beanName : BEANNAMES) {
					LOADERS.add((UserAttributeLoader) beanFactory.getBean(beanName));
				}
			}
		}
		return LOADERS;
	}
}
