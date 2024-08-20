package org.containercraft.servicefilemanager.convertor;

import lombok.RequiredArgsConstructor;
import org.containercraft.servicefilemanager.dto.ContentDTO;
import org.containercraft.servicefilemanager.entity.Content;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
<<<<<<< HEAD:micro-chats/src/main/java/org/cantainercraft/micro/chats/convertor/ContentDTOConvertor.java
import org.cantainercraft.micro.chats.dto.ContentDTO;
import org.cantainercraft.project.entity.chats.Content;
=======
>>>>>>> ln-bc-5:service-file-manager/src/main/java/org/containercraft/servicefilemanager/convertor/ContentDTOConvertor.java

@Component
@RequiredArgsConstructor
public class ContentDTOConvertor {
    private final ModelMapper modelMapper;

    public ContentDTO convertEntityToDTO(Content content) {
        return modelMapper.map(content, ContentDTO.class);
    }

    public Content convertDTOToEntity(ContentDTO contentDTO) {
        return modelMapper.map(contentDTO, Content.class);
    }
}
