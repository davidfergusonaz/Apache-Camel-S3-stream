package com.sap.datacloud.exception;

public class DataCloudOnboardException extends Exception{
	

	private static final long serialVersionUID = 1L;
	
	public DataCloudOnboardException() {
        super();
    }
    public DataCloudOnboardException(String s) {
        super(s);
    }
    public DataCloudOnboardException(String s, Throwable throwable) {
        super(s, throwable);
    }
    public DataCloudOnboardException(Throwable throwable) {
        super(throwable);
    }

}
