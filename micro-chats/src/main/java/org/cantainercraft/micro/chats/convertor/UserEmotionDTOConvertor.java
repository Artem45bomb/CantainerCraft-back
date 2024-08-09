package org.cantainercraft.micro.chats.convertor;

import lombok.RequiredArgsConstructor;
import org.cantainercraft.micro.chats.repository.dto.UserEmotionDTO;
import org.cantainercraft.micro.utilits.service.ConvertorDTO;
import org.cantainercraft.project.entity.chats.User_Emotion;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserEmotionDTOConvertor implements ConvertorDTO<UserEmotionDTO, User_Emotion> {
    private final ModelMapper modelMapper;

    @Override
    public UserEmotionDTO convertEntityToDTO(User_Emotion userEmotion) {
        return modelMapper.map(userEmotion, UserEmotionDTO.class);
    }
    @Override
    public User_Emotion convertDTOToEntity(UserEmotionDTO dto) {
        return modelMapper.map(dto, User_Emotion.class);
    }
}
