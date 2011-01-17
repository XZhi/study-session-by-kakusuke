package com.ss4o.core.model;

import java.sql.Timestamp;

/**
 * 基底ValueObject抽象クラス
 * 
 * @author kakusuke
 * 
 */
abstract public class BaseObject implements Validatable {
	/** ID */
	private String id;

	/** 論理削除フラグ */
	private boolean logicalDeleteFlag;

	/** データ有効期間 */
	private DateRange effectiveDateRange;

	/**
	 * @return id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            セットする id
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return logicalDeleteFlag
	 */
	public boolean isLogicalDeleteFlag() {
		return logicalDeleteFlag;
	}

	/**
	 * @param logicalDeleteFlag
	 *            セットする logicalDeleteFlag
	 */
	public void setLogicalDeleteFlag(boolean logicalDeleteFlag) {
		this.logicalDeleteFlag = logicalDeleteFlag;
	}

	/**
	 * @return effectiveDateRange
	 */
	public DateRange getEffectiveDateRange() {
		return effectiveDateRange;
	}

	/**
	 * @param effectiveDateRange
	 *            セットする effectiveDateRange
	 */
	public void setEffectiveDateRange(DateRange effectiveDateRange) {
		this.effectiveDateRange = effectiveDateRange;
	}

	/**
	 * @return fromDateTime
	 */
	public Timestamp getEffectiveFromDateTime() {
		if (effectiveDateRange == null)
			return null;
		return effectiveDateRange.getFromDateTime();
	}

	/**
	 * @param fromDateTime
	 *            セットする fromDateTime
	 */
	public void setFromDateTime(Timestamp fromDateTime) {
		if (effectiveDateRange == null)
			effectiveDateRange = new DateRange();
		effectiveDateRange.setFromDateTime(fromDateTime);
	}

	/**
	 * @return toDateTime
	 */
	public Timestamp getToDateTime() {
		if (effectiveDateRange == null)
			return null;
		return effectiveDateRange.getToDateTime();
	}

	/**
	 * @param toDateTime
	 *            セットする toDateTime
	 */
	public void setToDateTime(Timestamp toDateTime) {
		if (effectiveDateRange == null)
			effectiveDateRange = new DateRange();
		effectiveDateRange.setToDateTime(toDateTime);
	}
}
