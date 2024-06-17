package org.containercraft.streamredis.service;

import com.service.message.MessageRequest;
import com.service.message.MessageResponse;
import io.grpc.stub.StreamObserver;

public interface GrpcMessageService {
    void saveMessage(MessageRequest request,StreamObserver<MessageResponse> streamObserver);
}
