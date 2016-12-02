package com.iscas.dsgroup.securitysearch.pojo;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author lennyBai
 *
 */

//@XmlRootElement
public class UserPojo extends Pojo {

	private String userId;
	private String userName;
	private int userLevel;

	public UserPojo() {
		super();
		this.userId = null;
		this.userName = null;
		this.userLevel = 0;
	}

	public UserPojo(String userId, String userName, int userLevel) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.userLevel = userLevel;
	}

	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * @param userId
	 *            the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName
	 *            the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return the userLevel
	 */
	public int getUserLevel() {
		return userLevel;
	}

	/**
	 * @param userLevel
	 *            the userLevel to set
	 */
	public void setUserLevel(int userLevel) {
		this.userLevel = userLevel;
	}

}