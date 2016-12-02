package com.iscas.dsgroup.securitysearch.pojo;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author lennyBai
 *
 */

//@XmlRootElement
public class ConfigParam {
	private int todoAction;
	private int indexFrom;	//显示结果从第几项开始显示
	private int hitsNum;	//显示结果的条数，默认为10
	
	public ConfigParam()
	{
		todoAction = 0;
		indexFrom = 0;
		hitsNum = 10;
	}
	
	public int getTodoAction() {
		return todoAction;
	}
	public void setTodoAction(int todoAction) {
		this.todoAction = todoAction;
	}

	public int getIndexFrom() {
		return indexFrom;
	}

	public void setIndexFrom(int indexFrom) {
		this.indexFrom = indexFrom;
	}

	public int getHitsNum() {
		return hitsNum;
	}

	public void setHitsNum(int hitsNum) {
		this.hitsNum = hitsNum;
	}
}
