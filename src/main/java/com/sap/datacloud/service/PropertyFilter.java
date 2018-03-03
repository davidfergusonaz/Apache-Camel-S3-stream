package com.sap.datacloud.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.stereotype.Component;
/**
 * This is utility class to group similar propertry in application.properties
 * @author I339480
 *
 */
@Component
public class PropertyFilter {
	
	/**
	 * This method groups properties with similar prefix key 
	 * in application properties. Its used to get common properties for camel endpoints.
	 * @param prefix
	 * @param propertyName
	 * @return
	 * @throws IOException
	 */
	public Map<String, String> filter(String prefix, String ... propertyName) throws IOException {
		Map<String, String> propertiesMap = new HashMap<>();
		
		String property = propertyName.length >0 ?propertyName[0]:"application.properties";

		//need to change this to file resource if using external properties
		Resource resource = new ClassPathResource( property);
		
		Properties prop = PropertiesLoaderUtils.loadProperties(resource);

		for (String key : prop.stringPropertyNames()) {

			if (key.startsWith(prefix)) {
				propertiesMap.put( key,prop.getProperty(key));
			}
		}

		return propertiesMap;
	}

}
