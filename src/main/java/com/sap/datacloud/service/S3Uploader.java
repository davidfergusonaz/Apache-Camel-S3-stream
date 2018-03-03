package com.sap.datacloud.service;

import java.io.InputStream;

import org.apache.camel.Exchange;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.amazonaws.AmazonClientException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.transfer.TransferManager;
import com.amazonaws.services.s3.transfer.Upload;
import com.sap.datacloud.exception.DataCloudEndpointCreationExcepton;
import com.sap.datacloud.factory.DAOFactory;
import com.sap.datacloud.model.FileProcessDetails;
import com.sap.datacloud.model.StatusType;
import static com.sap.datacloud.util.AppConstants.CAMEL_FILE_LENGTH;
import static com.sap.datacloud.util.AppConstants.CAMEL_FILE_NAME;
import static com.sap.datacloud.util.AppConstants.AWS_PREFIX;
import static com.sap.datacloud.util.AppConstants.FILE_PROCESS_DETAILS;

/**
 * This class uploads file to amazon simple storage system (S3) from different
 * camel endpoint
 * 
 * @author I339480
 *
 */
@Component("s3Uploader")
public class S3Uploader {

	@Value("${s3.credential.accesskey}")
	private String accessKey;

	@Value("${s3.credential.secretKey}")
	private String secretKey;

	@Value("${s3.bucket.location}")
	private String bucketLocation;

	@Autowired
	private DAOFactory onboardDAOFactory;


	private static final Logger LOGGER = LogManager.getLogger(S3Uploader.class);

	/**
	 * This method uploads file from different provider endpoints to amazon s3.
	 * 
	 * @param exchange
	 */
	@Transactional
	public void uploadToAWS(Exchange exchange) {

		try {

			FileProcessDetails fileProcessDetails = (FileProcessDetails) exchange.getProperty(FILE_PROCESS_DETAILS);

			InputStream is = exchange.getIn().getBody(InputStream.class);

			String fileName = (String) exchange.getIn().getHeader(CAMEL_FILE_NAME);
			long fileSize = (long) exchange.getIn().getHeader(CAMEL_FILE_LENGTH);

			String key = exchange.getProperty(AWS_PREFIX) + fileName;

			LOGGER.info("Inserting  file  {}, with status {} in database", fileName, StatusType.NEW.toString());
			onboardDAOFactory.getInstance().getFileProcessDAO().insertFileDetails(fileProcessDetails);
			LOGGER.info("Inserted  file  {}, with status {}  in database. ", fileName, StatusType.NEW.toString());

			AWSCredentials credentials = new BasicAWSCredentials(accessKey, secretKey);

			TransferManager s3transferManager = new TransferManager(credentials);

			ObjectMetadata meta = new ObjectMetadata();

			meta.setContentLength(fileSize);

			PutObjectRequest request = new PutObjectRequest(bucketLocation, key, is, meta);
			LOGGER.info("Inserting  file  {}, with status {} in database. ", fileName,
					StatusType.INPROCESS.toString());
			onboardDAOFactory.getInstance().getFileProcessDAO().updateProcessTable(fileProcessDetails, StatusType.INPROCESS.name(), StatusType.NEW.name());
			LOGGER.info("Inserting  file  {}, with status {} in database. ", fileName,
					StatusType.INPROCESS.toString());
			Upload upload = s3transferManager.upload(request);

			try {

				upload.waitForCompletion();
				LOGGER.info("waiting for file: {} upload in amazon S3",fileName);
				LOGGER.info("Inserting  file  {}, with status {} in database. ", fileName,
						StatusType.COMPLETE.toString());
				onboardDAOFactory.getInstance().getFileProcessDAO().updateProcessTable(fileProcessDetails, StatusType.COMPLETE.name(), StatusType.INPROCESS.name());
				LOGGER.info("Inserting  file  {}, with status {} in database. ", fileName,
						StatusType.COMPLETE.toString());
				s3transferManager.shutdownNow();
			} catch (AmazonClientException | InterruptedException ae) {

				LOGGER.error("exception while uploading to Amazon S3 with message : {}. ", ae.getMessage());
				throw new DataCloudEndpointCreationExcepton(ae.getMessage(), ae);
			}

		} catch (Exception e) {
			LOGGER.error("exception while connection to Amazon S3 with message : {}. ", e.getMessage());
			throw new DataCloudEndpointCreationExcepton(e.getMessage(), e);
		}

	}

	}