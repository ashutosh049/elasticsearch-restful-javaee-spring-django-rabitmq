package com.iscas.dsgroup.securitysearch.support;

import java.util.concurrent.locks.AbstractQueuedLongSynchronizer.ConditionObject;

import com.iscas.dsgroup.securitysearch.common.ConstantsSetting;

/**
 * 
 * @author lennyBai
 *
 */

public abstract class EsDaoSupport {

	private EsDao esDao = new EsDao(new EsEntity(ConstantsSetting.ES_CLUSTERNAME, ConstantsSetting.ES_SERVERIP,
			ConstantsSetting.ES_SERVERPORT));

	public EsDaoSupport() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the esDao
	 */
	public EsDao getEsDao() {
		return esDao;
	}

	/**
	 * @param esDao
	 *            the esDao to set
	 */
	public void setEsDao(EsDao esDao) {
		this.esDao = esDao;
	}
}