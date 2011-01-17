/**
 * 
 */
package com.ss4o.core.utility;

import java.util.ArrayList;
import java.util.List;

/**
 * @author kakusuke
 * 
 */
public abstract class CommonUtil {
	/**
	 * 空の配列を返却します。
	 * 
	 * @param <T>
	 *            返却する配列の型
	 * @param clazz
	 *            対象の型のクラス
	 * @return 対象の型の空の配列
	 */
	@SuppressWarnings("unchecked")
	public static final <T> T[] getBlankArray(Class<T> clazz) {
		return (T[]) new Object[] {};
	}

	/**
	 * 配列をリストに変換します。
	 * 
	 * @param <T>
	 *            配列の型
	 * @param arr
	 *            配列
	 * @return 変換後リスト
	 */
	public static final <T> List<T> array2List(T[] arr) {
		List<T> list = new ArrayList<T>();
		if (arr != null) {
			for (T obj : arr) {
				list.add(obj);
			}
		}
		return list;
	}
}
