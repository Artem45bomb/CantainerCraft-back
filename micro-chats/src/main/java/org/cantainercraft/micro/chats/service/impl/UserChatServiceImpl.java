package org.cantainercraft.micro.chats.service.impl;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.cantainercraft.micro.chats.convertor.UserChatDTOConvertor;
import org.cantainercraft.micro.chats.dto.UserChatDTO;
import org.cantainercraft.micro.chats.service.UserChatService;
import org.cantainercraft.micro.chats.webflux.UserWebClient;
import org.cantainercraft.micro.utilits.exception.NotResourceException;
import org.cantainercraft.project.entity.chats.Chat;
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
    private final UserChatRepository repository;
    private final UserChatDTOConvertor convertor;
    private final UserWebClient userWebClient;

    public User_Chat save(UserChatDTO dto){
        log.info(dto.toString());
        User_Chat userChat = convertor.convertDTOToEntity(dto);
        if(!userWebClient.userExist(dto.getUserId())){
            throw new NotResourceException("user is not exist");
        }
        return repository.save(userChat);
    }

    public User_Chat update(UserChatDTO dto){
        User_Chat userChat = convertor.convertDTOToEntity(dto);
        Optional<User_Chat> entity= repository.findById(dto.getId());
        if(entity.isEmpty()){
            throw new NotResourceException("No content for update");
        }

        if(userWebClient.userExist(dto.getUserId()) == null){
            throw new NotResourceException("user is not exist");
        }
        return repository.save(userChat);
    }

    //���������
    public void deleteByUserId(Long userId, UUID chatId){
        Optional<User_Chat> user_chats = repository.findByChatUuidAndUserId(chatId,userId);
        if(user_chats.isEmpty()){
            throw new NotResourceException("No content for delete");
        }
        repository.deleteByUserIdAndChatUuid(userId,chatId);
    }

    public void deleteById(Long id){
            Optional<User_Chat> userChat = repository.findById(id);

            if(userChat.isEmpty()){
                throw new NotResourceException("No content for delete");
            }
        repository.deleteById(id);
    }

    public Optional<User_Chat> findById(Long id){
        Optional<User_Chat> chat = repository.findById(id);
        if(chat.isEmpty()) {
            throw new NotResourceException("No content");
        }
        return repository.findById(id);
    }

    public Optional<List<User_Chat>> findBySearch(Long id,Long userId,UUID chatId){
        Optional<User_Chat> userChat = repository.findById(id);
        if(userChat.isEmpty()){
            throw new NotResourceException("userChat not exist");
        }
        return repository.findBySearch(id,userId,chatId);
    }

    public List<User_Chat> findAll(){
        return repository.findAll();
    }
}
