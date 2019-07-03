package kr.co.address.config;

import java.nio.charset.Charset;

import javax.servlet.Filter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import kr.co.address.common.interceptor.HeaderInterceptor;
import kr.co.address.common.log.RequestBodyLoggingFilter;
import kr.co.address.interceptor.RequestLoggingInterceptor;


@Configuration
@PropertySource(value = { "file:/prop/adr-db-config.properties" })
public class WebMvcConfig implements WebMvcConfigurer{
	
	@Bean
	public HeaderInterceptor headerInterceptor() {
		return new HeaderInterceptor();
	}
	
	@Bean
	public RequestLoggingInterceptor requestLoggingInterceptor() {
		return new RequestLoggingInterceptor();
	}
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new RequestLoggingInterceptor()).addPathPatterns("/**").excludePathPatterns("/resources/**");
		registry.addInterceptor(headerInterceptor()).addPathPatterns("/**").excludePathPatterns("/resources/**");
		
		WebMvcConfigurer.super.addInterceptors(registry);
	}
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {

		registry.addResourceHandler("/resources/**").addResourceLocations("classpath:static/");

		// TODO Auto-generated method stub
		WebMvcConfigurer.super.addResourceHandlers(registry);
	}
	
	
	@Bean
	public HttpMessageConverter<String> responseBodyConverter() {
		return new StringHttpMessageConverter(Charset.forName("UTF-8"));
	}

	@Bean
	public Filter characterEncodingFilter() {
		CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
		characterEncodingFilter.setEncoding("UTF-8");
		characterEncodingFilter.setForceEncoding(true);
		return characterEncodingFilter;
	}
	@Bean
	public Filter requestBodyLoggingFilter() {
		RequestBodyLoggingFilter requestBodyLoggingFilter = new RequestBodyLoggingFilter();
		
		return requestBodyLoggingFilter;
	}
}
