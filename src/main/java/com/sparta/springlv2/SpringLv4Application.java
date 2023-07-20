package com.sparta.springlv2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class) // Spring Security 인증 기능 제외
@EnableJpaAuditing
public class SpringLv4Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringLv4Application.class, args);
	}

}
