package com.ss4o.core.component;

import com.ss4o.core.model.BaseObject;
import com.ss4o.core.model.RowRange;

/**
 * ページング機能つき検索用部品
 * 
 * @author kakusuke
 * 
 * @param <ValueObject>
 *            ValueObjectクラス
 * @param <ConditionClass>
 *            条件用ValueObjectクラス
 */
public interface PagingSelectComponent<ValueObject extends BaseObject, ConditionClass extends RowRange> extends SelectComponent<ValueObject, ConditionClass> {
}
