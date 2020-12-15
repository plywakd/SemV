package com.pai.pai_demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@SpringBootApplication
public class PaiDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(PaiDemoApplication.class, args);
	}

}
