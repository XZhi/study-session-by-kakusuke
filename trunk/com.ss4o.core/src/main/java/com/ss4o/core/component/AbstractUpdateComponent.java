package com.ss4o.core.component;

import java.util.ArrayList;
import java.util.List;

import com.ss4o.core.exception.BusinessException;
import com.ss4o.core.model.BaseObject;
import com.ss4o.core.model.Pair;


/**
 * 更新部品抽象クラス
 * 
 * @author kakusuke
 * 
 * @param <ValueObject>
 *            ValueObjectクラス
 */
abstract public class AbstractUpdateComponent<ValueObject extends BaseObject> implements UpdateComponent<ValueObject>, FindComponent<ValueObject> {

	@SuppressWarnings("unchecked")
	@Override
	public void doCheck(Pair<ValueObject, ValueObject>... values) {
		List<ValueObject> list1 = new ArrayList<ValueObject>();
		List<ValueObject> list2 = new ArrayList<ValueObject>();
		for (Pair<ValueObject, ValueObject> value : values) {
			if (null != value.getValue1()) {
				list1.add(value.getValue1());
				if (null != value.getValue2()) {
					if (!value.getValue1().getId().equals(value.getValue2().getId())) {
						throw new BusinessException("");
					}
				}
			} else if (null != value.getValue2()) {
				list2.add(value.getValue2());
			}
		}

		this.doLock((ValueObject[]) list1.toArray());
		this.doLockIgnoreNotFound((ValueObject[]) list2.toArray());
	}
}
