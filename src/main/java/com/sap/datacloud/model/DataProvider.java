
package com.sap.datacloud.model;

public class DataProvider {
	private Metadata metadata;
	private String providerId;
	private String domainId;


	@Override
	public String toString() {
		return "ClassPojo [storage = " + metadata + "]";
	}

	public String getProviderId() {
		return providerId;
	}

	public void setProviderId(String providerId) {
		this.providerId = providerId;
	}

	public String getDomainId() {
		return domainId;
	}

	public void setDomainId(String domainId) {
		this.domainId = domainId;
	}

	public Metadata getMetadata() {
		return metadata;
	}

	public void setMetadata(Metadata metadata) {
		this.metadata = metadata;
	}
}
