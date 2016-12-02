package com.iscas.dsgroup.securitysearch.deamon;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * class for test
 * 
 * @author lennyBai
 *
 */

@XmlRootElement
public class TestData {
	private int id;
	private String value;
	private String[] arg;

	public TestData() {
		id = 0;
		value = "testDate";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String[] getArg() {
		return arg;
	}

	public void setArg(String[] arg) {
		this.arg = arg;
	}
}