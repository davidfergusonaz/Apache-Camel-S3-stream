/**
 * 
 */
package com.sap.datacloud.builder;

import static com.sap.datacloud.util.AppConstants.COMMA;
import static com.sap.datacloud.util.AppConstants.INSERT_INTO;
import static com.sap.datacloud.util.AppConstants.LEFT_BRACE;
import static com.sap.datacloud.util.AppConstants.RIGHT_BRACE;
import static com.sap.datacloud.util.AppConstants.SPACE;
import static com.sap.datacloud.util.AppConstants.VALUES;

import java.util.List;

import org.springframework.stereotype.Component;

/**
 * @author I339480
 *
 */
@Component
public class DatasetQueryBuilder {

	StringBuilder query = new StringBuilder(INSERT_INTO);

	public DatasetQueryBuilder(String queryString) {
		query = new StringBuilder(queryString);

	}

	public DatasetQueryBuilder(){
		// default constrcutor
	}
	public DatasetQueryBuilder addTable(String tableName) {

		query.append(SPACE).append(tableName).append(SPACE);
		return this;
	}

	public DatasetQueryBuilder addLeftBrace() {
		query.append(LEFT_BRACE);
		return this;
	}

	public DatasetQueryBuilder addRightBrace() {

		query.append(RIGHT_BRACE);
		return this;
	}

	public DatasetQueryBuilder addvalue() {
		query.append(SPACE).append(VALUES).append(SPACE);
		return this;
	}

	public DatasetQueryBuilder addComma() {
		query.append(COMMA);
		return this;
	}

	public DatasetQueryBuilder addColumnNames(List<String> columns) {
		for (String column : columns) {
			query.append(column).append(COMMA);
		}
		query.setLength(query.length() - 1);
		return this;
	}

	public DatasetQueryBuilder addValuePlceHolder(int size) {

		for (int i = 0; i < size; i++) {
			query.append("?").append(COMMA);
		}
		query.setLength(query.length() - 1);
		return this;
	}

	public String build() {
		return query.toString();
	}
}
