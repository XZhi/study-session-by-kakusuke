package com.ss4o.core.component;

import java.util.List;

import com.ss4o.core.model.BaseObject;

/**
 * 検索用部品
 * 
 * @author kakusuke
 * 
 * @param <ValueObject>
 *            ValueObjectクラス
 * @param <ConditionClass>
 *            条件用ValueObjectクラス
 */
public interface SelectComponent<ValueObject extends BaseObject, ConditionClass> {
	/**
	 * 検索条件を元に検索した結果を返却します。
	 * 
	 * @param condition
	 *            検索条件
	 * @return 検索した結果
	 */
	abstract public List<ValueObject> doSelect(ConditionClass condition);

	/**
	 * 検索条件を元に検索した結果が存在するかを返却します。
	 * 
	 * @param condition
	 *            検索条件
	 * @return 検索した結果の有無
	 */
	public boolean isExist(ConditionClass condition);

	/**
	 * 検索条件を元に検索した結果の件数を返却します。 <br>
	 * <br>
	 * <strong>注意・COUNT文で取得したSQLの返却値を格納するメソッドでオーバーライドしてください</strong>
	 * 
	 * @param condition
	 *            検索条件
	 * @return 検索した結果の件数
	 */
	public int getCount(ConditionClass condition);
}
