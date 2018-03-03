
package com.sap.datacloud.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EndpointInfo {

	private String mode;
	private String fileType;
	private String delimeter;
    private String category;
    private String encoding;
    private String recordlimit;
    private String frequency;
    private String isCompressed;
    private String compressionFormat;
    private String isBase64Encoded;
    private String authenticationType;
    private List<FileDetail> fileDetails = null;
    private AuthenticationDetails authenticationDetails;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getEncoding() {
        return encoding;
    }

    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }

    public String getRecordlimit() {
        return recordlimit;
    }

    public void setRecordlimit(String recordlimit) {
        this.recordlimit = recordlimit;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public String getIsCompressed() {
        return isCompressed;
    }

    public void setIsCompressed(String isCompressed) {
        this.isCompressed = isCompressed;
    }

    public String getCompressionFormat() {
        return compressionFormat;
    }

    public void setCompressionFormat(String compressionFormat) {
        this.compressionFormat = compressionFormat;
    }

    public String getIsBase64Encoded() {
        return isBase64Encoded;
    }

    public void setIsBase64Encoded(String isBase64Encoded) {
        this.isBase64Encoded = isBase64Encoded;
    }

    public String getAuthenticationType() {
        return authenticationType;
    }

    public void setAuthenticationType(String authenticationType) {
        this.authenticationType = authenticationType;
    }

    public List<FileDetail> getFileDetails() {
        return fileDetails;
    }

    public void setFileDetails(List<FileDetail> fileDetails) {
        this.fileDetails = fileDetails;
    }

    public AuthenticationDetails getAuthenticationDetails() {
        return authenticationDetails;
    }

    public void setAuthenticationDetails(AuthenticationDetails authenticationDetails) {
        this.authenticationDetails = authenticationDetails;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public String getDelimeter() {
		return delimeter;
	}

	public void setDelimeter(String delimeter) {
		this.delimeter = delimeter;
	}

	public void setAdditionalProperties(Map<String, Object> additionalProperties) {
		this.additionalProperties = additionalProperties;
	}

}
