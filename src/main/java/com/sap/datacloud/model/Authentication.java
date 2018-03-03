package com.sap.datacloud.model;

import java.util.HashMap;
import java.util.Map;

public abstract class Authentication {
	protected String protocol;
	protected String host;
	protected String path;
	protected String port;
	protected String proxytype;
	protected Map<String, Object> additionalProperties = new HashMap<String, Object>();

	public String getProtocol() {
		return protocol;
	}

	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getProxytype() {
		return proxytype;
	}

	public void setProxytype(String proxytype) {
		this.proxytype = proxytype;
	}

	public String getUserid() {
		return null;
	}

	public void setUserid(String userid) {

	}

	public String getPassword() {
		return null;
	}

	public void setPassword(String password) {

	}

	public Map<String, Object> getAdditionalProperties() {
		return additionalProperties;
	}

	public void setAdditionalProperty(Map<String, Object> attr) {
		additionalProperties = attr;
	}

	public String getKeystorelocation() {
		return null;
	}

	public void setKeystorelocation(String keystorelocation) {

	}

	public String getKeystorepassword() {
		return null;
	}

	public void setKeystorepassword(String keystorepassword) {

	}

	public String getTruststorelocation() {
		return null;
	}

	public void setTruststorelocation(String truststorelocation) {

	}

	public String getTruststorepassword() {
		return null;
	}

	public void setTruststorepassword(String truststorepassword) {

	}

	public String getTokenserviceurl() {
		return null;
	}

	public void setTokenserviceurl(String tokenserviceurl) {

	}

	public String getTokenserviceuser() {
		return null;
	}

	public void setTokenserviceuser(String tokenserviceuser) {

	}

	public String getTokenservicepassword() {
		return null;
	}

	public void setTokenservicepassword(String tokenservicepassword) {

	}

	public String getClientkey() {
		return null;
	}

	public void setClientkey(String clientkey) {

	}

	public String getClientsecret() {
		return null;
	}

	public void setClientsecret(String clientsecret) {

	}

	public String getAudience() {
		return null;
	}

	public void setAudience(String audience) {

	}

	public String getAuthurl() {
		return null;
	}

	public void setAuthurl(String authurl) {

	}

}
