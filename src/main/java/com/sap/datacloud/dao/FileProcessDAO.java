package com.sap.datacloud.dao;

import com.sap.datacloud.model.FileProcessDetails;

public interface FileProcessDAO {
	
	public void updateProcessTable(FileProcessDetails fileProcessDetails, String newStatus, String oldStatus);
	
	public void insertFileDetails(FileProcessDetails fileProcessDetails, String ...status);

}
