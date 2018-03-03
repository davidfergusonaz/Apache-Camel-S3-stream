package com.sap.datacloud.model;

/**
 * This is a simple java pojo class which contains the endpoint connection information along with security,
 * domain and provider name.
 * @author I339480
 *
 */
public class ProviderPrincipal {
	
	private String authType;
	private String provider;
	private String domain;
	private Authentication authentication;
	
	public String getProvider() {
		return provider;
	}

	public void setProvider(String provider) {
		this.provider = provider;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public String getAuthType() {
		return authType;
	}

	public void setAuthType(String authType) {
		this.authType = authType;
	}

	public Authentication getAuthentication() {
		return authentication;
	}

	public void setAuthentication(Authentication authentication) {
		this.authentication = authentication;
	}

}
