package org.cantainercraft.messenger.service.impl;

import lombok.RequiredArgsConstructor;
import org.cantainercraft.messenger.dto.MessageDTO;
import org.cantainercraft.messenger.dto.MessageSearchDTO;
import org.cantainercraft.messenger.service.MessageService;
import org.cantainercraft.messenger.webclient.MessageClient;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {
    private final MessageClient messageClient;


    @Override
    public MessageDTO findById(UUID uuid){
        return messageClient.findById(uuid);
    }

    @Override
    public MessageDTO save(MessageDTO messageDTO){
        return messageClient.save(messageDTO);
    }
    @Override
    public Flux<MessageDTO> asyncSave(MessageDTO messageDTO){
        return messageClient.asyncSave(messageDTO);
    }

    @Override
    public Flux<Boolean> deleteByClientId(UUID uuid){
        return messageClient.deleteByClientId(uuid);
    }

    @Override
    public Flux<MessageDTO> update(MessageDTO messageDTO){
        return messageClient.update(messageDTO);
    }


    @Override
    public List<MessageDTO> findBySearch(MessageSearchDTO searchDTO){
        return messageClient.findBySearch(searchDTO);
    }


}
