package com.sap.datacloud.dao.impl;

import java.sql.Timestamp;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sap.datacloud.dao.FileProcessDAO;
import com.sap.datacloud.model.FileProcessDetails;
import com.sap.datacloud.model.StatusType;
import com.sap.datacloud.util.AppConstants;
import com.sap.datacloud.builder.DatasetQueryBuilder;
import com.sap.datacloud.builder.SQLQueryBuilder;
import com.sap.datacloud.builder.SQLQueryBuilder.QueryType;

@Component
public class FileProcessDAOImpl implements FileProcessDAO {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Value("${fileprocess.table.name}")
	private String tableName;
	
	@Value("${fileprocess.table.columns}")
	private String columnNames;
	
	@Value("${fileprocess.table.fileName}")
	private String fileNameColumn;
	
	@Value("${fileprocess.table.providerId}")
	private String providerIdColumn;
	
	@Value("${fileprocess.table.domainId}")
	private String domainIdColumn;
	
	@Value("${fileprocess.table.stepType}")
	private String stepTypeColumn;
	
	@Value("${fileprocess.table.status}")
	private String statusColumn;
	
	@Value("${fileprocess.table.startTime}")
	private String startTime;
	
	@Value("${fileprocess.table.inprocessTime}")
	private String inprocessTime;
	
	@Value("${fileprocess.table.completeTime}")
	private String completeTime;

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void updateProcessTable(FileProcessDetails fileProcessDetails, String status, String oldStatus) {
		
		
		
		
		
		String sql = new SQLQueryBuilder(QueryType.UPDATE).table(tableName).
				update(statusColumn, status).
				where(fileNameColumn, fileProcessDetails.getFilename()).  
				and(providerIdColumn, fileProcessDetails.getProviderId()). 
				and(domainIdColumn, fileProcessDetails.getDomainId()). 
				and(stepTypeColumn, fileProcessDetails.getStepType()). 
				and(statusColumn, oldStatus).
				and(getTimestampColumnName(status),new Timestamp(new Date().getTime()).toString()).
				build();
		
		jdbcTemplate.execute(sql);
		
		
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void insertFileDetails(FileProcessDetails fileProcessDetails, String... status) {
		
		String statusType = status.length >1 ? status[0]:StatusType.NEW.toString();
		fileProcessDetails.setStatus(statusType);
		fileProcessDetails.setStepType(AppConstants.PROVIDER_TO_S3);
	
		
		String[] columns = columnNames.split(AppConstants.COMMA);
		
		
		String query = new SQLQueryBuilder(QueryType.INSERT).table(tableName).
				        columns(Arrays.asList(columns)).build();
		
		Object[] params = new Object[]{fileProcessDetails.getProviderId(),fileProcessDetails.getDomainId(),fileProcessDetails.getFilename(),
				fileProcessDetails.getCategory(),AppConstants.PROVIDER_TO_S3,statusType, new Timestamp(new Date().getTime())};
		
		jdbcTemplate.update(query,params);
					
		
	}
	
	protected String getTimestampColumnName(String status) {
		String timeStampColumnName = null;
		if("NEW".equals(status)){
			timeStampColumnName = startTime;
		}else if("INPROCESS".equals(status)){
			timeStampColumnName = inprocessTime;
		}else if("COMPLETE".equals(status)){
			timeStampColumnName = completeTime;
		}
		return timeStampColumnName;
	}
	
}
