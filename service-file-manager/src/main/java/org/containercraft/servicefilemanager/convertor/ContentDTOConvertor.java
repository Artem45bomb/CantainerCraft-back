package org.containercraft.servicefilemanager.convertor;

import lombok.RequiredArgsConstructor;
import org.containercraft.servicefilemanager.dto.ContentDTO;
import org.containercraft.servicefilemanager.entity.Content;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;


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
