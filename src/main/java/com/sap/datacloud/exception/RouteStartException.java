package com.sap.datacloud.exception;

public class RouteStartException extends RuntimeException{
	

	private static final long serialVersionUID = 1L;
	
	public RouteStartException() {
        super();
    }
    public RouteStartException(String s) {
        super(s);
    }
    public RouteStartException(String s, Throwable throwable) {
        super(s, throwable);
    }
    public RouteStartException(Throwable throwable) {
        super(throwable);
    }

}
