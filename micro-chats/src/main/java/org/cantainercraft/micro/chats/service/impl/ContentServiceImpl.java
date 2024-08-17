package org.cantainercraft.micro.chats.service.impl;

import lombok.RequiredArgsConstructor;
import org.cantainercraft.micro.chats.convertor.ContentDTOConvertor;
import org.cantainercraft.micro.chats.dto.ContentDTO;
import org.cantainercraft.micro.chats.service.ContentService;
import org.cantainercraft.micro.utilits.exception.NotResourceException;
import org.cantainercraft.project.entity.chats.Content;
import org.cantainercraft.micro.chats.repository.ContentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class ContentServiceImpl implements ContentService {

    private final ContentRepository repository;
    private final ContentDTOConvertor convertor;

    @Override
    public Content save(ContentDTO contentDTO) {
        Content entity = convertor.convertDTOToEntity(contentDTO);
        return repository.save(entity);
    }


    public Content update(ContentDTO contentDTO) {
        if (!repository.existsById(contentDTO.getUuid())) {
           throw new NotResourceException("no content by id");
        }
        Content entity = convertor.convertDTOToEntity(contentDTO);
        return repository.save(entity);
    }

    @Override
    public void delete(UUID uuid) {
        if(!repository.existsById(uuid)){
            throw new NotResourceException("content is not exist");
        }

        repository.deleteById(uuid);
    }

    @Override
    public List<Content> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Content> findById(UUID uuid) {
        return repository.findById(uuid);
    }
}
