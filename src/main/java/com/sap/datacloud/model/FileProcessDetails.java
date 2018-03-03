package com.sap.datacloud.model;

import java.sql.Timestamp;

public class FileProcessDetails {
	
	private String providerId;
	private String domainId;
	private String filename;
	private String category;
	private String stepType;
	private String status;
	private Timestamp timestamp;
	private String delta;
	public String getDelta() {
		return delta;
	}
	public void setDelta(String delta) {
		this.delta = delta;
	}
	public String getSeed() {
		return seed;
	}
	public void setSeed(String seed) {
		this.seed = seed;
	}
	private String seed;
	
	public Timestamp getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getStepType() {
		return stepType;
	}
	public void setStepType(String stepType) {
		this.stepType = stepType;
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
	
	
	

}
