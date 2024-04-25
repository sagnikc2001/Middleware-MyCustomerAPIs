package com.alahli.middleware.customer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource({"classpath:spring/camel-context.xml"})
public class GetCustomerPfaApplication {

	public static void main(String[] args) {
		SpringApplication.run(GetCustomerPfaApplication.class, args);
	}

}
