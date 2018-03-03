package com.sap.datacloud.routes;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

public class BusinessPartnerRouter extends RouteBuilder  {
	
	@Value("${domain.businessPartner}")
	String businessPartner;
	
	@Autowired
	private CamelContext context;

	

	@Override
	public void configure() throws Exception {
		
		from("direct:startBusinessPartner").
		         to("bean:serviceGenerator");
	}

	
}
