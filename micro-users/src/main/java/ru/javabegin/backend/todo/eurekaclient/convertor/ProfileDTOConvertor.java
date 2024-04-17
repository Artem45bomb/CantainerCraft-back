package ru.javabegin.backend.todo.eurekaclient.convertor;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.javabegin.backend.todo.eurekaclient.dto.ProfileDTO;
import ru.weather.project.entity.Profile;

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
