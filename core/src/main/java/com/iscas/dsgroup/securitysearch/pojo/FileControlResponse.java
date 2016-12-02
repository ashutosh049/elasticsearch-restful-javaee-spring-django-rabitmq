package com.iscas.dsgroup.securitysearch.pojo;

import javax.xml.bind.annotation.XmlRootElement;

import com.sun.xml.txw2.annotation.XmlElement;

//@XmlRootElement
public class FileControlResponse {

	private int code;
	private String msg;
	private String body;
	
	public FileControlResponse()
	{
		this.code = 0;
		this.msg = null;
		this.body = null;
	}
	
	public FileControlResponse( int code, String msg, String body)
	{
		this.code = code;
		this.msg = msg;
		this.body = body;
	}
	
	public int getCode() {
		return code;
	}

	public String getMsg() {
		return msg;
	}

	public String getBody() {
		return body;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public void setBody(String body) {
		this.body = body;
	}
}
