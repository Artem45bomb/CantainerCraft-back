package org.containercraft.streaming.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.ip.tcp.TcpReceivingChannelAdapter;
import org.springframework.integration.ip.tcp.connection.TcpNetClientConnectionFactory;
import org.springframework.integration.ip.tcp.serializer.ByteArraySingleTerminatorSerializer;

@Configuration
public class TCPChannelConfig {
    @Value("${tcp.connection.port}")
    private Integer tcpPortConnection;

    @Bean
    public TcpReceivingChannelAdapter tcpReceivingChannelAdapter(){
        var tcpReceivingChannelAdapter = new TcpReceivingChannelAdapter();
        tcpReceivingChannelAdapter.setConnectionFactory(tcpNetClientConnectionFactory());
        tcpReceivingChannelAdapter.setClientMode(true);
        tcpReceivingChannelAdapter.setOutputChannelName("server-chanel");
        return tcpReceivingChannelAdapter;
    }

    @Bean
    public TcpNetClientConnectionFactory tcpNetClientConnectionFactory(){
        var tcpClientConnectionFactory = new TcpNetClientConnectionFactory("localhost",tcpPortConnection);
        tcpClientConnectionFactory.setDeserializer(new ByteArraySingleTerminatorSerializer((byte) '|'));
        return tcpClientConnectionFactory;
    }
}
