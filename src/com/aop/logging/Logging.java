package com.aop.logging;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;

import java.util.*;

public class Logging {
	static Logger log = Logger.getLogger(Logging.class);

	public void beforeAdvice(JoinPoint joinPoint) {
		log.debug("Before setting The Method    " +joinPoint.getSignature().getName());
		log.debug(new Date() + ":     " + " " + " To be executing:     " + ""
				+ joinPoint.getSignature().getName());
	}
	/**
	 * This is the method which I would like to execute after a selected method
	 * execution.
	 */
	public void afterAdvice(JoinPoint joinPoint) {

		log.debug("After setting The Method " + new Date() + ":     " + " "
				+ " Excecuted:           " + joinPoint.getSignature().getName());

	}
	/**
	 * This is the method which I would like to execute when any method returns.
	 */
	public void afterReturningAdvice(Object retVal) {

		try {
			log.debug("After Returning from the method  " + new Date() + ":     "
					+ "  Returning:           " + retVal.toString());
		} catch (Exception e) {

		}

	}
	public void AfterThrowingAdvice(JoinPoint joinPoint, Throwable ex) {

		log.debug(new Date() + ":     " + "  Method               "
				+ joinPoint.getSignature().getName() + "         exception "
				+ ex.toString());// +" "+ new new Date()() );

	}
}