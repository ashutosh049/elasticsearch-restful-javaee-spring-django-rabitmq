package com.iscas.dsgroup.securitysearch.support;

/**
 * 
 * @author lennyBai
 *
 */

public class EsEntity {

	private String clusterName;
	private String ip;
	private int port;
	private boolean sslAvalible;

	public EsEntity() {

		this.clusterName = null;
		this.ip = null;
		this.port = -1;
		this.sslAvalible = false;
	}

	// 暂时没有使用，定义为private
	public EsEntity(String clusterName, String ip, int port, boolean sslAvalible) {

		this.clusterName = clusterName;
		this.ip = ip;
		this.port = port;
		this.sslAvalible = sslAvalible;
	}
	
	// 暂时没有使用，定义为private
	public EsEntity(String clusterName, String ip, int port) {

		this.clusterName = clusterName;
		this.ip = ip;
		this.port = port;
		this.sslAvalible = false;
	}

	/**
	 * @return the clusterName
	 */
	public String getClusterName() {
		return clusterName;
	}

	/**
	 * @param clusterName
	 *            the clusterName to set
	 */
	public void setClusterName(String clusterName) {
		this.clusterName = clusterName;
	}

	/**
	 * @return the ip
	 */
	public String getIp() {
		return ip;
	}

	/**
	 * @param ip
	 *            the ip to set
	 */
	public void setIp(String ip) {
		this.ip = ip;
	}

	/**
	 * @return the port
	 */
	public int getPort() {
		return port;
	}

	/**
	 * @param port
	 *            the port to set
	 */
	public void setPort(int port) {
		this.port = port;
	}

	public boolean isSslAvalible() {
		return sslAvalible;
	}

	public void setSslAvalible(boolean sslAvalible) {
		this.sslAvalible = sslAvalible;
	}

}
