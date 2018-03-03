package com.sap.datacloud.service;

import java.util.Arrays;
import java.util.List;

import org.apache.camel.CamelContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.sap.datacloud.exception.DataCloudEndpointCreationExcepton;
import com.sap.datacloud.factory.AuthenticationFactory;
import com.sap.datacloud.factory.HandlerFactory;
import com.sap.datacloud.model.DataProvider;
import com.sap.datacloud.model.EndpointInfo;
import com.sap.datacloud.model.FileDetail;
import com.sap.datacloud.model.FileProcessDetails;
import com.sap.datacloud.model.ProviderDomainInfo;
import com.sap.datacloud.model.ProviderPrincipal;
import com.sap.datacloud.model.Storage;
import com.sap.datacloud.routes.DynamicRouter;
import com.sap.datacloud.util.AppConstants;
import com.sap.datacloud.util.OnboardAppUtil;

@Component
public class CamelRouteCreator implements RouteCreator {

	@Autowired
	private CamelContext camelContext;

	@Autowired
	private HandlerFactory handlerFactory;

	@Autowired
	private OnboardAppUtil appUtil;
	
	@Value("${s3.bocket.key}")
	private String bucketKey;
	
	@Value("${spring.profiles.active:dev}")
	private String env;
	
	private static final Logger LOGGER = LogManager.getLogger(CamelRouteCreator.class);
	
	/*
	 * This method creates the routes based on the provider metadata
	 * (non-Javadoc)
	 * @see com.sap.datacloud.service.RouteCreator#createRoutes(java.lang.Object)
	 */
	@Override
	public void createRoutes(Object providerInfo) {

		DataProvider[] dataProviders = (DataProvider[]) providerInfo;

		Arrays.asList(dataProviders).forEach(dataProvider -> {
			
			
			ProviderDomainInfo providerDomainInfo = getProviderInfo(dataProvider);

			for (EndpointInfo endpointInfo : dataProvider.getMetadata().getStorage().getEndpointInfo()) {

				dispatchInternal(providerDomainInfo, endpointInfo);

			}

		});

	}



	private ProviderDomainInfo getProviderInfo(DataProvider dataProvider) {
		ProviderDomainInfo providerInfo = new ProviderDomainInfo();
		providerInfo.setDomainID(dataProvider.getDomainId());
		providerInfo.setProviderID(dataProvider.getProviderId());
		providerInfo.setDomainName(dataProvider.getMetadata().getStorage().getDomain());
		providerInfo.setProviderName(dataProvider.getMetadata().getStorage().getDataprovider());
		return providerInfo;
	}

	/**
	 * This method creates individual routes by choosing corresponding endpoint handler and populates metadata related to endpoint, credentials 
	 * and also creates the from url.
	 * @param providerName
	 * @param domain
	 * @param endPointInfo
	 */
	public void dispatchInternal(ProviderDomainInfo providerInfo, EndpointInfo endPointInfo) {

		
		String authType = endPointInfo.getAuthenticationType();
		
		LOGGER.debug("authType for domain {} and provider {} is {}. ",providerInfo.getDomainName(),providerInfo.getProviderName(),authType);

		ProviderPrincipal principal = AuthenticationFactory.getPrincipal(authType,endPointInfo.getAuthenticationDetails());

		// get respective handler such as ftp,http, rest etc.
		RequestHandler endPointHandler = handlerFactory.getRequestHandler(principal);

		String from = endPointHandler.createEndPointUrl(principal);

		List<FileDetail> prefixList = endPointInfo.getFileDetails();

		for (FileDetail fileDetail : prefixList) {

			// get regex filter for specific file names
			from = appUtil.getRegexIncludeFilter(from, fileDetail.getFileName());
			
			LOGGER.debug("from URI for domain {} and provider {} is {}. ",providerInfo.getDomainName(),providerInfo.getProviderName(),from);

			//set fileprocess details which is needed to save status in fileprocess table
			FileProcessDetails fileProcessDetails = appUtil.getFileDetails(endPointInfo,providerInfo);

			String awskeyPrefix = getS3BucketPrefix(providerInfo);
			
			LOGGER.debug("S3 bucket Key for doamin{} and provider {} is {}. ",providerInfo.getDomainName(),providerInfo.getProviderName(),awskeyPrefix);

			try {
				camelContext.addRoutes(new DynamicRouter(from, AppConstants.AWS_ENDPOINT, AppConstants.REPOSITORY_ENDPOINT, fileProcessDetails, awskeyPrefix));
			} catch (Exception e) {
				
				LOGGER.error("exception while creating endpoint for dataprovider : {} and domain : {} , error message {} ",providerInfo.getDomainName(),providerInfo.getProviderName(),e.getMessage());
				throw new DataCloudEndpointCreationExcepton(e.getMessage(), e);
			}

			LOGGER.debug("endpoint is created  for doamin{} and provider {}. ",providerInfo.getDomainName(),providerInfo.getProviderName());
		}

	}
	
	private String getS3BucketPrefix(ProviderDomainInfo providerInfo ){
		
		String key = bucketKey.replace("${domain}", providerInfo.getDomainName()).
				replace("${provider}", providerInfo.getProviderName()).
				replace("${env}",env);
		
		return key;
				
		
	}


}
 