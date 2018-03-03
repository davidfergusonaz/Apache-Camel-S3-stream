package com.sap.datacloud.service;

import java.util.List;
import java.util.Map;

import org.apache.camel.CamelContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sap.datacloud.exception.DataCloudEndpointCreationExcepton;
import com.sap.datacloud.exception.DataCloudOnboardException;
import com.sap.datacloud.model.DataProvider;
import com.sap.datacloud.model.ProviderMetaData;
import com.sap.datacloud.model.Storage;
import com.sap.datacloud.util.OnboardAppUtil;

/**
 * This class is responsible for processing the route creation logic, it accepts metadata infor 
 * from provider service and creates the endpoints/routes.
 * @author I339480
 *
 */
@Component("routeProcessor")
public class RouteProcessorImpl implements RouteProcessor {

	@Autowired
	private ProviderService dataProviderServiceImpl;

	@Autowired
	private RouteCreator routeCreater;

	/*
	 * This method create and start routes from meta data info.
	 * (non-Javadoc)
	 * @see com.sap.datacloud.service.RouteProcessor#startRoutes()
	 */
	@Override
	public void startRoutes() throws DataCloudOnboardException {

		try {
			Object providerMetaData = dataProviderServiceImpl.getProviderInfo();

			DataProvider[] dataProviderInfo = OnboardAppUtil.getObjectFromJSON(providerMetaData);

			routeCreater.createRoutes(dataProviderInfo);

		} catch (DataCloudEndpointCreationExcepton e) {

			throw new DataCloudOnboardException(e.getMessage(), e);
		}

	}

}
