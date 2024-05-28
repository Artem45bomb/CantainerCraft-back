package org.containercraft.streaming.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.ip.tcp.TcpInboundGateway;
import org.springframework.integration.ip.tcp.TcpOutboundGateway;
import org.springframework.integration.ip.tcp.TcpSendingMessageHandler;
import org.springframework.integration.ip.tcp.connection.*;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;

@Configuration
public class TCPChanelConfig {
    @Value("${tcp.connection.port}")
    private Integer tcpPortConnection;

    @MessagingGateway(defaultRequestChannel="toTcp")
    public interface Gateway {

        String viaTcp(String in);

    }
    @Bean
    public AbstractServerConnectionFactory serverConnectionFactory() {
        TcpNetServerConnectionFactory serverConnectionFactory = new TcpNetServerConnectionFactory(tcpPortConnection);
        return serverConnectionFactory;
    }

    @Bean AbstractClientConnectionFactory clientConnectionFactory(){
        var clientCF = new TcpNetClientConnectionFactory("localhost",tcpPortConnection);
        return clientCF;
    }
    @Bean
    @ServiceActivator(inputChannel="toTcp")
    public MessageHandler messageHandler(AbstractClientConnectionFactory connectionFactory){
        var messageHandler = new TcpOutboundGateway();
        messageHandler.setConnectionFactory(connectionFactory);
        messageHandler.setOutputChannelName("serverChannel");
        return messageHandler;
    }

    @Bean
    public MessageChannel inboundChannel() {
        return new DirectChannel();
    }

    @Bean
    public TcpInboundGateway inboundGateway(AbstractServerConnectionFactory connectionFactory,
                                            MessageChannel inboundChannel) {
        TcpInboundGateway tcpInboundGateway = new TcpInboundGateway();
        tcpInboundGateway.setConnectionFactory(connectionFactory);
        tcpInboundGateway.setRequestChannel(inboundChannel);
        return tcpInboundGateway;
    }
}
