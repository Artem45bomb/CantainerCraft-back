package ru.micro.chats.data.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.micro.chats.data.convertor.UserChatDTOConvertor;
import ru.micro.chats.data.dto.UserChatDTO;
import ru.micro.chats.data.repository.UserChatRepository;
import ru.weather.project.entity.chats.User_Chat;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
public class UserChatService {
    private final UserChatRepository userChatRepository;
    private final UserChatDTOConvertor userChatDTOConvertor;

    public UserChatService(UserChatRepository userChatRepository, UserChatDTOConvertor userChatDTOConvertor) {
        this.userChatRepository = userChatRepository;
        this.userChatDTOConvertor = userChatDTOConvertor;
    }

    public User_Chat save(UserChatDTO userChatDTO){
        log.info(userChatDTO.toString());
        User_Chat userChat = userChatDTOConvertor.convertUserChatDTOToUserChat(userChatDTO);
        return userChatRepository.save(userChat);
    }

    public User_Chat update(UserChatDTO userChatDTO){
        User_Chat userChat = userChatDTOConvertor.convertUserChatDTOToUserChat(userChatDTO);
        return userChatRepository.save(userChat);
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
