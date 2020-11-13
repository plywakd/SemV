package com.pai;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@ComponentScan({"com.pai.repository"})
public class PaiApplication {

	public static void main(String[] args) {
		SpringApplication.run(PaiApplication.class, args);
	}
	@GetMapping("/")
	public String sayHello() {
		return String.format("Hello !");
	}
}
