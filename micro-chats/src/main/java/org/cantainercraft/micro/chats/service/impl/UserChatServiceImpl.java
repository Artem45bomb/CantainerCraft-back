package org.cantainercraft.micro.chats.service.impl;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.cantainercraft.micro.chats.convertor.UserChatDTOConvertor;
import org.cantainercraft.micro.chats.dto.UserChatDTO;
import org.cantainercraft.micro.chats.service.UserChatService;
import org.cantainercraft.micro.chats.webflux.UserWebClient;
import org.cantainercraft.micro.utilits.exception.ExistResourceException;
import org.cantainercraft.micro.utilits.exception.NotResourceException;
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

    @Override
    public User_Chat save(UserChatDTO dto){
        log.info(dto.toString());
        User_Chat userChat = convertor.convertDTOToEntity(dto);

        if(!userWebClient.userExist(dto.getUserId()))
            throw new NotResourceException("user is not exist");

        if(repository.existsByUserIdAndChatUuid(dto.getUserId(),dto.getChat().getUuid()))
            throw new ExistResourceException("user is add chat");

        return repository.save(userChat);
    }

    @Override
    public void deleteByUserId(Long userId, UUID chatId){
        if(!repository.existsByUserIdAndChatUuid(userId,chatId))
            throw new NotResourceException("No content for delete");

        repository.deleteByUserIdAndChatUuid(userId,chatId);
    }

    @Override
    public void deleteById(Long id){
        if(!repository.existsById(id))
            throw new NotResourceException("No content for delete");

        repository.deleteById(id);
    }

    @Override
    public Optional<User_Chat> findById(Long id){
        return repository.findById(id);
    }

    @Override
    public List<User_Chat> findBySearch(Long userId,UUID chatId){
        return repository.findBySearch(userId,chatId);
    }

    @Override
    public List<User_Chat> findAll(){
        return repository.findAll();
    }
}
