package org.cantainercraft.micro.chats.service.impl;

import lombok.RequiredArgsConstructor;
import org.cantainercraft.micro.chats.convertor.ContentDTOConvertor;
import org.cantainercraft.micro.chats.repository.dto.ContentDTO;
import org.cantainercraft.micro.chats.service.ContentService;
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
        Content entity = convertor.convertContentDTOToContent(contentDTO);
        return repository.save(entity);
    }

    @Override
    public boolean update(ContentDTO contentDTO) {
        if (repository.existsById(contentDTO.getUuid())) {
            Content entity = convertor.convertContentDTOToContent(contentDTO);
            repository.save(entity);
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(UUID uuid) {
        if (repository.existsById(uuid)) {
            repository.deleteById(uuid);
            return true;
        }
        return false;
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
