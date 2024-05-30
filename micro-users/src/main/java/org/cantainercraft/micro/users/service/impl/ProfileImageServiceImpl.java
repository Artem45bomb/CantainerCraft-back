package org.cantainercraft.micro.users.service.impl;

import lombok.RequiredArgsConstructor;
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
    private final org.cantainercraft.micro.users.convertor.ProfileImageDTOConvertor ProfileImageDTOConvertor;
    private final ProfileImageRepository profileImageRepository;


    @Override
    public Profile_Image save(ProfileImageDTO profileImageDTO) {
        if (profileImageRepository.existsById(profileImageDTO.getUuid())) {
            throw  new NotResourceException("Profile image already exists");
        }
        else {
            Profile_Image Profile_Image = ProfileImageDTOConvertor.convertProfileImageDTOToProfileImage(profileImageDTO);
            return profileImageRepository.save(Profile_Image);
        }
    }

    @Override
    public List<Profile_Image> findAll() {

        return profileImageRepository.findAll();
    }

    @Override
    public Optional<Profile_Image> findById(UUID uuid) {
        if (profileImageRepository.existsById(uuid)) {
            throw new NotResourceException("Profile image already exists");
        }
        else {
            return profileImageRepository.findById(uuid);
        }
    }

    @Override
    public Profile_Image findByProfileId(String profileId) {
        return null;
    }

    @Override
    public Profile_Image update(ProfileImageDTO profileImageDTO) {
        Profile_Image ProfileImage = ProfileImageDTOConvertor.convertProfileImageDTOToProfileImage(profileImageDTO);
        ProfileImage.setUuid(profileImageDTO.getUuid());
        profileImageRepository.save(ProfileImage);
        return profileImageRepository.save(ProfileImage);
    }

    public void deleteById(UUID uuid) {
        profileImageRepository.deleteById(uuid);
    }

    @Override
    public void deleteByProfileId(Long profileId) {}

}
