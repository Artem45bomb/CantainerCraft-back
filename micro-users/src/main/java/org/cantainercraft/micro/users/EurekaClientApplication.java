package org.cantainercraft.micro.users;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication(scanBasePackages = "org.cantainercraft.micro.users")
@EnableDiscoveryClient
@EnableCaching
@EntityScan("org.cantainercraft.project.entity.users")
public class EurekaClientApplication {


    public static void main(String[] args) {
        SpringApplication.run(EurekaClientApplication.class, args);
    }

}
