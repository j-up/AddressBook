package kr.co.address.common.interceptor;

import java.util.Base64;
import java.util.Base64.Decoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import kr.co.address.common.exception.FaultCode;
import kr.co.address.common.exception.HMException;
import kr.co.address.domain.auth.AuthVO;
import kr.co.address.service.auth.AuthService;


/**
 * <pre>
 * Basic Auth 값 비교를 위한 Interceptor
 * </pre>
 *
 */


public class HeaderInterceptor implements HandlerInterceptor {
	private Logger logger = LoggerFactory.getLogger(HeaderInterceptor.class);
	
	@Autowired
	AuthService authService;
	/**
	 * Basic Auth 값 DB 비교용 Service
	 */
	
	
	/**
	 * 헤더 값 사전 검사
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		logger.info("------------------------------------------------------------------------------");
		logger.info("header check");
		//필수 Header 값 검사. 
		checkContentType(request);
		checkAuthorization(request);
		logger.info("------------------------------------------------------------------------------");
		return HandlerInterceptor.super.preHandle(request, response, handler);
	}

	private void checkContentType(HttpServletRequest request) {
		String contentType = request.getHeader("Content-Type");

		if (contentType == null) {
			throw new HMException(FaultCode.NO_CONTENT);
		} else if (!"application/json".equals(contentType)) {
			throw new HMException(FaultCode.NO_CONTENT);
		}
	}
	
	/**
	 * @description Authorization를 Parsing 후 검사
	 * @param String
	 * @return boolean
	 */
	private void checkAuthorization(HttpServletRequest request) throws Exception {
		boolean checkString;
		String Authorization = request.getHeader("Authorization");
		String[] arrAuth;
		
		if (Authorization == null) {
			throw new HMException(FaultCode.INVLID_AUTHORIZATION);
		}
		if (Authorization.isEmpty()) {
			throw new HMException(FaultCode.INVLID_AUTHORIZATION);
		}

		arrAuth = Authorization.split(" ");
		checkString = arrAuth[0].equals("Basic") && arrAuth.length == 2;

		if (!checkString){
			throw new HMException(FaultCode.INVLID_AUTHORIZATION);			
		}
		if (arrAuth.length < 2) {
			throw new HMException(FaultCode.INVLID_AUTHORIZATION);
		}
		if (!chcekBasicAuth(decodeAuthorization(arrAuth[1]))){
			throw new HMException(FaultCode.INVLID_AUTHORIZATION);
		}
	}

	/**
	 * @description base64 형태 decode
	 * @param String
	 * @return String
	 */
	private String decodeAuthorization(String base64) throws Exception {
		Decoder decoder = Base64.getDecoder();
		byte[] decodedByte = decoder.decode(base64);

		String decodeStr = new String(decodedByte, "UTF-8");
		boolean chcekString = decodeStr.contains(":") && decodeStr.split(":").length == 2;

		return chcekString ? decodeStr : null;
	}

	/**
	 * @description DB에서 Id와 Key 일치 검사
	 * @param String
	 * @return boolean
	 */
	private boolean chcekBasicAuth(String idKey) {
		
		AuthVO param = new AuthVO();
		
		if (idKey == null)
			return false;
		if (idKey.isEmpty())
			return false;
		
		String[] partner = idKey.split(":");
		param.setBasic_auth_id(partner[0]);
		param.setBasic_auth_pw(partner[1]);

		return StringUtils.isEmpty(authService.checkAuthKey(param)) ? false : true;
	}

}
