package com.sap.datacloud.model;

import java.util.Map;

public interface MetadataType

{

	public String getUri();

	public String getHost();

	public String getPort();

	public String getSeedPath();

	public String getDeltaPath();

	public Map<String, String> getParams();

	public Map<String, String> getAuthentication();

}