package org.containercraft.streaming.endpoint;

import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.integration.annotation.ServiceActivator;

@MessageEndpoint
public class TCPMessageChanelImage {

    @ServiceActivator(inputChannel = "server-chanel")
    public void imageChannel(byte[] bytes){
        System.out.println(new String(bytes));
    }
}
