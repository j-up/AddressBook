package kr.co.address.common.exception;

import org.springframework.http.HttpStatus;

import kr.co.address.common.utils.StringUtils;

public class ExHandler {
	private static final String ONLY_NUMBER_REG = "\\d+";
	
	public static void exceptionMsg(String msg, FaultCode faultCode) throws HMException {
		if ( StringUtils.isBlank(msg) ) {
			throw new HMException(faultCode);
		}
	}
	
	public static void exceptionMsg(String msg, FaultCode faultCode, String item) throws HMException {
		if ( StringUtils.isBlank(msg) ) {
			throw new HMException(faultCode);
		}
	}
	
	
	public static void exceptionNullMsg(Object msg, String code, String message, HttpStatus status) throws HMException {
		if ( msg == null ) {
			throw new HMException(code, message, status);
		}
		
		
	}
	
	public static void exceptionNullMsg(Object msg,  FaultCode faultCode) throws HMException {
		if ( msg == null ) {
			throw new HMException(faultCode);
		}
		
		
	}
	
	public static void exceptionMsg(Object msg, String code, String message, HttpStatus status) throws HMException {
		if ( msg == null ) {
			throw new HMException(code, message, status);
		}
	}
	
	public static void exceptionEmptyMsg(String msg, FaultCode faultCode) throws HMException {
		if (  StringUtils.isEmpty(msg)  ) {
			throw new HMException(faultCode);
		}
	}
	
	public static void exceptionEmptyMsg(String msg, String code, String message, HttpStatus status) throws HMException {
		if ( StringUtils.isEmpty(msg) ) {
			throw new HMException(code, message, status);
		}
	}
	
	public static void exceptionLimitSize(int compareNum, int limit, FaultCode faultCode) throws HMException {
		if ( compareNum >= limit ) {
			throw new HMException(faultCode);
		}
	}
	
	// Number
	public static void exceptionNumber(String msg) throws HMException {
		if ( msg != null && msg.matches(ONLY_NUMBER_REG) == false ) {
			//throw new HMException(FaultCode.IS_NOT_NUMBER);
		}
	}
	
	// Number
	public static void exceptionNumber(String msg, FaultCode faultCode) throws HMException {
		if ( msg != null && msg.matches(ONLY_NUMBER_REG) == false ) {
			throw new HMException(faultCode);
		}
	}
	
	public static void exceptionIncludeMsg(String[] args, String value, FaultCode faultCode) throws HMException {
		boolean result = false;
		for (String item : args) {
			
			if ( item.equals(value) ) {
				result = true;
			}
		}
		
		if ( !result ) {
			throw new HMException(faultCode);
		}
	}
	
	public static void exceptionDateFormat(String msg) {
		String dateRule = "((19|20)\\d\\d)(0?[1-9]|1[012])(0?[1-9]|[12][0-9]|3[01])([01]?[0-9]|2[0-3])[0-5][0-9][0-5][0-9]";
		
		if(msg.length() != 14) {
			throw new HMException(FaultCode.INVLID_REQUEST);
		}
		if ( !msg.matches(dateRule) ) {
			throw new HMException(FaultCode.INVLID_REQUEST);
		}
		
	}
	
}
