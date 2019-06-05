package kr.co.address;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@EnableAspectJAutoProxy
@SpringBootApplication
@ServletComponentScan // filter 사용 
public class AddressBookServerApplication {
	public static void main(String[] args) {
		SpringApplication.run(AddressBookServerApplication.class, args);
	}
}
