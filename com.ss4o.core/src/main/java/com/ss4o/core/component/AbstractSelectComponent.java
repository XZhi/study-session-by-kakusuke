package com.ss4o.core.component;

import com.ss4o.core.model.BaseObject;

/**
 * 検索用部品抽象クラス
 * 
 * @author kakusuke
 * 
 * @param <ValueObject>
 *            検索結果ValueObjectクラス
 * @param <ConditionClass>
 *            検索条件クラス
 */
abstract public class AbstractSelectComponent<ValueObject extends BaseObject, ConditionClass> implements SelectComponent<ValueObject, ConditionClass> {
	public boolean isExist(ConditionClass condition) {
		return !doSelect(condition).isEmpty();
	}

	public int getCount(ConditionClass condition) {
		return doSelect(condition).size();
	}
}
