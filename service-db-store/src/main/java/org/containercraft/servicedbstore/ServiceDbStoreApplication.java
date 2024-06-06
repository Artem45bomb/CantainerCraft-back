package org.containercraft.servicedbstore;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.context.annotation.Bean;
import reactor.core.Disposable;
import reactor.core.publisher.Flux;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;

@EnableRabbit
@SpringBootApplication(scanBasePackages = "org.containercraft.servicedbstore")
public class ServiceDbStoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceDbStoreApplication.class, args);
	}

}
