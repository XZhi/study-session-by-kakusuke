/**
 * 
 */
package com.ss4o.core.model;

import com.ss4o.core.exception.ValidateException;

/**
 * 値チェッククラス用インターフェース
 * 
 * @author kakusuke
 * 
 */
public interface Validatable {
	/**
	 * 値チェック
	 * 
	 * @throws ValidateException
	 *             値チェックエラー
	 */
	void validate() throws ValidateException;
}
