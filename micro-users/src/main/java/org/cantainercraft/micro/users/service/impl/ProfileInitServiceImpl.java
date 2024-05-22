package org.cantainercraft.micro.users.service.impl;

import lombok.RequiredArgsConstructor;
import org.cantainercraft.micro.users.convertor.ProfileDTOConvertor;
import org.cantainercraft.micro.users.dto.ProfileDTO;
import org.cantainercraft.micro.users.service.InitService;
import org.cantainercraft.micro.users.service.ProfileService;
import org.cantainercraft.project.entity.users.Profile;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProfileInitServiceImpl implements InitService<Profile> {
    private final ProfileService profileService;
    private final ProfileDTOConvertor convertor;


    @Override
    public void init(Profile profile) {
        ProfileDTO profileDTO = convertor.convertProfileToProfileDTO(profile);
        profileService.save(profileDTO);
    }
}
