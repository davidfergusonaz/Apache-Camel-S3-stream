package com.sap.datacloud.dao;

import com.sap.datacloud.model.FileProcessDetails;

public interface RepositoryDAO {
	
	public boolean isFilePresent(FileProcessDetails fileProcessDetails);

}
