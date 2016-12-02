package com.iscas.dsgroup.securitysearch.pojo;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

/**
 * 
 * @author lennyBai
 *
 */

@XmlRootElement
@XmlSeeAlso({FilePojo.class,FileControlResponse.class})
public class ResponseElement {
	private String status;
	private Object response;

	public ResponseElement() {
	}

	public ResponseElement(String status, Object response) {
		this.status = status;
		this.response = response;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Object getResponse() {
		return this.response;
	}

	public void setResponse(Object response) {
		this.response = response;
	}

	public static ResponseElement newSuccessInstance(Object response) {
		return new ResponseElement("OK", response);
	}

	public static ResponseElement newFailedInstance(Object message) {
		return new ResponseElement("FAILED", message);
	}
}