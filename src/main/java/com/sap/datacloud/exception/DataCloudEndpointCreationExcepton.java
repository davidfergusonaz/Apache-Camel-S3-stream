package com.sap.datacloud.exception;

public class DataCloudEndpointCreationExcepton extends RuntimeException {
	
	
	private static final long serialVersionUID = 1L;
	
	public DataCloudEndpointCreationExcepton() {
        super();
    }
    public DataCloudEndpointCreationExcepton(String s) {
        super(s);
    }
    public DataCloudEndpointCreationExcepton(String s, Throwable throwable) {
        super(s, throwable);
    }
    public DataCloudEndpointCreationExcepton(Throwable throwable) {
        super(throwable);
    }

}
