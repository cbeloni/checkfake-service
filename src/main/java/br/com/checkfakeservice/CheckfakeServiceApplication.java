package br.com.checkfakeservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class CheckfakeServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CheckfakeServiceApplication.class, args);
	}

}
