package org.cantainercraft.micro.users.service.impl;

import org.cantainercraft.micro.users.convertor.ProfileDTOConvertor;
import org.cantainercraft.micro.users.dto.ProfileDTO;
import org.cantainercraft.micro.users.service.InitService;
import org.cantainercraft.micro.users.service.ProfileService;
import org.cantainercraft.project.entity.Profile;
import org.springframework.stereotype.Component;

@Component
public class ProfileInitServiceImpl implements InitService<Profile> {
    private final ProfileService profileService;
    private final ProfileDTOConvertor convertor;

    public ProfileInitServiceImpl(ProfileService profileService, ProfileDTOConvertor convertor) {
        this.profileService = profileService;
        this.convertor = convertor;
    }

    @Override
    public void init(Profile profile) {
        ProfileDTO profileDTO = convertor.convertProfileToProfileDTO(profile);
        profileService.save(profileDTO);
    }
}
