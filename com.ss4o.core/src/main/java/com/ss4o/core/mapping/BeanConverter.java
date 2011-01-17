package com.ss4o.core.mapping;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import com.ss4o.core.exception.SystemException;


/**
 * java beanの変換用クラス
 * 
 * @author kakusuke
 * 
 */
public class BeanConverter<T1, T2> {
	private static final String MSG_CANNOT_CONVERT = "変換できませんでした。";
	private Class<T1> clazz1;
	private Class<T2> clazz2;
	private Map<String, String> map12 = new HashMap<String, String>();
	private Map<String, String> map21 = new HashMap<String, String>();
	private Map<String, Method> map1 = new HashMap<String, Method>();
	private Map<String, Method> map2 = new HashMap<String, Method>();

	/**
	 * @param clazz1
	 * @param clazz2
	 */
	public BeanConverter(Class<T1> clazz1, Class<T2> clazz2) {
		this.clazz1 = clazz1;
		this.clazz2 = clazz2;
		loadMap();
	}

	/**
	 * @param object1
	 * @param object2
	 */
	@SuppressWarnings("unchecked")
	public BeanConverter(T1 object1, T2 object2) {
		this.clazz1 = (Class<T1>) object1.getClass();
		this.clazz2 = (Class<T2>) object2.getClass();
		loadMap();
	}

	/**
	 * 正変換処理です。
	 * 
	 * @param object1
	 *            変換元bean
	 * @return 変換後bean
	 */
	public T2 convert(T1 object1) {
		if (null == object1)
			return null;
		T2 object2 = getInstance2();
		for (String m1 : map12.keySet()) {
			try {
				map2.get(map12.get(m1)).invoke(object2, map1.get(m1));
			} catch (IllegalArgumentException e) {
				throw new SystemException(MSG_CANNOT_CONVERT, e);
			} catch (IllegalAccessException e) {
				throw new SystemException(MSG_CANNOT_CONVERT, e);
			} catch (InvocationTargetException e) {
				throw new SystemException(MSG_CANNOT_CONVERT, e);
			}
		}
		return object2;
	}

	/**
	 * 逆変換処理です。
	 * 
	 * @param object2
	 *            変換元bean
	 * @return 変換後bean
	 */
	public T1 revert(T2 object2) {
		if (null == object2)
			return null;
		T1 object1 = getInstance1();
		for (String methodName2 : map21.keySet()) {
			try {
				map1.get(map21.get(methodName2)).invoke(object1, map2.get(methodName2));
			} catch (IllegalArgumentException e) {
				throw new SystemException(MSG_CANNOT_CONVERT, e);
			} catch (IllegalAccessException e) {
				throw new SystemException(MSG_CANNOT_CONVERT, e);
			} catch (InvocationTargetException e) {
				throw new SystemException(MSG_CANNOT_CONVERT, e);
			}
		}
		return object1;
	}

	private T1 getInstance1() {
		T1 object1 = null;
		try {
			object1 = clazz1.newInstance();
		} catch (InstantiationException e) {
			throw new SystemException(MSG_CANNOT_CONVERT, e);
		} catch (IllegalAccessException e) {
			throw new SystemException(MSG_CANNOT_CONVERT, e);
		}
		return object1;
	}

	private T2 getInstance2() {
		T2 object2 = null;
		try {
			object2 = clazz2.newInstance();
		} catch (InstantiationException e) {
			throw new SystemException(MSG_CANNOT_CONVERT, e);
		} catch (IllegalAccessException e) {
			throw new SystemException(MSG_CANNOT_CONVERT, e);
		}
		return object2;
	}

	private void loadMap() {
		Map<String, Method> setter1 = new HashMap<String, Method>();
		Map<String, Method> getter1 = new HashMap<String, Method>();
		for (Method method : clazz1.getMethods()) {
			if (method.isAccessible() && Void.class.equals(method.getReturnType()) && method.getName().startsWith("set") && (method.getParameterTypes() != null && method.getParameterTypes().length == 1)) {
				// void&引数一つ→setter？
				setter1.put(method.getName(), method);
			} else if (method.isAccessible() && !Void.class.equals(method.getReturnType()) && (method.getName().startsWith("get") || method.getName().startsWith("is")) && (method.getParameterTypes() == null || method.getParameterTypes().length == 0)) {
				// void以外&引数０→getter？
				getter1.put(method.getName(), method);
			}
		}

		Map<String, Method> setter2 = new HashMap<String, Method>();
		Map<String, Method> getter2 = new HashMap<String, Method>();
		for (Method method : clazz2.getMethods()) {
			if (method.isAccessible() && Void.class.equals(method.getReturnType()) && method.getName().startsWith("set") && (method.getParameterTypes() != null && method.getParameterTypes().length == 1)) {
				// void&引数一つ→setter？
				setter2.put(method.getName(), method);
			} else if (method.isAccessible() && !Void.class.equals(method.getReturnType()) && (method.getName().startsWith("get") || method.getName().startsWith("is")) && (method.getParameterTypes() == null || method.getParameterTypes().length == 0)) {
				// void以外&引数０→getter？
				getter2.put(method.getName(), method);
			}
		}

		for (String m1 : getter1.keySet()) {
			String m2 = null;
			if (setter2.containsKey(m1.replaceAll("^get", "set"))) {
				m2 = m1.replaceAll("^get", "set");
			} else if (setter2.containsKey(m1.replaceAll("^is", "set"))) {
				m2 = m1.replaceAll("^is", "set");
			}
			if (m2 != null) {
				Method method1 = getter1.get(m1);
				Method method2 = setter2.get(m2);
				if (method1.getReturnType().isInstance(method2.getParameterTypes()[0])) {
					map12.put(m1, m2);
					map1.put(m1, method1);
					map2.put(m2, method2);
				}
			}
		}

		for (String m2 : getter2.keySet()) {
			String m1 = null;
			if (setter1.containsKey(m2.replaceAll("^get", "set"))) {
				m1 = m2.replaceAll("^get", "set");
			} else if (setter1.containsKey(m2.replaceAll("^is", "set"))) {
				m1 = m2.replaceAll("^is", "set");
			}
			if (m1 != null) {
				Method method1 = setter1.get(m1);
				Method method2 = getter2.get(m2);
				if (method2.getReturnType().isInstance(method1.getParameterTypes()[0])) {
					map21.put(m2, m1);
					map1.put(m1, method1);
					map2.put(m2, method2);
				}
			}
		}
	}
}
