/**
 * 
 */
package com.ss4o.core.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import com.ss4o.core.exception.BusinessException;
import com.ss4o.core.security.User;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.Assert;

/**
 * サービスプロセス内のコンテキスト
 * 
 * @author kakusuke
 * 
 */
public class ServiceContext {
	/** logger */
	public static Log log = LogFactory.getLog(ServiceContext.class);

	/** class name stack */
	private static ThreadLocal<Stack<String>> classNameStack = new ThreadLocal<Stack<String>>();

	/** exception list */
	private static ThreadLocal<List<BusinessException>> exceptions = new ThreadLocal<List<BusinessException>>();

	/** user info */
	private static ThreadLocal<User> userInfo = new ThreadLocal<User>();

	/** MSG_START_CLASS */
	private static String MSG_START_CLASS = "start context for ";

	/** MSG_END_CLASS */
	private static String MSG_END_CLASS = "end context for ";

	/** MSG_NOT_LOGINED */
	private static String MSG_NOT_LOGINED = "not logined";

	/**
	 * ユーザー情報の取得
	 * 
	 * @return ユーザー情報
	 */
	public static User getUser() {
		User _return = userInfo.get();
		if (_return == null) {
			throw new BusinessException(MSG_NOT_LOGINED);
		}
		return _return;
	}

	/**
	 * サービスプロセスへのログイン処理
	 * 
	 * @param user
	 *            ユーザー情報
	 */
	public static void login(User user) {
		// TODO	ログイン処理の実装
		userInfo.set(user);
	}

	/**
	 * サービスプロセス内の例外情報の追加
	 * 
	 * @param e
	 *            例外情報
	 */
	public static void appendException(BusinessException e) {
		if (exceptions.get() == null) {
			exceptions.set(new ArrayList<BusinessException>());
		}
		exceptions.get().add(e);
	}

	/**
	 * サービスプロセス内の例外情報の取得
	 * 
	 * @return 例外情報（発生順）
	 */
	public static List<BusinessException> getExceptions() {
		List<BusinessException> _return = exceptions.get();
		if (_return == null) {
			return new ArrayList<BusinessException>();
		}
		return _return;
	}

	/**
	 * @param className
	 */
	private static void pushClassName(String className) {
		Stack<String> stack = classNameStack.get();
		if (null == stack || stack.size() == 0) {
			classNameStack.set(new Stack<String>());
			exceptions.set(new ArrayList<BusinessException>());
		}
		classNameStack.get().push(className);
	}

	/**
	 * @return
	 */
	private static String popClassName() {
		String _return = classNameStack.get().pop();
		if (classNameStack.get().size() < 1) {
			classNameStack.set(null);
			userInfo.set(null);
		}
		return _return;
	}

	/**
	 * サービス開始時に呼び出す処理
	 * 
	 * @param clazz
	 *            対象サービスのクラスオブジェクト
	 */
	public static void start(Class<? extends Object> clazz) {
		Assert.isNull(clazz, "argument [clazz] cannot set null.");
		String className = clazz.getName();
		pushClassName(className);
		log.trace(MSG_START_CLASS + "[" + className + "]");
	}

	/**
	 * サービス終了時に呼び出す処理
	 */
	public static void end() {
		String className = popClassName();
		log.trace(MSG_END_CLASS + "[" + className + "]");
	}

	/**
	 * ネストされた呼び出し階層チェック
	 * 
	 * @return ネストされた呼び出し階層:true<br>
	 *         ルート呼び出し階層:false
	 */
	public static boolean isNestedService() {
		Stack<String> stack = classNameStack.get();
		return null != stack && stack.size() > 1;
	}
}
