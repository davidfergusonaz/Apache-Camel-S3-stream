package com.sap.datacloud.aspect;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class OnboardLoggingAspect {
	
	
	private static final Logger LOGGER = LogManager.getLogger(OnboardLoggingAspect.class);
	private static final String NULL = "null";
	private static final String COMMA = ",";

	
	/**
	 * This method is defines point cut expression for all advices defined in logging aspect class.
	 * 
	 * It excludes constant classes , dto classes and exception classes from logging.
	 * 
	 * @param
	 * @return
	 * @throws
	 * 
	 * 
	 */
	@Pointcut("within(com.sap.datacloud..*)"
			+ " && !within(com.sap.datacloud.main..*)"
			+ " && !within(com.sap.datacloud.util..*)"
			+ " && !within(com.sap.datacloud.model..*)"
			+ " && !within(com.sap.datacloud.exception..*)")
	public void logAllMethods(){
		
		/**
		 * This method is kept empty because it is a point cut expression method.
		 * 
		 */
		
	}
	
	
	/**
	 * Method to log entry of a method with arguments passed.
	 * This method captures entry point of all methods defines as per pointcut expression.
	 * This log is printed in proxy-debug logs , If debug level is changed from debug to error these logs will stop printing.
	 * 
	 * 
	 * @param jp
	 * @return
	 * @throws
	 * 
	 */
    @Before("logAllMethods()")
    public void loggerMethodAtEntry(JoinPoint jp){
    	LOGGER.debug("Executing method {} . {}   with args= {}",jp.getTarget().getClass().getName(), jp.getSignature().getName(), logArgs(jp));
    	
    }
    
   
	/**
	 * Method to log exit of a method with arguments passed.
	 * This method captures exit point of all methods defines as per pointcut expression.
	 * This log is printed in console logs , If debug level is changed from debug to error these logs will stop printing.
	 * 
	 * 
	 * @param jp
	 * @return
	 * @throws
	 * 
	 */
    @AfterReturning("logAllMethods()") 
    public void logMethodAfterExecComplete(JoinPoint jp){
    	LOGGER.debug("Executed method {} . {}   with args= {}",jp.getTarget().getClass().getName(),jp.getSignature().getName(),logArgs(jp));
    	
    }
    
    /**
     * Method to return input arguments of a method as simple string.
     * 
     * @param jp
     * @return
     */
    public String logArgs(JoinPoint jp){
    	
    	StringBuilder builder = new StringBuilder();
    	if(jp.getArgs()!=null && jp.getArgs().length>0){
    		
    		for(Object object : jp.getArgs()){
    			
    			builder.append(object!=null?object.toString():NULL);
    			builder.append(COMMA);
    		}
		
    		
    	}
    	
    	return builder.toString();
    }
    
    
	/**
	 * Method to log failure of a method with arguments passed.
	 * This method captures failure point of all methods defines as per pointcut expression.
	 * This log is printed in console logs , These logs are important for finding failure point in application.
	 * 
	 * 
	 * @param jp ,error
	 * @return
	 * @throws
	 * 
	 */
    @AfterThrowing(pointcut ="logAllMethods()", throwing="error")
    public void logMethodMethodFailure(JoinPoint jp, Throwable error){
    	LOGGER.error("Error occured in method {}  .   {} ",jp.getTarget().getClass().getName(),jp.getSignature().getName());
    	LOGGER.error("Error details: {}",error.getMessage());
    }

}
