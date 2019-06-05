package kr.co.address.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * @author 		minjae_kang
 * @Date  	 	2019. 5. 17. 
 * @description Http Request Logging Interceptor		
 */
public class RequestLoggingInterceptor extends HandlerInterceptorAdapter{
	
	Logger logger = LoggerFactory.getLogger(RequestLoggingInterceptor.class);
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		logger.info("------------------------------------------------------------------------------");
		logger.info("req url : {}", request.getRequestURL());
		logger.info("------------------------------------------------------------------------------");
		
		return super.preHandle(request, response, handler);
	}
}
