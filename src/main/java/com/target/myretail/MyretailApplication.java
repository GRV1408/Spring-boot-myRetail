package com.target.myretail;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class MyretailApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyretailApplication.class, args);
	}

}
