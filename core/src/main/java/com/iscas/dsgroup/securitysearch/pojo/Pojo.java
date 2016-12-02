package com.iscas.dsgroup.securitysearch.pojo;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author lennyBai
 *
 */

//@XmlRootElement
public abstract class Pojo {

	private Date createdate;
	private Date updatedate;

	public Pojo() {
	}

	public Date getCreatedate() {
		return this.createdate;
	}

	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}

	public Date getUpdatedate() {
		return this.updatedate;
	}

	public void setUpdatedate(Date updatedate) {
		this.updatedate = updatedate;
	}
}