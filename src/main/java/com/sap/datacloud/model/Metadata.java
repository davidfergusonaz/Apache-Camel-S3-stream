package com.sap.datacloud.model;

import java.util.HashMap;
import java.util.Map;

public class Metadata {

private Storage storage;
private Map<String, Object> additionalProperties = new HashMap<String, Object>();

public Storage getStorage() {
return storage;
}

public void setStorage(Storage storage) {
this.storage = storage;
}

public Map<String, Object> getAdditionalProperties() {
return this.additionalProperties;
}

public void setAdditionalProperty(String name, Object value) {
this.additionalProperties.put(name, value);
}

}