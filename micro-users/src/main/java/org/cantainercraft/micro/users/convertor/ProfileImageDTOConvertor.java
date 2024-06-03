package org.cantainercraft.micro.users.convertor;

import lombok.RequiredArgsConstructor;
import org.cantainercraft.micro.users.dto.ProfileImageDTO;
import org.cantainercraft.micro.utilits.service.ConvertorDTO;
import org.cantainercraft.project.entity.users.Profile_Image;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProfileImageDTOConvertor implements ConvertorDTO<ProfileImageDTO, Profile_Image> {

    private final ModelMapper modelMapper;


    @Override
    public ProfileImageDTO convertEntityToDTO(Profile_Image profileImage) {
        return modelMapper.map(profileImage,ProfileImageDTO.class);
    }

    @Override
    public Profile_Image convertDTOToEntity(ProfileImageDTO profileImageDTO) {
        return modelMapper.map(profileImageDTO, Profile_Image.class);
    }
}
