package org.containercraft.streamredis.service.impl;

import com.service.message.MessageRequest;
import com.service.message.MessageResponse;
import com.service.message.MessageServiceGrpc;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.containercraft.streamredis.service.GrpcMessageService;

import java.util.UUID;

@GrpcService
public class GrpcMessageServiceImpl extends MessageServiceGrpc.MessageServiceImplBase implements GrpcMessageService {

    @Override
    public void saveMessage(MessageRequest request, StreamObserver<MessageResponse> streamObserver){

        MessageResponse response = MessageResponse.newBuilder()
                        .setIsSaveMessage(true)
                        .build();

        streamObserver.onNext(response);
        streamObserver.onCompleted();
    }
}
