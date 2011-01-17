package com.ss4o.core.model;

import java.sql.Timestamp;
import java.util.Date;

import com.ss4o.core.exception.ValidateException;

/**
 * 日付範囲クラス
 * 
 * @author kakusuke
 * 
 */
public class DateRange implements Validatable {

	/** MSG_VALIDATION_ERROR */
	private static final String MSG_VALIDATION_ERROR = "日付範囲が不正です。";

	/** 開始日時 */
	private Timestamp fromDateTime;

	/** 終了日時 */
	private Timestamp toDateTime;

	/* (非 Javadoc)
	 * @see com.ss4o.core.model.Validatable#validate()
	 */
	public void validate() throws ValidateException {
		if (fromDateTime == null || toDateTime == null)
			return;
		if (fromDateTime.compareTo(toDateTime) < 0)
			return;
		throw new ValidateException(MSG_VALIDATION_ERROR);
	}

	/**
	 * 対象日付が日付範囲に含まれるか判定します。
	 * 
	 * @param date
	 *            対象日付
	 * @return 判定結果
	 */
	public boolean checkRange(Date date) {
		validate();
		if (date == null)
			throw new NullPointerException();
		if ((fromDateTime != null && fromDateTime.compareTo(date) > 0) || (toDateTime != null && toDateTime.compareTo(date) < 0))
			return false;
		return true;
	}

	/**
	 * @return fromDateTime
	 */
	public Timestamp getFromDateTime() {
		return fromDateTime;
	}

	/**
	 * @param fromDateTime
	 *            セットする fromDateTime
	 */
	public void setFromDateTime(Timestamp fromDateTime) {
		this.fromDateTime = fromDateTime;
	}

	/**
	 * @return toDateTime
	 */
	public Timestamp getToDateTime() {
		return toDateTime;
	}

	/**
	 * @param toDateTime
	 *            セットする toDateTime
	 */
	public void setToDateTime(Timestamp toDateTime) {
		this.toDateTime = toDateTime;
	}
}
