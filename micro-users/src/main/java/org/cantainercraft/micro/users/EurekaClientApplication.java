package org.cantainercraft.micro.users;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "org.cantainercraft.micro.users")
@EnableDiscoveryClient
@EntityScan("org.cantainercraft.project.entity")
public class EurekaClientApplication {


    public static void main(String[] args) {

        SpringApplication.run(EurekaClientApplication.class, args);
    }

}
