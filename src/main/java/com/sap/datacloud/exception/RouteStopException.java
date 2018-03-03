package com.sap.datacloud.exception;

public class RouteStopException extends RuntimeException{
	

	private static final long serialVersionUID = 1L;
	
	public RouteStopException() {
        super();
    }
    public RouteStopException(String s) {
        super(s);
    }
    public RouteStopException(String s, Throwable throwable) {
        super(s, throwable);
    }
    public RouteStopException(Throwable throwable) {
        super(throwable);
    }

}
