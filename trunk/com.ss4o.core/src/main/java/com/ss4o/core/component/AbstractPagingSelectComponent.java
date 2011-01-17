package com.ss4o.core.component;

import com.ss4o.core.model.BaseObject;
import com.ss4o.core.model.RowRange;

/**
 * ページング機能つき検索用部品抽象クラス
 * 
 * @author kakusuke
 * 
 * @param <ValueObject>
 *            検索結果ValueObjectクラス
 * @param <ConditionClass>
 *            検索条件クラス
 */
public abstract class AbstractPagingSelectComponent<ValueObject extends BaseObject, ConditionClass extends RowRange> extends AbstractSelectComponent<ValueObject, ConditionClass> implements PagingSelectComponent<ValueObject, ConditionClass> {
	/* (非 Javadoc)
	 * com.ss4o.core.component.AbstractSelectComponent#isExist(java.lang.Object)
	 */
	@Override
	public boolean isExist(ConditionClass condition) {
		@SuppressWarnings("unchecked")
		ConditionClass copied = (ConditionClass) condition.createClone();
		copied.setFromRow(1);
		copied.setToRow(1);
		return super.isExist(copied);
	}
}
