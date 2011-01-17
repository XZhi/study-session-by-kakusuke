package com.ss4o.core.component;

import com.ss4o.core.model.BaseObject;

/**
 * １件検索用部品
 * 
 * @author kakusuke
 * 
 * @param <ValueObject>
 *            ValueObjectクラス
 */
public interface FindComponent<ValueObject extends BaseObject> {
	/**
	 * PKにて検索します。<br>
	 * 見つからない場合は例外をthrowします。
	 * 
	 * @param values
	 *            PKに値がセットされたValueObject
	 */
	void doFind(ValueObject... values);

	/**
	 * PKにて検索し、ロックします。<br>
	 * 見つからない場合は例外をthrowします。
	 * 
	 * @param values
	 *            PKに値がセットされたValueObject
	 */
	void doLock(ValueObject... values);

	/**
	 * PKにて検索し、ロックします。<br>
	 * 見つからない場合も処理を続けます。
	 * 
	 * @param values
	 *            PKに値がセットされたValueObject
	 */
	void doLockIgnoreNotFound(ValueObject... values);
}
