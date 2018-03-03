package com.sap.datacloud.util;

public class AppConstants {
	
	public static final String FORMAT = ".format";
	public static final String DOT = ".";
	public static final String INSERT_INTO = "INSERT INTO ${table}";
	public static final String UPDATE_TABLE = "UPDATE ${table} SET";
	public static final String SELECT_FROM = 	"SELECT ${selectColumns} FROM ${table}";
	public static final String SELECT_COLUMN = 	"${selectColumns}";
	public static final String TABLE = 	"${table}";
	public static final String ASTERISK = "*";
	public static final String AND = "AND";
	


	public static final String SPACE = " ";
	public static final String LEFT_BRACE = "(";
	public static final String RIGHT_BRACE = ")";
	public static final String COMMA = ",";
	public static final String VALUES = "VALUES";
	public static final String PLACE_HOLDER = "?";
	public static final String WHERE = "where";
	public static final String SINGLE_QUOTE = "'";
	
    public static final String ESCAPE = "\\";
    public static final String ZEROES = "000";
    public static final String UNDERSCORE = "_";
    
    public static final String BASIC = "basic";
    public static final String USERNAME = "username";
    public static final String FTP = "ftp";
    public static final String AMPERSAND = "&";
    public static final String PASSWORD = "password";
    public static final String EQUALS = "=";
    
    public static final String PROVIDER_TO_S3 = "ftpToS3";
    public static final String ID_CAPS = "ID";
    public static final String INCLUDE = "include";
    public static final String PIPE = "|";
    public static final String COLON = ":";
    
    public static final String FILE_PROCESS_DETAILS = "FileProcessDetails";
    public static final String CAMEL_FILE_NAME = "CamelFileName";
    public static final String CAMEL_FILE_LENGTH = "CamelFileLength";
    public static final String AWS_PREFIX = "AwsKeyPrefix";
    public static final String AWS_ENDPOINT = "bean:s3Uploader?method=uploadToAWS";
    public static final String REPOSITORY_ENDPOINT = "bean:camelPersistenceRepository?method=checkForAlreadyProcessedFile";
    public static final String FTP_PROPERTY_GROUP_KEY = "ftp.endpoint.";

}
