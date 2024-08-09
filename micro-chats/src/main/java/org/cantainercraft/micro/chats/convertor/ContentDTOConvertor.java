package org.cantainercraft.micro.chats.convertor;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.cantainercraft.micro.chats.repository.dto.ContentDTO;
import org.cantainercraft.project.entity.chats.Content;

@Component
@RequiredArgsConstructor
public class ContentDTOConvertor {
    private final ModelMapper modelMapper;

    public ContentDTO convertContentToContentDTO(Content content) {
        return modelMapper.map(content, ContentDTO.class);
    }

    public Content convertContentDTOToContent(ContentDTO contentDTO) {
        return modelMapper.map(contentDTO, Content.class);
    }
}
