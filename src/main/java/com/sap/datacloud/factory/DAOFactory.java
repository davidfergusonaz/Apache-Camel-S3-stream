package com.sap.datacloud.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.sap.datacloud.dao.DataCloudOnboardDAO;

@Component
public class DAOFactory {
	
	@Value("${onboard.database}")
	private String dataBaseType;
	
	@Autowired
	@Qualifier("hana")
	private DataCloudOnboardDAO dataCloudOnboardDAO;
	
	public DataCloudOnboardDAO getInstance(){
		
		if("hana".equals(dataBaseType)){
			return dataCloudOnboardDAO;
		}
		else 
			return null;
	}

}
