package org.containercraft.servicefilemanager.service.files.impl;

import lombok.RequiredArgsConstructor;

import org.containercraft.servicefilemanager.dto.ContentDTO;
import org.containercraft.servicefilemanager.convertor.ContentDTOConvertor;
import org.containercraft.servicefilemanager.entity.Content;
import org.containercraft.servicefilemanager.exception.StorageException;
import org.containercraft.servicefilemanager.exception.StorageFileNotFoundException;
import org.containercraft.servicefilemanager.repository.ContentRepository;
import org.containercraft.servicefilemanager.service.files.ContentService;
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

    @Override
    public Optional<Content> findBySrc(String src) {
        return repository.findBySrcContent(src);
    }

    @Override
    public Content update(Content dto){

        if(!repository.existsById(dto.getUuid())) throw new StorageFileNotFoundException("FILE IS NOT EXIST");

        return repository.save(dto);
    }

    @Override
    public void delete(UUID uuid) {
        if(!repository.existsById(uuid)){
            throw new StorageException("file is not exist");
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
