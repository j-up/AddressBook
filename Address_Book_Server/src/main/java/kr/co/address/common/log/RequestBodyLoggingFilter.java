package kr.co.address.common.log;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

/**
 * <pre>
 * http 통신 시작 시 로직 진입 전 request body 추출용 filter.
 * </pre>
 *
 * Created on 2019. 01. 14.
 * 
 */

public class RequestBodyLoggingFilter implements Filter {
	
	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		try {
			RequestBodyWrapper wrapper = new RequestBodyWrapper((HttpServletRequest)request);
			wrapper.setAttribute("requestBody", wrapper.getRequestBody());
			chain.doFilter(wrapper, response);
		} catch (Exception e) {
			chain.doFilter(request, response);
		}
	}

}