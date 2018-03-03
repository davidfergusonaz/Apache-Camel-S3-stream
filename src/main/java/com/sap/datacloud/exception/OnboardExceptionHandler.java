package com.sap.datacloud.exception;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.sap.datacloud.model.OnboardErrorResponse;

@Component
@ControllerAdvice
public class OnboardExceptionHandler extends ResponseEntityExceptionHandler {
	
	
	@Value("${route.create.errmessage}")
	private String routeCreateErrMessage;

	@Value("${route.stop.errmessage}")
	private String routeStopErrMessage;

	@Value("${route.start.errmessage}")
	private String routeStartErrMessage;
	
	@Value("${route.internal.errmsg}")
	private String internalServerErrMsg;
	

	/**
	 * This method catches all Exception originated and convert them to error dto object. 
	 * 
	 * @param e
	 * @return
	 */
	@ExceptionHandler({ Exception.class })
	@ResponseBody
	public ResponseEntity<OnboardErrorResponse> handleAnyException(Exception e) {
		
		return response(new OnboardErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), internalServerErrMsg), HttpStatus.INTERNAL_SERVER_ERROR);
		
	}
	
	/**
	 * This method catches  RouteStartException originated and convert them to error dto object. 
	 * 
	 * @param e
	 * @return
	 */
	@ExceptionHandler({ RouteStartException.class })
	@ResponseBody
	public ResponseEntity<OnboardErrorResponse> handleRouteStartException(RouteStartException e) {
		
		return response(new OnboardErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(),routeStartErrMessage), HttpStatus.INTERNAL_SERVER_ERROR);
		
	}
	
	
	/**
	 * This method catches  RouteStartException originated and convert them to error dto object. 
	 * 
	 * @param e
	 * @return
	 */
	@ExceptionHandler({ RouteStopException.class })
	@ResponseBody
	public ResponseEntity<OnboardErrorResponse> handleRouteStopException(RouteStopException e) {
		
		return response(new OnboardErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(),routeStopErrMessage), HttpStatus.INTERNAL_SERVER_ERROR);
		
	}
	
	/**
	 * This method catches  RouteStartException originated and convert them to error dto object. 
	 * 
	 * @param e
	 * @return
	 */
	@ExceptionHandler({ DataCloudEndpointCreationExcepton.class })
	@ResponseBody
	public ResponseEntity<OnboardErrorResponse> handleRouteCreationException(DataCloudEndpointCreationExcepton e) {
		
		return response(new OnboardErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(),routeCreateErrMessage), HttpStatus.INTERNAL_SERVER_ERROR);
		
	}
	
	/**
	 * This method converts error dto object to response entity object to be returned
	 * as api response.
	 * 
	 * @param body
	 * @param status
	 * @return
	 */
	protected <T> ResponseEntity<T> response(T body, HttpStatus status) {
		return new ResponseEntity<>(body, new HttpHeaders(), status);
	}

}
