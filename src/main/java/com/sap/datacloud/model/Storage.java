
package com.sap.datacloud.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.annotations.SerializedName;

public class Storage {

	@SerializedName("dataprovider")
    private String dataprovider;
    
    @SerializedName("domain")
    private String domain;
    
    @SerializedName("endpointInfo")
    private List<EndpointInfo> endpointInfo = null;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public String getDataprovider() {
        return dataprovider;
    }

    public void setDataprovider(String dataprovider) {
        this.dataprovider = dataprovider;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public List<EndpointInfo> getEndpointInfo() {
        return endpointInfo;
    }

    public void setEndpointInfo(List<EndpointInfo> endpointInfo) {
        this.endpointInfo = endpointInfo;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
