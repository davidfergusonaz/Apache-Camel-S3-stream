
package com.sap.datacloud.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FileDetail {

    private List<String> fileName = null;
    private String languageCode;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();


    public List<String> getFileName() {
        return fileName;
    }

    public void setFileName(List<String> fileName) {
        this.fileName = fileName;
    }

    public String getLanguageCode() {
        return languageCode;
    }

    public void setLanguageCode(String languageCode) {
        this.languageCode = languageCode;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
