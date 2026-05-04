package com.blog.eureka.EurekaServerApplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;


@SpringBootApplication
public class EurekaServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(EurekaServerApplication.class, args);
		System.out.println("\n====================================================");
		System.out.println("✓ Eureka Server Started Successfully!");
		System.out.println("✓ Access Dashboard: http://localhost:8761");
		System.out.println("✓ Health Check: http://localhost:8761/actuator/health");
		System.out.println("====================================================\n");
	}

}
