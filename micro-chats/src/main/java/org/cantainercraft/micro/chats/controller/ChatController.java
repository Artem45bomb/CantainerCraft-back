package org.cantainercraft.micro.chats.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.cantainercraft.micro.chats.dto.ChatDTO;
import org.cantainercraft.micro.chats.dto.ChatSearchDTO;
import org.cantainercraft.micro.chats.service.ChatService;
import org.cantainercraft.micro.chats.service.UserChatService;
import org.cantainercraft.micro.utilits.exception.NotResourceException;
import org.cantainercraft.micro.utilits.exception.NotValidateParamException;
import org.cantainercraft.project.entity.users.TypeChat;
import org.cantainercraft.project.entity.chats.Chat;
import org.cantainercraft.project.entity.chats.User_Chat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/chat")
@RequiredArgsConstructor
public class ChatController {
    private final ChatService service;
    private final UserChatService userChatService;

    @Operation(summary = "get all chats",
            description = "we get all chats without input params",
            tags = {"get","all"})
    @ApiResponse(responseCode = "200",
            content = @Content(mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = Chat.class))))

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping("/all")
    public ResponseEntity<List<Chat>> findAll(){
        return ResponseEntity.ok(service.findAll());
    }

    @Operation(summary = "get chat",
            description = "we get uuid, name, link, date, type chat, users, messages",
            parameters = @Parameter(name = "chat id", description = "chat id", schema = @Schema(implementation = UUID.class)),
            tags = "get")
    @ApiResponses({
            @ApiResponse(responseCode = "200",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Chat.class))),
            @ApiResponse(responseCode = "400",
                    description = "not valid param",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "406",
                    description = "No content",
                    content = @Content(mediaType = "application/json"))
    })

    @PostMapping("/uuid")
    public ResponseEntity<Chat> findByUUID(@RequestBody UUID uuid){
        Optional<Chat> chat = service.findByUUID(uuid);

        if(chat.isEmpty()){
            throw new NotResourceException("No content");
        }

        return ResponseEntity.ok(chat.get());
    }

    @Operation(summary = "delete chat",
            description = "delete chat",
            parameters = @Parameter(
                    name = "uuid",
                    description = "chat id",
                    schema = @Schema(implementation = UUID.class)
            ),
            tags = {"delete"})
    @ApiResponses({
            @ApiResponse(responseCode = "200",
                    description = "if the operation is successful, it will return to delete the chat",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400",
                    description = "not valid param",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "406",
                    description = "chat is not exist",
                    content = @Content(mediaType = "application/json"))
    })

    @PutMapping("/delete")
    public void delete(@RequestBody UUID uuid){
        service.delete(uuid);
    }

    @Operation(parameters = @Parameter(
            name = "chat data",
            schema = @Schema(implementation = ChatDTO.class)),
            summary = "Add chat image",
            tags = {"add"})
    @ApiResponses({
            @ApiResponse(responseCode = "200",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Chat.class)
                    )),
            @ApiResponse(responseCode = "400",
                    content = @Content(mediaType = "application/json"),
                    description = "not valid param"),
            @ApiResponse(responseCode = "409",
                    description = "link for chat is exist",
                    content = @Content(mediaType = "application/json")),
    })
    @PostMapping("/add")
    public ResponseEntity<Chat> save(@Valid @RequestBody ChatDTO chatDTO){
        return ResponseEntity.ok(service.save(chatDTO));
    }

    @Operation(
            summary = "update chat",
            parameters = @Parameter(
                    name = "chat data",
                    description = "each link unique for chat",
                    schema = @Schema(implementation = ChatDTO.class)
            ),
            tags = {"update"}
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Chat.class)
                    )),
            @ApiResponse(responseCode = "400",
                    content = @Content(mediaType = "application/json"),
                    description = "not valid param"),
            @ApiResponse(responseCode = "404",
                    content = @Content(mediaType = "application/json"),
                    description = "chat is not exist"),
            @ApiResponse(responseCode = "409",
                    description = "link for chat is exist",
                    content = @Content(mediaType = "application/json")),
    })
    @PutMapping("/update")
    public ResponseEntity<Chat> update(@Valid @RequestBody ChatDTO chatDTO) throws Exception{
        if(chatDTO.getUuid() == null){
            throw new NotValidateParamException("missed param: id");
        }

        return ResponseEntity.ok(service.update(chatDTO));
    }

    @Operation(summary = "get chats",
            parameters = @Parameter(name = "data", description = "search data", schema = @Schema(implementation = ChatSearchDTO.class)),
            tags = {"get","search"})
    @ApiResponses({
            @ApiResponse(responseCode = "200",
                    content = @Content(
                            mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Chat.class)))),
            @ApiResponse(responseCode = "400",
                    content = @Content(mediaType = "application/json"),
                    description = "not valid param")
    })
    @PostMapping("/search")
    public ResponseEntity<List<Chat>> findBySearch(@RequestBody ChatSearchDTO chatSearchDTO){

        UUID uuid = chatSearchDTO.getUuid() == null ? null :chatSearchDTO.getUuid();
        String chatName = chatSearchDTO.getChatName() == null ? null : chatSearchDTO.getChatName();
        TypeChat typeChat = chatSearchDTO.getTypeChat() == null ? null : chatSearchDTO.getTypeChat();

        Date dateStart = null;
        Date dateEnd = null;

        if (chatSearchDTO.getDateEnd() != null) {

            Calendar calendarEnd = Calendar.getInstance();
            calendarEnd.setTime(chatSearchDTO.getDateEnd());
            calendarEnd.set(Calendar.HOUR_OF_DAY, 23);
            calendarEnd.set(Calendar.MINUTE, 59);
            calendarEnd.set(Calendar.SECOND, 59);
            calendarEnd.set(Calendar.MILLISECOND, 999);
            dateEnd = calendarEnd.getTime(); // записываем конечную дату с 23:59
        }

        if (chatSearchDTO.getDateStart() != null) {

            Calendar calendarStart = Calendar.getInstance();
            calendarStart.setTime(chatSearchDTO.getDateEnd());
            calendarStart.set(Calendar.HOUR_OF_DAY, 0);
            calendarStart.set(Calendar.MINUTE, 0);
            calendarStart.set(Calendar.SECOND, 0);
            calendarStart.set(Calendar.MILLISECOND, 0);

            dateStart = calendarStart.getTime(); // записываем конечную дату с 23:59
        }

        return ResponseEntity.ok(service.findBySearch(uuid,chatName,typeChat,dateStart,dateEnd));

    }

    @Operation(summary = "get chats of user",
            description = "get chats by userId",
            parameters = @Parameter(name = "userId", description = "search userId", schema = @Schema(implementation = Long.class)),
            tags = {"get","search","user_chat"})
    @ApiResponses({
            @ApiResponse(responseCode = "200",
                    content = @Content(
                            mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Chat.class)))),
            @ApiResponse(responseCode = "400",
                    content = @Content(
                            mediaType = "application/json"),
                    description = "not valid param")
    })
    @PostMapping("/user/search")
    public ResponseEntity<List<Chat>> search(@RequestBody Long userId){
        List<User_Chat> userChats =userChatService.findBySearch(userId,null);
        
        return ResponseEntity.ok(userChats.stream()
                .map(User_Chat::getChat)
                .collect(Collectors.toList()));
    }

}