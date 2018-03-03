package com.sap.datacloud.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.sap.datacloud.model.ProviderPrincipal;
import com.sap.datacloud.service.RequestHandler;

@Component
public class HandlerFactory {

	@Autowired
	@Qualifier("ftp")
	private RequestHandler ftprequestHandler;

	public RequestHandler getRequestHandler(ProviderPrincipal principal) {

		if ("ftp".equals(principal.getAuthentication().getProtocol())) {
			return ftprequestHandler;
		}

		return null;
	}

}
