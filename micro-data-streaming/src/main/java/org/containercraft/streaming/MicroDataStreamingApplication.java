package org.containercraft.streaming;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.config.EnableIntegration;

import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

@EnableIntegration
@SpringBootApplication(scanBasePackages = {"org.containercraft.streaming"})
public class MicroDataStreamingApplication{



    public static void main(String[] args) throws IOException,InterruptedException{



        SpringApplication.run(MicroDataStreamingApplication.class, args);
    }

}



