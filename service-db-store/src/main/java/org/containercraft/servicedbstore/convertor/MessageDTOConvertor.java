package org.containercraft.servicedbstore.convertor;

import lombok.RequiredArgsConstructor;
import org.cantainercraft.micro.utilits.service.ConvertorDTO;
import org.containercraft.servicedbstore.dto.MessageDTO;
import org.containercraft.servicedbstore.entity.Message;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MessageDTOConvertor implements ConvertorDTO<MessageDTO, Message> {
    private final ModelMapper mapper;

    @Override
    public MessageDTO convertEntityToDTO(Message message) {
        return mapper.map(message,MessageDTO.class);
    }

    @Override
    public Message convertDTOToEntity(MessageDTO messageDTO) {
        return mapper.map(messageDTO, Message.class);
    }
}
