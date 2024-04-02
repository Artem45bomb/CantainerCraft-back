package ru.javabegin.backend.todo.eurekaclient;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication(scanBasePackages = "ru.javabegin.backend.todo.eurekaclient")
@EnableDiscoveryClient
public class EurekaClientApplication {


    public static void main(String[] args) {

        SpringApplication.run(EurekaClientApplication.class, args);
    }

}
