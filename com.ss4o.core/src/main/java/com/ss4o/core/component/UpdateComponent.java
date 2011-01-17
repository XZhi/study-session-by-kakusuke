package com.ss4o.core.component;

import com.ss4o.core.model.BaseObject;
import com.ss4o.core.model.Pair;

/**
 * 更新用部品
 * 
 * @author kakusuke
 * 
 * @param <ValueObject>
 *            ValueObjectクラス
 */
public interface UpdateComponent<ValueObject extends BaseObject> extends CheckComponent<Pair<ValueObject, ValueObject>> {
	/**
	 * 更新処理を実行します。
	 * 
	 * @param oldNewValues
	 *            更新前データと更新後データのペアオブジェクト
	 */
	void doUpdate(Pair<ValueObject, ValueObject>... oldNewValues);
}
