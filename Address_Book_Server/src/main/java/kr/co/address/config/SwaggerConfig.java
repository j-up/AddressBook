package kr.co.address.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @description API 시각화 처리
 * 				/설정path/swagger-ui.html 로 접근가 		
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select() // ApiSelectorBuilder 인스턴스를 리턴 -> 스웨거에 의해 노출되는 단을 제어하는 하나의 방법 
				.apis(RequestHandlerSelectors.basePackage("kr.co.address.controller"))
				.paths(PathSelectors.any())
				.build();
	}
}
