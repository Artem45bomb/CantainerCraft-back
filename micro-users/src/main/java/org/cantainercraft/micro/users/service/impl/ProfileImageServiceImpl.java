package org.cantainercraft.micro.users.service.impl;

import lombok.RequiredArgsConstructor;
import org.cantainercraft.micro.users.convertor.ProfileImageDTOConvertor;
import org.cantainercraft.micro.users.dto.ProfileImageDTO;
import org.cantainercraft.micro.users.repository.ProfileImageRepository;
import org.cantainercraft.micro.users.service.ProfileImageService;
import org.cantainercraft.micro.utilits.exception.NotResourceException;
import org.cantainercraft.project.entity.users.Profile_Image;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Component
@RequiredArgsConstructor
public class ProfileImageServiceImpl implements ProfileImageService {
    private final ProfileImageDTOConvertor profileImageDTOConvertor;
    private final ProfileImageRepository profileImageRepository;


    @Override
    public Profile_Image save(ProfileImageDTO profileImageDTO) {
        Profile_Image profileImage = profileImageDTOConvertor.convertDTOToEntity(profileImageDTO);
        if (profileImageRepository.existsById(profileImageDTO.getUuid())) {
            throw  new NotResourceException("Profile image already exists");
        }

        return profileImageRepository.save(profileImage);
    }

    @Override
    public List<Profile_Image> findAll() {
        return profileImageRepository.findAll();
    }

    @Override
    public Profile_Image findById(UUID uuid) {
        Optional<Profile_Image> profileImage = profileImageRepository.findById(uuid);
        if (profileImage.isEmpty()) {
            throw new NotResourceException("Profile image already exists");
        }

        return profileImage.get();
    }

    @Override
    public Profile_Image update(ProfileImageDTO profileImageDTO) {
        Profile_Image entity = profileImageDTOConvertor.convertDTOToEntity(profileImageDTO);
        Optional<Profile_Image> profileImageOptional = profileImageRepository.findById(profileImageDTO.getUuid());
        if(profileImageOptional.isEmpty()){
            throw new NotResourceException("Profile image already exists");
        }

        return profileImageRepository.save(entity);
    }

    public void deleteById(UUID uuid) {
        Optional<Profile_Image> profileImage = profileImageRepository.findById(uuid);
        if(profileImage.isEmpty()){
            throw new NotResourceException("Profile image does not exist");
        }

        profileImageRepository.deleteById(uuid);
    }

}
