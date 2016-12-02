package com.iscas.dsgroup.securitysearch.pojo;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author lennyBai
 *
 */

@XmlRootElement
public class CommonParam {

	private FilePojo filePojo;
	private UserPojo userPojo;
	private ConfigParam configParam;

	public CommonParam() {
		filePojo = new FilePojo();
		userPojo = new UserPojo();
		configParam = new ConfigParam();
	}

	/**
	 * @return the filePojo
	 */
	public FilePojo getFilePojo() {
		return filePojo;
	}

	/**
	 * @return the userPojo
	 */
	public UserPojo getUserPojo() {
		return userPojo;
	}

	/**
	 * @param filePojo
	 *            the filePojo to set
	 */
	public void setFilePojo(FilePojo filePojo) {
		this.filePojo = filePojo;
	}

	/**
	 * @param userPojo
	 *            the userPojo to set
	 */
	public void setUserPojo(UserPojo userPojo) {
		this.userPojo = userPojo;
	}

	public ConfigParam getConfigParam() {
		return configParam;
	}

	public void setConfigParam(ConfigParam configParam) {
		this.configParam = configParam;
	}
	
	public CommonParam getDemo()
	{
		filePojo.setEncrypt(false);
		filePojo.setFileContent("Enter file content");
		filePojo.setFileID("Enter file id");
		filePojo.setFileLevel(0);
		filePojo.setFileLib("Enter File Lib");
		filePojo.setFileName("Enter file name");
		filePojo.setFileOwner("Enter file Owner");
		filePojo.setFilePath("Enter File Path");
		filePojo.setLibType("Enter Lib Type");
		
		userPojo.setUserId("Enter User ID");
		userPojo.setUserLevel(0);
		userPojo.setUserName("Enter User Name");
		
		configParam.setIndexFrom(0);
		configParam.setHitsNum(10);
		configParam.setTodoAction(0);
		
		return this;
	}

}