package org.containercraft.servicedbstore;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.function.StreamBridge;

@SpringBootApplication(scanBasePackages = "org.containercraft.servicedbstore")
public class ServiceDbStoreApplication {
	@Autowired
	private StreamBridge streamBridge;

	public static void main(String[] args) {
		SpringApplication.run(ServiceDbStoreApplication.class, args);
	}

}
