package com.ss4o.core.model;

/**
 * ペアオブジェクトクラス
 * 
 * @author kakusuke
 * 
 * @param <ValueObject1>
 *            ValueObjectクラス１
 * @param <ValueObject2>
 *            ValueObjectクラス２
 */
public class Pair<ValueObject1 extends BaseObject, ValueObject2 extends BaseObject> {

	/** ValueObject1 */
	private ValueObject1 value1;

	/** ValueObject2 */
	private ValueObject2 value2;

	/**
	 * デフォルトコンストラクタ
	 */
	public Pair() {
	}

	/**
	 * コンストラクタ
	 * 
	 * @param value1
	 *            ValueObject1
	 * @param value2
	 *            ValueObject2
	 */
	public Pair(ValueObject1 value1, ValueObject2 value2) {
		this.value1 = value1;
		this.value2 = value2;
	}

	/**
	 * @return value1
	 */
	public ValueObject1 getValue1() {
		return value1;
	}

	/**
	 * @param value1
	 *            セットする value1
	 */
	public void setValue1(ValueObject1 value1) {
		this.value1 = value1;
	}

	/**
	 * @return value2
	 */
	public ValueObject2 getValue2() {
		return value2;
	}

	/**
	 * @param value2
	 *            セットする value2
	 */
	public void setValue2(ValueObject2 value2) {
		this.value2 = value2;
	}
}
