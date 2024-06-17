package org.containercraft.servicedbstore.service.impl;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.service.message.MessageRequest;
import com.service.message.MessageResponse;
import com.service.message.MessageServiceGrpc;
import com.service.message.TypeChat;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.containercraft.servicedbstore.entity.Message;
import org.containercraft.servicedbstore.service.GrpcMessageService;
import org.springframework.stereotype.Service;

@Service
public class GrpcMessageServiceImpl implements GrpcMessageService {


    @GrpcClient("message")
    private MessageServiceGrpc.MessageServiceBlockingStub messageService;


    @Override
    public MessageResponse sendMessage(Message message){

        MessageRequest messageRequest = MessageRequest.newBuilder()
                .setChatId(message.getChatId().toString())
                .setDate(message.getDate().toString())
                .setClientId(message.getClientId().toString())
                .setIsPinned(message.getIsPinned())
                .setUserId(message.getUserId())
                .setType(TypeChat.valueOf(message.getType().toString()))
                .setText(message.getText())
                .build();


        return messageService.saveMessage(messageRequest);
    }
}
