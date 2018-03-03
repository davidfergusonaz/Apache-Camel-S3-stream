package com.sap.datacloud.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.sap.datacloud.dao.DataCloudOnboardDAO;
import com.sap.datacloud.dao.FileProcessDAO;
import com.sap.datacloud.dao.RepositoryDAO;

@Component("hana")
public class DataCloudHANADAOImpl implements DataCloudOnboardDAO{
	
	@Autowired
	private FileProcessDAO fileProcessDAO;

	
	@Autowired
	private RepositoryDAO repositoryDAO;

	@Override
	public FileProcessDAO getFileProcessDAO() {
		return fileProcessDAO;
	}

	@Override
	public RepositoryDAO getRepositoryDAO() {
		
		return repositoryDAO;
	}

}
