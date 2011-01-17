package com.ss4o.core.security;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

/**
 * ユーザー情報クラス
 * 
 * @author kakusuke
 * 
 */
public class User {
	private UserAttributeLoaderManager userAttributeLoaderManager;

	/**
	 * @param userAttributeLoaderManager
	 *            セットする userAttributeLoaderManager
	 */
	@Resource
	public void setUserAttributeLoaderManager(UserAttributeLoaderManager userAttributeLoaderManager) {
		this.userAttributeLoaderManager = userAttributeLoaderManager;
	}

	/** id */
	private String id;
	private Map<String, Object> attributes = new HashMap<String, Object>();

	/**
	 * @return attributes
	 */
	public Map<String, Object> getAttributes() {
		return attributes;
	}

	/**
	 * @return
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * ログイン処理を行います。
	 */
	public void login() {
		if (null != userAttributeLoaderManager) {
			for (UserAttributeLoader userAttributeLoader : userAttributeLoaderManager.getUserAttributeLoaders()) {
				attributes.put(userAttributeLoader.getKey(), userAttributeLoader.load(id));
			}
		}
	}

	/**
	 * 権限を持っているかチェックします。
	 * 
	 * @param id
	 *            権限ID
	 * @return チェック結果
	 */
	public boolean hasAuthentication(String id) {
		return true;
	}
}
