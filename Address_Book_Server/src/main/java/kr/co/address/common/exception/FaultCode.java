package kr.co.address.common.exception;

import org.springframework.http.HttpStatus;

public enum FaultCode {

	 SUCCESS(HttpStatus.OK ,"ADR-200", "Success")
	, NOT_FOUND(HttpStatus.NOT_FOUND ,"ADR-404", "Url Not Found")
	, INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR ,"ADR-500", "Internal Server Error")
	, AUTH_ERROR(HttpStatus.BAD_REQUEST ,"ADR-403", "Auth Error")
	, URL_AUTH_DENINED(HttpStatus.BAD_REQUEST ,"ADR-403-404", "Url Auth Denined")
	, MANDANTORY_ERROR(HttpStatus.BAD_REQUEST ,"ADR-400-1000", "{key} Value is Mandantory")
	, INVALID_FORMAT(HttpStatus.BAD_REQUEST ,"ADR-400-2000", "{key} Value is Invalid format : {Data Format}")
	, LENGTH_INVALID(HttpStatus.BAD_REQUEST ,"ADR-400-3000", "{key} Value lenth is invalid : min : %d ")
	, INVALID_CONNECT(HttpStatus.BAD_REQUEST, "ADR-42000", " INVALID CONNECT")
	, INVLID_REQUEST(HttpStatus.BAD_REQUEST, "PTNR-400-001", "INVLID_REQUEST")	
	;

	private final String code;
	private final HttpStatus status;
	private final String description;

	private FaultCode(HttpStatus status, String code, String description) {
		this.status = status;
		this.code = code;
		this.description = description;
	}

	public String getCode() {
		return code;
	}

	public String getDescription() {
		return description;
	}

	public String toString() {

		return code + ": " + description;
	}

	/**
	 * @return the status
	 */
	public HttpStatus getStatus() {
		return status;
	}

}

