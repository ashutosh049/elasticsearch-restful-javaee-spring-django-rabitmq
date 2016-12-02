package com.iscas.dsgroup.securitysearch.pojo;

import javax.xml.bind.annotation.XmlRootElement;

import com.google.gson.Gson;

/**
 * 
 * @author lennyBai
 *
 */

@XmlRootElement
public class FilePojo extends Pojo {

	private String fileID;
	private String fileName;
	private String filePath;
	private String fileOwner;
	private String[] fileUser;
	private String fileLib;
	private String[] fileTag;
	private String libType;
	private String fileContent;
	private String fileKeyword;
	private boolean encrypt;
	private int fileLevel;

	public FilePojo() {
		super();
		this.fileID = "";
		this.fileName = "";
		this.filePath = "";
		this.fileOwner = "";
		this.fileUser = null;
		this.fileLib = "";
		this.fileTag = null;
		this.libType = "";
		this.fileContent = "";
		this.fileKeyword = "";
		this.encrypt = false;
		this.fileLevel = 0;
	}

	/**
	 * @return the fileID
	 */
	public String getFileID() {
		return fileID;
	}

	/**
	 * @param fileID
	 *            the fileID to set
	 */
	public void setFileID(String fileID) {
		this.fileID = fileID;
	}

	/**
	 * @return the fileName
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 * @param fileName
	 *            the fileName to set
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	/**
	 * @return the filePath
	 */
	public String getFilePath() {
		return filePath;
	}

	/**
	 * @param filePath
	 *            the filePath to set
	 */
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	/**
	 * @return the fileOwner
	 */
	public String getFileOwner() {
		return fileOwner;
	}

	/**
	 * @param fileOwner
	 *            the fileOwner to set
	 */
	public void setFileOwner(String fileOwner) {
		this.fileOwner = fileOwner;
	}

	/**
	 * @return the fileLib
	 */
	public String getFileLib() {
		return fileLib;
	}

	/**
	 * @param fileLib
	 *            the fileLib to set
	 */
	public void setFileLib(String fileLib) {
		this.fileLib = fileLib;
	}

	/**
	 * @return the libType
	 */
	public String getLibType() {
		return libType;
	}

	/**
	 * @param libType
	 *            the libType to set
	 */
	public void setLibType(String libType) {
		this.libType = libType;
	}

	/**
	 * @return the fileContent
	 */
	public String getFileContent() {
		return fileContent;
	}

	/**
	 * @param fileContent
	 *            the fileContent to set
	 */
	public void setFileContent(String fileContent) {
		this.fileContent = fileContent;
	}

	public void setFileUser(String[] fileUser) {
		this.fileUser = fileUser;
	}

	public void setFileTag(String[] fileTag) {
		this.fileTag = fileTag;
	}

	public void setFileKeyword(String fileKeyword) {
		this.fileKeyword = fileKeyword;
	}

	/**
	 * @return the encrypt
	 */
	public boolean isEncrypt() {
		return encrypt;
	}

	/**
	 * @param encrypt
	 *            the encrypt to set
	 */
	public void setEncrypt(boolean encrypt) {
		this.encrypt = encrypt;
	}

	/**
	 * @return the fileLevel
	 */
	public int getFileLevel() {
		return fileLevel;
	}

	/**
	 * @param fileLevel
	 *            the fileLevel to set
	 */
	public void setFileLevel(int fileLevel) {
		this.fileLevel = fileLevel;
	}


	public String[] getFileUser() {
		return fileUser;
	}

	public String[] getFileTag() {
		return fileTag;
	}

	public String getFileKeyword() {
		return fileKeyword;
	}

	
	public String toJsonString() {
		Gson gson = new Gson();
		return gson.toJson(this);
	}

}