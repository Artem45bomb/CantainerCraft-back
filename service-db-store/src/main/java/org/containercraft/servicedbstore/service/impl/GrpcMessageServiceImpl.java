package org.containercraft.servicedbstore.service.impl;


import com.service.message.MessageRequest;
import com.service.message.MessageResponse;
import com.service.message.MessageServiceGrpc;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.containercraft.servicedbstore.entity.Message;
import org.springframework.stereotype.Service;

@Service
public class GrpcMessageServiceImpl{


    @GrpcClient("message")
    private MessageServiceGrpc.MessageServiceBlockingStub messageService;


    public MessageResponse sendMessage(Message message){

        MessageRequest messageRequest = MessageRequest.newBuilder()
                .setText(message.getText())
                .build();


        MessageResponse messageResponse = messageService.saveMessage(messageRequest);
        System.out.println(messageResponse);
        return  messageResponse;
    }
}
