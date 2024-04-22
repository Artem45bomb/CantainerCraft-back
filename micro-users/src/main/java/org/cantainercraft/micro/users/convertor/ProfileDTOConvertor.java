package org.cantainercraft.micro.users.convertor;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.cantainercraft.micro.users.dto.ProfileDTO;
import org.cantainercraft.project.entity.Profile;

@Component
public class ProfileDTOConvertor {
    @Autowired
    private ModelMapper modelMapper;

    public ProfileDTO convertProfileToProfileDTO(Profile profile){
        return modelMapper.map(profile,ProfileDTO.class);
    }

    public Profile convertProfileDTOToProfile(ProfileDTO profileDTO){
        return modelMapper.map(profileDTO,Profile.class);
    }
}
