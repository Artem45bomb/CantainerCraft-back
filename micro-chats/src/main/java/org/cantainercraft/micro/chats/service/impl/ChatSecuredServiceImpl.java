package org.cantainercraft.micro.chats.service.impl;

import lombok.RequiredArgsConstructor;
import org.cantainercraft.micro.chats.dto.ChatSecuredDTO;
import org.cantainercraft.micro.chats.repository.ChatRepository;
import org.cantainercraft.micro.chats.repository.ChatSecuredRepository;
import org.cantainercraft.micro.chats.service.ChatSecuredService;
import org.cantainercraft.micro.chats.webflux.UserWebClient;
import org.cantainercraft.micro.utilits.exception.NotResourceException;
import org.cantainercraft.project.entity.chats.Chat;
import org.cantainercraft.project.entity.chats.Chat_Secured;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class ChatSecuredServiceImpl implements ChatSecuredService {
    private final UserWebClient userClient;
    private final ChatSecuredRepository repository;
    private final ChatRepository chatRepository;

    @Override
    public Chat_Secured save(ChatSecuredDTO dto) {
        Optional<Chat> chat = chatRepository.findById(dto.getChatId());

        //if(!userClient.userExist(dto.getUserId())) throw new NotResourceException("user is not exist");
        if(chat.isEmpty()) throw new NotResourceException("chat is not exist");

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
                .map(e ->ChatSecuredDTO.builder()
                        .uuid(e.getUuid())
                        .userId(e.getUserId())
                        .chatId(e.getChat().getUuid())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public List<ChatSecuredDTO> findByUserId(Long userId) {
        return repository.findByUserId(userId)
                .stream()
                .map(e ->ChatSecuredDTO.builder()
                        .uuid(e.getUuid())
                        .userId(e.getUserId())
                        .chatId(e.getChat().getUuid())
                        .build())
                .collect(Collectors.toList());
    }
}
