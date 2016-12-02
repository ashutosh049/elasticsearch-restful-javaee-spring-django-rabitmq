package com.iscas.dsgroup.securitysearch.support;

import org.apache.log4j.Logger;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.ImmutableSettings;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;

/**
 * 
 * @author lennyBai
 *
 */

public class EsDao implements Runnable{

	private final static Logger logger = Logger.getLogger(EsDao.class);

	private EsEntity esEntity;
	private Client esClient;
	private boolean connectStatus;
	
	public EsDao() {
		this.esEntity = null;
		this.esClient = null;
		this.connectStatus = false;
	}

	// 现在不被使用，暂时定义为private
	public EsDao(EsEntity esEntity) {
		this.esEntity = esEntity;
		this.esClient = null;
		this.connectStatus = false;
		
		Thread thread = new Thread(this);
		thread.start();
	}

	public void EsConnect() {

		Settings settings = null;
		settings = this.getSettings();
		this.buildConnection(settings);
	}

	private Settings getSettings() {

		Settings settings = null;
		if(esEntity.isSslAvalible())
		{
			ImmutableSettings.settingsBuilder()
						.put("cluster.name", this.esEntity.getClusterName())
				        .put("shield.user", "es_admin:123456")
				        .build();
		}
		else
		{
			settings = ImmutableSettings.settingsBuilder()
						.put("cluster.name", this.esEntity.getClusterName())
						.build();
		}
		return settings;
	}

	@SuppressWarnings("resource")
	private void buildConnection(Settings settings) {

		this.esClient = new TransportClient(settings)
				.addTransportAddress(new InetSocketTransportAddress(this.esEntity.getIp(), this.esEntity.getPort()));	
		
		if ( this.esClient != null )
			this.setConnectStatus(true);
		else
			logger.debug("..");
	}

	public void EsDisconnect(Client client) {

		try {
			client.close();
		} catch (Exception e) {
		}
	}

	/**
	 * @return the esEntity
	 */
	public EsEntity getEsEntity() {
		return esEntity;
	}

	/**
	 * @param esEntity
	 *            the esEntity to set
	 */
	public void setEsEntity(EsEntity esEntity) {
		this.esEntity = esEntity;
	}

	/**
	 * @return the client
	 */
	public Client getEsClient() {

		return esClient;
	}

	/**
	 * @param client
	 *            the client to set
	 */
	public void setEsClient(Client client) {
		this.esClient = client;
	}

	/**
	 * @return the connectStatus
	 */
	public boolean isConnected() {
		return connectStatus;
	}

	/**
	 * @param connectStatus
	 *            the connectStatus to set
	 */
	public void setConnectStatus(boolean connectStatus) {
		this.connectStatus = connectStatus;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		establishEsConnnet();
	}
	
	
	private void establishEsConnnet()
	{
		while( true )
		{
			logger.debug(this.isConnected());
			if (!isConnected())
				this.EsConnect();
			//每隔60s检测一下链接情况
			try {
				Thread.sleep(60000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

}