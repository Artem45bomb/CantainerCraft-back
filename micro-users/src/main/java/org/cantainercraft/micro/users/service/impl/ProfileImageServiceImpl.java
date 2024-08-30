package org.cantainercraft.micro.users.service.impl;

import lombok.RequiredArgsConstructor;
import org.cantainercraft.micro.users.convertor.ProfileImageDTOConvertor;
import org.cantainercraft.micro.users.dto.ProfileImageDTO;
import org.cantainercraft.micro.users.repository.ProfileImageRepository;
import org.cantainercraft.micro.users.service.ProfileImageService;
import org.cantainercraft.micro.users.webflux.FileWebClient;
import org.cantainercraft.micro.utilits.exception.NotResourceException;
import org.cantainercraft.project.entity.users.Profile_Image;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Component
@RequiredArgsConstructor
public class ProfileImageServiceImpl implements ProfileImageService {
    private final ProfileImageDTOConvertor convertor;
    private final ProfileImageRepository repository;
    private final FileWebClient fileClient;

    @Override
    public Profile_Image save(ProfileImageDTO dto) {
        Profile_Image profileImage = convertor.convertDTOToEntity(dto);

        if(fileClient.findBySrc(profileImage.getSrcContent()) == null ){
            throw new NotResourceException("src is not exist");
        }

        return repository.save(profileImage);
    }

    @Override
    public List<Profile_Image> findAll() {
        return repository.findAll();
    }

    @Override
    @Cacheable(value = "profile-image",key = "#uuid",unless = "#result == null")
    public Profile_Image findById(UUID uuid) {
        Optional<Profile_Image> profileImage = repository.findById(uuid);
        if (profileImage.isEmpty()) {
            throw new NotResourceException("Profile image already exists");
        }

        return profileImage.get();
    }

    @Override
    @CachePut(value = "profile-image",key = "#dto.uuid")
    public Profile_Image update(ProfileImageDTO dto) {
        Profile_Image entity = convertor.convertDTOToEntity(dto);
        if(repository.existsById(dto.getUuid())){
            throw new NotResourceException("Profile image already exists");
        }

        if(fileClient.findBySrc(entity.getSrcContent()) == null ){
            throw new NotResourceException("src is not exist");
        }

        return repository.save(entity);
    }

    @Override
    @CacheEvict(value = "profile-image",key = "#uuid")
    public void deleteById(UUID uuid) {
        if(repository.existsById(uuid)){
            throw new NotResourceException("Profile image already exists");
        }

        repository.deleteById(uuid);
    }

}
