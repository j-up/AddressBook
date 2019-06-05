package kr.co.address.common.exception;

import org.springframework.http.HttpStatus;

public class HMException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7325588672370682543L;

	private String error_code;
	private String error_message;

	private HttpStatus status = HttpStatus.BAD_REQUEST;

	public HMException(String error_code, String error_message, HttpStatus status) {
		this.error_code = error_code;
		this.error_message = error_message;
		this.setStatus(status);
	}
	
	public HMException(String error_code, int i) {
		this.error_code = error_code;
		this.error_message = "BAD_REQUEST";
		this.setStatus(HttpStatus.BAD_REQUEST);
	}

	public HMException(String error_code, String error_message) {
		this.error_code = error_code;
		this.error_message = error_message;
		this.setStatus(FaultCode.INVALID_CONNECT.getStatus());
	}

	public HMException(FaultCode faultCode) {
		// TODO Auto-generated constructor stub
		this.error_code = faultCode.getCode();
		this.error_message = faultCode.getDescription();
		this.setStatus(faultCode.getStatus());
		
	}	
	public HMException(String faultCode) {
		// TODO Auto-generated constructor stub
		this.error_code = faultCode;
		this.error_message = faultCode;
		this.setStatus(FaultCode.INTERNAL_SERVER_ERROR.getStatus());
		
	}

	public HMException(String string, Exception ex) {
		this.error_code = error_code;
		this.error_message = ex.getMessage();
		this.setStatus(HttpStatus.BAD_REQUEST);
	}

	public String getError_code() {
		return error_code;
	}

	public void setError_code(String error_code) {
		this.error_code = error_code;
	}

	public String getError_message() {
		return error_message;
	}

	public void setError_message(String error_message) {
		this.error_message = error_message;
	}

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

}
