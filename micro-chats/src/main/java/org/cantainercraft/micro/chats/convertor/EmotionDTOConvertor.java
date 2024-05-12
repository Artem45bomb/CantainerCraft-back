package org.cantainercraft.micro.chats.convertor;

import lombok.RequiredArgsConstructor;
import org.cantainercraft.micro.chats.dto.EmotionDTO;
import org.cantainercraft.project.entity.chats.Emotion;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmotionDTOConvertor {
    private final ModelMapper modelMapper;

    public EmotionDTO convertEmotionToEmotionDTO(Emotion emotion){
        return modelMapper.map(emotion,EmotionDTO.class);
    }

    public Emotion convertEmotionDTOToEmotion(EmotionDTO emotionDTO){
        return modelMapper.map(emotionDTO,Emotion.class);
    }
}
