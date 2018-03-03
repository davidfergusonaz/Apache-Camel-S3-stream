package com.sap.datacloud.service;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.amazonaws.util.IOUtils;

@Component
public class DataProviderServiceImpl implements ProviderService{

	@Autowired
	private RestTemplate restProcessor;
	
	@Value("${provider.metadata.url}")
	private String metaDataUrl;
	
	private static final Logger LOGGER = LogManager.getLogger(CamelRouteCreator.class);
	
	
	@Override
	public Object getProviderInfo() {

//		String providerJson = restProcessor.getForObject(metaDataUrl, String.class);
//		
//		LOGGER.debug("meta data url recieved from provider metadata service {}. ",providerJson);
//		
		
		
		  InputStream is = 
				  this.getClass().getClassLoader().getResourceAsStream("metadata.txt");
	        String jsonTxt = null;
			try {
				jsonTxt = IOUtils.toString( is );
				//jsonTxt = new String(Files.readAllBytes(Paths.get("src/main/resources/metadata.txt")));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
	        
		return jsonTxt;
	}

}
