package org.cantainercraft.micro.chats.convertor;

import lombok.RequiredArgsConstructor;
import org.cantainercraft.micro.chats.dto.UserEmotionDTO;
import org.cantainercraft.micro.utilits.service.ConvertorDTO;
import org.cantainercraft.project.entity.chats.User_Emotion;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
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
    public User_Emotion convertDTOToEntity(UserEmotionDTO userEmotionDTO) {
        return modelMapper.map(userEmotionDTO,User_Emotion.class);
    }
}
