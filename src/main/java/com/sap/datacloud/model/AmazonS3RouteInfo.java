package com.sap.datacloud.model;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;


public class AmazonS3RouteInfo {
	
	@Value("${s3.credential.accesskey}")
	private String accessKey;
	
	@Value("${s3.credential.secretKey}")
	private String secretKey;
	
	@Value("${s3.proxy.host}")
	private String proxyHost;
	
	@Value("${s3.proxy.port}")
	private String proxyPort;
	
	@Value("${s3.max.retry}")
	private String maxretry;
	
	@Value("${s3.socket.timeout}")
	private String socketTimeout;
	
	@Value("${s3.connection.idle}")
	private String maxIdleInMillis;
	
	@Value("${s3.bucket.location}")
	private String bucketLocation;
	
	
	
	public String getS3Uri(){
		return "aws-s3://"+bucketLocation+"?AmazonS3Client=#s3Client&delay=5000&maxMessagesPerPoll=5&deleteAfterWrite=false";
	}
	
	
	
	@Bean
	public AmazonS3 s3Client(){
		 AWSCredentials awsCredentials = new BasicAWSCredentials("AKIAJJ2LKPRVRKEDGFZQ", "zJLqWEqCZg072cTVn6vXHvWMN2xqBcMPDSRALtIm");
	     
	     ClientConfiguration clientConfiguration = new ClientConfiguration();

	     clientConfiguration.setConnectionTimeout(6000);
	     clientConfiguration.setMaxErrorRetry(3);
	     clientConfiguration.setSocketTimeout(30000);
	     clientConfiguration.withConnectionMaxIdleMillis(6000);
	      
	     return new AmazonS3Client(awsCredentials, clientConfiguration);
	     
	}

}
