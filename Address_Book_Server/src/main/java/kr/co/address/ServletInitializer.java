package kr.co.address;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

// SpringBootServletInitializer을 상속하는 것은 Tomcat 같은 Servlet Container환경에서 boot가 동작가능하도록 구성한다는 의미
	// ->war로 배포하기위함 
public class ServletInitializer extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(AddressBookServerApplication.class);
	}

}
