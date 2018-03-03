package com.sap.datacloud.service;

import org.apache.camel.Exchange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sap.datacloud.dao.RepositoryDAO;
import com.sap.datacloud.factory.DAOFactory;
import com.sap.datacloud.model.FileProcessDetails;
import com.sap.datacloud.util.AppConstants;

@Component("camelPersistenceRepository")
public class CamelPersistenceRepository {
	
	@Autowired
	private DAOFactory dAOFactory;
	
	/**
	 * This method checks if the file is already processed or picked up by 
	 *  camel before,if the file is already picked up then stop the current exchange.
	 * @param exchange
	 */
	public void checkForAlreadyProcessedFile(Exchange exchange){
		
		FileProcessDetails fileProcessDetails = (FileProcessDetails) exchange.getProperty(AppConstants.FILE_PROCESS_DETAILS);
		
		boolean isFilePresent = dAOFactory.getInstance().getRepositoryDAO().isFilePresent(fileProcessDetails);

		if (isFilePresent) {
			exchange.setProperty(Exchange.ROUTE_STOP, Boolean.TRUE);
		}

		
	}

}
