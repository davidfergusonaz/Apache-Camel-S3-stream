package com.sap.datacloud.routes;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sap.datacloud.dao.FileProcessDAO;
import com.sap.datacloud.model.FileProcessDetails;

@Component("filePostProcessor")
public class FilePostProcessor implements Processor {
	
	
	@Autowired
	FileProcessDAO fileProcessDAO;

	@Override
	public void process(Exchange exchange) throws Exception {
		FileProcessDetails fileProcessDetails = (FileProcessDetails) exchange.getProperty("FileProcessDetails");
		fileProcessDAO.updateProcessTable(fileProcessDetails, "COMPLETE", "INPROGRESS");
		
		
		
	}

}
