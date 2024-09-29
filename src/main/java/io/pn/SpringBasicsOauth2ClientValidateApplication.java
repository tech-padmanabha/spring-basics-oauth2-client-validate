package io.pn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class SpringBasicsOauth2ClientValidateApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBasicsOauth2ClientValidateApplication.class, args);
	}

	
	@Bean
	RestTemplate restTemplate() {
		
		return new RestTemplate();
	}
}
