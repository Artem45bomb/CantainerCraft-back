package org.cantainercraft.micro.users.convertor;

import lombok.RequiredArgsConstructor;
import org.cantainercraft.micro.users.dto.ProfileImageDTO;
import org.cantainercraft.project.entity.users.Profile_Image;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProfileImageDTOConvertor {

    private final ModelMapper modelMapper;

    public ProfileImageDTO convertProfileImageToProfileImageDTO(Profile_Image profileImage){

            return modelMapper.map(profileImage, ProfileImageDTO.class);
    }

    public Profile_Image convertProfileImageDTOToProfileImage(ProfileImageDTO profileImageDTO){

        return modelMapper.map(profileImageDTO,Profile_Image.class);
    }



}
