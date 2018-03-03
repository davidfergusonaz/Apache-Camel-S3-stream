package com.sap.datacloud.util;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sap.datacloud.model.DataProvider;
import com.sap.datacloud.model.EndpointInfo;
import com.sap.datacloud.model.FileProcessDetails;
import com.sap.datacloud.model.ProviderDomainInfo;
import com.sap.datacloud.model.StatusType;
import com.sap.datacloud.service.PropertyFilter;
import static com.sap.datacloud.util.AppConstants.*;

@Component
public class OnboardAppUtil {

	@Autowired
	private PropertyFilter propertyFilter;

	/**
	 * This method populates the JSON string to java pojo's. It uses gson.
	 * @param jsonString
	 * @return
	 */
	public static DataProvider[] getObjectFromJSON(Object jsonString) {

		String json = (String) jsonString;

		Gson gson = new Gson();

		DataProvider[] dataProviderInfo = gson.fromJson(json, new TypeToken<DataProvider[]>() {}.getType());
		

		return dataProviderInfo;

	}

	/**
	 * This methods sets the common paramaters to endpoint URI's which is defined in properties file.
	 * @param endPointUrl
	 * @param filterPrefix
	 * @return
	 * @throws IOException
	 */
	public String setUriFromProperties(String endPointUrl, String filterPrefix) throws IOException {

		StringBuilder endPointBuilder = new StringBuilder(endPointUrl);

		Map<String, String> filteredProperties = propertyFilter.filter(filterPrefix);

		for (Map.Entry<String, String> entry : filteredProperties.entrySet()) {
			endPointBuilder.append(AMPERSAND).append((entry.getKey().substring(entry.getKey().lastIndexOf(DOT) + 1).trim()))
					.append(EQUALS).append(entry.getValue());
		}

		return endPointBuilder.toString();

	}

	/**
	 * This method sets the FileProcessDetails objects while creating the camel endpoint routes with information such as domain id,provider id,
	 * category etc. 
	 * @param endPoint
	 * @param domain
	 * @param provider
	 * @return
	 */
	public FileProcessDetails getFileDetails(EndpointInfo endPoint, ProviderDomainInfo providerInfo) {

		FileProcessDetails fileDetails = new FileProcessDetails();
		fileDetails.setProviderId(providerInfo.getProviderID());
		fileDetails.setDomainId(providerInfo.getDomainID());
		fileDetails.setCategory(endPoint.getCategory());
		fileDetails.setStatus(StatusType.NEW.toString());
		return fileDetails;

	}
	
	/**
	 * This method combines the regex pattern provided by data providers.
	 * @param endPointUrl
	 * @param filePattern
	 * @return
	 */
	public String getRegexIncludeFilter(String endPointUrl, List<String> filePattern){
		
		StringBuilder pattern = new StringBuilder();
		
		for (String prefix : filePattern) {
				pattern.append(prefix). append(PIPE);
		}
		pattern.setLength(pattern.length()-1);
		
		return endPointUrl+AMPERSAND+INCLUDE+EQUALS+pattern.toString();
	}

}
