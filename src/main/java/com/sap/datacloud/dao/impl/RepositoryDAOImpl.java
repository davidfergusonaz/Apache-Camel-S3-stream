package com.sap.datacloud.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.sap.datacloud.builder.SQLQueryBuilder;
import com.sap.datacloud.builder.SQLQueryBuilder.QueryType;
import com.sap.datacloud.builder.SQLQueryBuilder.SelectType;
import com.sap.datacloud.dao.RepositoryDAO;
import com.sap.datacloud.model.FileProcessDetails;
@Component
public class RepositoryDAOImpl implements RepositoryDAO {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Value("${fileprocess.table.name}")
	private String tableName;
	
	@Value("${fileprocess.table.fileName}")
	private String fileNameColumn;
	
	@Value("${fileprocess.table.providerId}")
	private String providerIdColumn;
	
	@Value("${fileprocess.table.domainId}")
	private String domainIdColumn;

	@Override
	public boolean isFilePresent(FileProcessDetails fileDetails) {
			
		String query = new SQLQueryBuilder(QueryType.SELECT).select(SelectType.ALL).table(tableName).where(fileNameColumn, fileDetails.getFilename()). 																				         
									and(domainIdColumn,fileDetails.getDomainId()). 
									and(providerIdColumn,fileDetails.getProviderId()).build();
		List<Map<String,Object>> lst =jdbcTemplate.queryForList(query);
		
		return !lst.isEmpty();
	}
	
	
}
