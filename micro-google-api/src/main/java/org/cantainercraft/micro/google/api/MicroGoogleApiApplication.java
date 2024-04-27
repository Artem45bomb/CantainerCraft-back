package org.cantainercraft.micro.google.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication(scanBasePackages = "org.cantainercraft.micro.google.api")
@EnableDiscoveryClient
public class MicroGoogleApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroGoogleApiApplication.class, args);
	}

}
