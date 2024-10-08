package org.cantainercraft.micro.users.service;

import org.cantainercraft.micro.users.dto.ProfileImageDTO;
import org.cantainercraft.project.entity.users.Profile_Image;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProfileImageService {

    List<Profile_Image> findAll();

    Profile_Image findById(UUID id);

    Profile_Image save(ProfileImageDTO profileImageDTO);

    Profile_Image update(ProfileImageDTO profileImageDTO);

    void deleteById(UUID uuid);

}
