package com.sap.datacloud.exception;

public class FTPEndpointCreationException extends DataCloudEndpointCreationExcepton{
	
	 
	private static final long serialVersionUID = 1L;
	
	public FTPEndpointCreationException(String s) {
	        super(s);
	    }
	    public FTPEndpointCreationException(String s, Throwable throwable) {
	    	
	        super(s, throwable);
	    }

}
