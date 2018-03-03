package com.sap.datacloud.model;

import java.util.Map;

public class ProviderMetaData implements MetadataType {

	private String providerName;

	private String domainName;

	private String protocol;

	private String seedPath;

	private String deltaPath;

	private String host;

	private String port;

	private boolean isStreamDownload;

	private String authenticationType;

	private boolean isFile;

	private String fileType;

	private boolean isCompressed;

	private String compressType;

	private String isBase64Encoded;

	private Map<String, String> authentication;

	private Map<String, String> endpointConfig;

	private String stringUri;

	public String getProviderName() {
		return providerName;
	}

	public void setProviderName(String providerName) {
		this.providerName = providerName;
	}

	public String getDomainName() {
		return domainName;
	}

	public void setDomainName(String domainName) {
		this.domainName = domainName;
	}

	public String getProtocol() {
		return protocol;
	}

	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}

	public String getSeedPath() {
		return seedPath;
	}

	public void setSeedPath(String seedPath) {
		this.seedPath = seedPath;
	}

	public String getDeltaPath() {
		return deltaPath;
	}

	public void setDeltaPath(String deltaPath) {
		this.deltaPath = deltaPath;
	}

	@Override
	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	@Override
	
	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public boolean isStreamDownload() {
		return isStreamDownload;
	}

	public void setStreamDownload(boolean isStreamDownload) {
		this.isStreamDownload = isStreamDownload;
	}

	public String getAuthenticationType() {
		return authenticationType;
	}

	public void setAuthenticationType(String authenticationType) {
		this.authenticationType = authenticationType;
	}

	public boolean isFile() {
		return isFile;
	}

	public void setFile(boolean isFile) {
		this.isFile = isFile;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public boolean isCompressed() {
		return isCompressed;
	}

	public void setCompressed(boolean isCompressed) {
		this.isCompressed = isCompressed;
	}

	public String getCompressType() {
		return compressType;
	}

	public void setCompressType(String compressType) {
		this.compressType = compressType;
	}

	public String getIsBase64Encoded() {
		return isBase64Encoded;
	}

	public void setIsBase64Encoded(String isBase64Encoded) {
		this.isBase64Encoded = isBase64Encoded;
	}

	public Map<String, String> getAuthentication() {
		return authentication;
	}

	public void setAuthentication(Map<String, String> authentication) {
		this.authentication = authentication;
	}

	public Map<String, String> getEndpointConfig() {
		return endpointConfig;
	}

	public void setEndpointConfig(Map<String, String> endpointConfig) {
		this.endpointConfig = endpointConfig;
	}

	@Override
	public String getUri() {

		return stringUri;
	}

	@Override
	public Map<String, String> getParams() {

		return endpointConfig;
	}


}
