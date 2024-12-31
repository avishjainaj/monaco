package com.ecommerce.monaco;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class MonacoApplication {

	public static void main(String[] args) {
		SpringApplication.run(MonacoApplication.class, args);
	}

}
