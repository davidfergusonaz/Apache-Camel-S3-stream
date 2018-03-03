package com.sap.datacloud.routes;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.sap.datacloud.dao.FileProcessDAO;
import com.sap.datacloud.model.FileProcessDetails;

public class DynamicRouter extends RouteBuilder {
	

    private  String from;
    private  String to;
    private  String intercept;
    private  FileProcessDetails fileProcessDetails;
    private  String awsKeyPrefix;

    public DynamicRouter(String frm, String too, String interceptUrl, FileProcessDetails fileDetails,String prefix) {
        from = frm;
        to = too;
        intercept = interceptUrl;
        fileProcessDetails = fileDetails;
        awsKeyPrefix = prefix;
    }

    @Override
    public void configure() throws Exception {
    	
    	onException(Exception.class)
        .maximumRedeliveries(3).redeliveryDelay(60000);
    	
        from(from).routeId("route_"+fileProcessDetails.getDomainId()+"_"+fileProcessDetails.getProviderId()+"_"+fileProcessDetails.getCategory()).
        process( new Processor() {
			
			@Override
			public void process(Exchange exchange) throws Exception {
				String fileName = (String) exchange.getIn().getHeader("CamelFileName");
				fileProcessDetails.setFilename(fileName);
				exchange.setProperty("FileProcessDetails", fileProcessDetails);
				exchange.setProperty("AwsKeyPrefix", awsKeyPrefix);
				
			}
		}).
        to(intercept).
        to(to);
      
    }


}

