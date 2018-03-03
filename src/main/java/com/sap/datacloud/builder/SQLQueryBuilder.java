package com.sap.datacloud.builder;

import static com.sap.datacloud.util.AppConstants.AND;
import static com.sap.datacloud.util.AppConstants.ASTERISK;
import static com.sap.datacloud.util.AppConstants.COMMA;
import static com.sap.datacloud.util.AppConstants.EQUALS;
import static com.sap.datacloud.util.AppConstants.INSERT_INTO;
import static com.sap.datacloud.util.AppConstants.LEFT_BRACE;
import static com.sap.datacloud.util.AppConstants.PLACE_HOLDER;
import static com.sap.datacloud.util.AppConstants.RIGHT_BRACE;
import static com.sap.datacloud.util.AppConstants.SELECT_COLUMN;
import static com.sap.datacloud.util.AppConstants.SELECT_FROM;
import static com.sap.datacloud.util.AppConstants.SINGLE_QUOTE;
import static com.sap.datacloud.util.AppConstants.SPACE;
import static com.sap.datacloud.util.AppConstants.TABLE;
import static com.sap.datacloud.util.AppConstants.UPDATE_TABLE;
import static com.sap.datacloud.util.AppConstants.VALUES;
import static com.sap.datacloud.util.AppConstants.WHERE;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class SQLQueryBuilder {

	StringBuilder query = null;
	
	AtomicInteger updateCounter = new AtomicInteger(0);

	public SQLQueryBuilder(QueryType queryType) {

		if (QueryType.SELECT.equals(queryType)) {
			query = new StringBuilder(SELECT_FROM);
		} else if (QueryType.UPDATE.equals(queryType)) {
			query = new StringBuilder(UPDATE_TABLE);

		} else if (QueryType.INSERT.equals(queryType)) {
			query = new StringBuilder(INSERT_INTO);

		}

	}

	public SQLQueryBuilder select(SelectType selectFrom, String... columns) {

		if (SelectType.ALL.equals(selectFrom)) {
			String queryInString = query.toString().replace(SELECT_COLUMN, ASTERISK);
			query = new StringBuilder(queryInString);
		} else {
			String joined = String.join(COMMA, columns);
			query.append(joined);
		}
		return this;

	}

	public SQLQueryBuilder table(String tableName) {
		String queryString = query.toString().replace(TABLE, tableName);
		query = new StringBuilder(queryString);
		return this;
	}

	public SQLQueryBuilder update(String columnName, String columnValue) {
		int currentVal = updateCounter.get();
		if(currentVal>0){
			query.append(COMMA);
		}
		query.append(SPACE).append(columnName).
		append(EQUALS).append(SINGLE_QUOTE).
		append(columnValue).
		append(SINGLE_QUOTE);
		updateCounter.incrementAndGet();
		return this;
	}

	public SQLQueryBuilder where(String columnName, String value) {

		query.append(SPACE).append(WHERE).append(SPACE).append(columnName).append(EQUALS).append(SINGLE_QUOTE)
				.append(value).append(SINGLE_QUOTE);
		return this;

	}
	
	public SQLQueryBuilder and(String columnName, String value) {

		query.append(SPACE).append(AND).append(SPACE).append(columnName).append(EQUALS).append(SINGLE_QUOTE)
				.append(value).append(SINGLE_QUOTE);
		return this;

	}

	public SQLQueryBuilder columns(List<String> columnNames) {
		query.append(SPACE);
		addLeftBrace();
		for (String column : columnNames) {
			query.append(column).append(COMMA);
		}
		query.setLength(query.length() - 1);
		addRightBrace();
		query.append(SPACE).append(VALUES).append(SPACE);
		addLeftBrace();
		for (int i = 0; i < columnNames.size(); i++) {
			query.append(PLACE_HOLDER).append(COMMA);
		}
		query.setLength(query.length() - 1);
		addRightBrace();
		return this;

	}
	

	private SQLQueryBuilder addLeftBrace() {
		query.append(LEFT_BRACE);
		return this;
	}

	private SQLQueryBuilder addRightBrace() {

		query.append(RIGHT_BRACE);
		return this;
	}
	
	public String build() {
		return query.toString();
	}

	public enum QueryType {
		SELECT, UPDATE, INSERT;
	}
	
	public enum SelectType {
		ALL, FEW; 
	}
}
