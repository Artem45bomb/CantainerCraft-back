package org.containercraft.streaming.endpoint;

import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.annotation.Transformer;


@MessageEndpoint
public class TCPMessageHandlerEndpoint {

    @Transformer(inputChannel="inboundChannel", outputChannel="toTCPMessageHandlerEndpoint")
    public String convert(byte[] bytes) {
        return new String(bytes);
    }

    @ServiceActivator(inputChannel="toTCPMessageHandlerEndpoint")
    public String upCase(String in) {
        return in.toUpperCase();
    }

    @Transformer(inputChannel="serverChannel")
    public String convertResult(byte[] bytes) {
        return new String(bytes);
    }
}