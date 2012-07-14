/**
 * 
 */
package com.ss4o.nucleus.sca.maven.plugin;

import java.io.File;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * @author tadasuke-win7
 * 
 */
public class DynamicClassPathLoader {
	@SuppressWarnings("rawtypes")
	private static final Class[] parameters = new Class[] { URL.class };

	public static void addClassPath(ClassLoader classLoader, String path) {
		addClassPath(classLoader, new File(path));
	}

	public static void addClassPath(ClassLoader classLoader, File path) {
		try {
			addClassPath(classLoader, path.toURI().toURL());
		} catch (MalformedURLException e) {
		}
	}

	public static void addClassPath(ClassLoader classLoader, URL path) {
		URLClassLoader urlClassLoader = (URLClassLoader) classLoader;
		try {
			Method method = URLClassLoader.class.getDeclaredMethod("addURL", parameters);
			method.setAccessible(true);
			method.invoke(urlClassLoader, new Object[] { path });
		} catch (Exception e) {
		}
	}
}
