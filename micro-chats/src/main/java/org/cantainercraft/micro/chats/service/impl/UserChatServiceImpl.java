package org.cantainercraft.micro.chats.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.cantainercraft.micro.chats.convertor.UserChatDTOConvertor;
import org.cantainercraft.micro.chats.dto.UserChatDTO;
import org.cantainercraft.micro.chats.service.UserChatService;
import org.springframework.stereotype.Service;
import org.cantainercraft.micro.chats.repository.UserChatRepository;
import org.cantainercraft.project.entity.chats.User_Chat;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserChatServiceImpl implements UserChatService {
    private final UserChatRepository userChatRepository;
    private final UserChatDTOConvertor userChatDTOConvertor;

    public User_Chat save(UserChatDTO userChatDTO){
        log.info(userChatDTO.toString());
        User_Chat userChat = userChatDTOConvertor.convertUserChatDTOToUserChat(userChatDTO);
        return userChatRepository.save(userChat);
    }

    public User_Chat update(UserChatDTO userChatDTO){
        User_Chat userChat = userChatDTOConvertor.convertUserChatDTOToUserChat(userChatDTO);
        return userChatRepository.save(userChat);
    }

    public Integer deleteByUserId(Long userId,UUID chatId){
        return userChatRepository.deleteByUserIdAndChatUuid(userId,chatId);
    }
    public void deleteById(Long id){
        userChatRepository.deleteById(id);
    }

    public Optional<User_Chat> findById(Long id){
        return userChatRepository.findById(id);
    }

    public List<User_Chat> findBySearch(Long id,Long userId,UUID chatId){
        return userChatRepository.findBySearch(id,userId,chatId);
    }

    public List<User_Chat> findAll(){
        return userChatRepository.findAll();
    }
}
