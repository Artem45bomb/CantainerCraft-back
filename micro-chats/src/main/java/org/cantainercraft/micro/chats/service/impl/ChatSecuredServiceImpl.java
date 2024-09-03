package org.cantainercraft.micro.chats.service.impl;

import lombok.RequiredArgsConstructor;
import org.cantainercraft.micro.chats.dto.ChatSecuredDTO;
import org.cantainercraft.micro.chats.repository.ChatSecuredRepository;
import org.cantainercraft.micro.chats.service.ChatSecuredService;
import org.cantainercraft.micro.chats.service.ChatService;
import org.cantainercraft.micro.chats.webflux.UserWebClient;
import org.cantainercraft.micro.utilits.exception.ExistResourceException;
import org.cantainercraft.micro.utilits.exception.NotResourceException;
import org.cantainercraft.project.entity.chats.Chat;
import org.cantainercraft.project.entity.chats.Chat_Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
@Transactional
@RequiredArgsConstructor
public class ChatSecuredServiceImpl implements ChatSecuredService {
    private final UserWebClient userClient;
    private final ChatSecuredRepository repository;
    private final ChatService chatService;

    @Override
    public Chat_Secured save(ChatSecuredDTO dto) {
        Optional<Chat> chat = chatService.findByUUID(dto.getChatId());

        if(chat.isEmpty()) throw new NotResourceException("chat is not exist");
        if(!userClient.userExist(dto.getUserId())) throw new NotResourceException("user is not exist");
        if(repository.existsByUserIdAndChat(dto.getUserId(),chat.get())) throw new ExistResourceException("chat is secured");

        Chat_Secured chatSecured = Chat_Secured.builder()
                .chat(chat.get())
                .userId(dto.getUserId())
                .build();
        return repository.save(chatSecured);
    }

    @Override
    public void deleteById(UUID uuid) {
        if(!repository.existsById(uuid)) throw new NotResourceException("chat secured is not exist");

        repository.deleteById(uuid);
    }

    @Override
    public void delete(ChatSecuredDTO dto) {

        Chat chat = Chat.builder()
                .uuid(dto.getChatId())
                .build();

        if(!repository.existsByUserIdAndChat(dto.getUserId(),chat)){
            throw new NotResourceException("chat secured is not exist");
        }

        repository.deleteByUserIdAndChat(dto.getUserId(),chat);
    }

    @Override
    public List<ChatSecuredDTO> findAll() {
        return repository.findAll()
                .stream()
                .map(e ->new ChatSecuredDTO(e.getUuid(),e.getUserId(),e.getChat().getUuid()))
                .collect(Collectors.toList());
    }

    @Override
    public List<ChatSecuredDTO> findByUserId(long userId) {
        return repository.findByUserId(userId)
                .stream()
                .map(e -> new ChatSecuredDTO(e.getUuid(), e.getUserId(), e.getChat().getUuid()))
                .collect(Collectors.toList());
    }
}
