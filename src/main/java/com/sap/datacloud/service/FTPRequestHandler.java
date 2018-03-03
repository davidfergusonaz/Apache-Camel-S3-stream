package com.sap.datacloud.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sap.datacloud.builder.CamelSecurityURLBuilder;
import com.sap.datacloud.exception.FTPEndpointCreationException;
import com.sap.datacloud.model.ProviderPrincipal;
import com.sap.datacloud.util.AppConstants;
import com.sap.datacloud.util.OnboardAppUtil;

/**
 * This class is specific to the ftp endpoint handler, 
 * @author I339480
 *
 */
@Component("ftp")
public class FTPRequestHandler implements RequestHandler {

	@Autowired
	OnboardAppUtil appUtil;

	/*
	 * This method creates FTPEndpoint URL based on provider metadata, it also adds the security params and 
	 * common params to endpoints based on metadata and security and application properties respectively.
	 * @see com.sap.datacloud.service.RequestHandler#createEndPointUrl(com.sap.
	 * datacloud.model.ProviderPrincipal)
	 */
	@Override
	public String createEndPointUrl(ProviderPrincipal principal) {

		StringBuilder ftpEndPoint = new StringBuilder(FTP_ENDPOINT);

		ftpEndPoint.append(principal.getAuthentication().getHost()).append(AppConstants.COLON)
				.append(principal.getAuthentication().getPort()).append(principal.getAuthentication().getPath());

		// get credential and set in 'from' URL for that endpoint
		// for now just basic is supported, need to extend for other security configuration
		
		String securityUri = CamelSecurityURLBuilder.buildURLFromAuth(AppConstants.FTP, principal);

		ftpEndPoint.append(securityUri);

		String finalUri = null;
		try {
			// it sets some common properties such as streaming,noop, etc from
			// properties file.
			finalUri = appUtil.setUriFromProperties(ftpEndPoint.toString(), AppConstants.FTP_PROPERTY_GROUP_KEY);
		} catch (IOException e) {

			throw new FTPEndpointCreationException(e.getMessage(), e);
		}

		return finalUri;
	}

}
