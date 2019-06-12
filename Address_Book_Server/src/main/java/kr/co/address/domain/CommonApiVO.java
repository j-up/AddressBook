package kr.co.address.domain;

import java.io.Serializable;

/**
 * @description API 응답 공통 멤버		
 */
public class CommonApiVO implements Serializable{
	
	protected String resultCode;
	protected String resultMessage;
	protected String responseDateTime;
	
	/**
	 * @return the resultCode
	 */
	public String getResultCode() {
		return resultCode;
	}
	/**
	 * @param resultCode the resultCode to set
	 */
	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}
	/**
	 * @return the resultMessage
	 */
	public String getResultMessage() {
		return resultMessage;
	}
	/**
	 * @param resultMessage the resultMessage to set
	 */
	public void setResultMessage(String resultMessage) {
		this.resultMessage = resultMessage;
	}
	/**
	 * @return the responseDateTime
	 */
	public String getResponseDateTime() {
		return responseDateTime;
	}
	/**
	 * @param responseDateTime the responseDateTime to set
	 */
	public void setResponseDateTime(String responseDateTime) {
		this.responseDateTime = responseDateTime;
	}
	
	public void setSuccess() {
		this.setResultCode("ADR-200");
		this.setResultMessage("Success");
	}
	
}
