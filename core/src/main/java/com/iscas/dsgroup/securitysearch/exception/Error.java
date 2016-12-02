package com.iscas.dsgroup.securitysearch.exception;

/**
 * 
 * @author lennyBai
 *
 */

public class Error {
	private String code;
	private Object[] args;

	public Error() {
	}

	public Error(String code, Object[] args) {
		this.code = code;
		this.args = args;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void setArgs(Object[] args) {
		this.args = args;
	}

	public String getCode() {
		return this.code;
	}

	public Object[] getArgs() {
		return this.args;
	}
}