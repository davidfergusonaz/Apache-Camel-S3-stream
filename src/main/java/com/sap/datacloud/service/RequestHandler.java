package com.sap.datacloud.service;

import com.sap.datacloud.model.ProviderPrincipal;

public interface RequestHandler {
	
	public static String FTP_ENDPOINT = "ftp://";
	public static String FTPS_ENDPOINT = "ftps://";
	public static String SFTP_ENDPOINT = "sftp://";
	
	public String createEndPointUrl(ProviderPrincipal providerInfo);

}
