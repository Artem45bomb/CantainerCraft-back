package org.containercraft.servicedbstore.service;

import com.service.message.MessageRequest;
import com.service.message.MessageResponse;
import org.containercraft.servicedbstore.entity.Message;

public interface GrpcMessageService {
    MessageResponse sendMessage(Message message);
}
