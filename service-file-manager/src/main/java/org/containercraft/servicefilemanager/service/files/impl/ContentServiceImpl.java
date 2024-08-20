package org.containercraft.servicefilemanager.service.files.impl;

import lombok.RequiredArgsConstructor;
<<<<<<< HEAD:micro-chats/src/main/java/org/cantainercraft/micro/chats/service/impl/ContentServiceImpl.java
import org.cantainercraft.micro.chats.convertor.ContentDTOConvertor;
import org.cantainercraft.micro.chats.dto.ContentDTO;
import org.cantainercraft.micro.chats.service.ContentService;
import org.cantainercraft.micro.utilits.exception.NotResourceException;
import org.cantainercraft.project.entity.chats.Content;
import org.cantainercraft.micro.chats.repository.ContentRepository;
=======

import org.containercraft.servicefilemanager.dto.ContentDTO;
import org.containercraft.servicefilemanager.convertor.ContentDTOConvertor;
import org.containercraft.servicefilemanager.entity.Content;
import org.containercraft.servicefilemanager.exception.StorageException;
import org.containercraft.servicefilemanager.exception.StorageFileNotFoundException;
import org.containercraft.servicefilemanager.repository.ContentRepository;
import org.containercraft.servicefilemanager.service.files.ContentService;
>>>>>>> ln-bc-5:service-file-manager/src/main/java/org/containercraft/servicefilemanager/service/files/impl/ContentServiceImpl.java
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
