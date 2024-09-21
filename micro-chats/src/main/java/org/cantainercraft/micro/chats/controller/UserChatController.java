package org.cantainercraft.micro.chats.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.cantainercraft.micro.chats.service.UserChatService;
import org.cantainercraft.micro.utilits.exception.NotResourceException;
import org.cantainercraft.project.entity.chats.User_Emotion;
import org.cantainercraft.project.entity.chats.User_Privilege;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.cantainercraft.micro.chats.dto.UserChatDTO;
import org.cantainercraft.micro.chats.dto.UserChatSearchDTO;
import org.cantainercraft.project.entity.chats.User_Chat;

import java.util.*;

@RestController
@RequestMapping("/api/user_chat")
@RequiredArgsConstructor
class UserChatController {
    //UserFeignClient для взаимодействия с micro-users
    private final UserChatService service;

    @Operation(summary = "get all chats of user",
            description = "we get all chats of user without input params",
            tags = {"get","all"})
    @ApiResponse(responseCode = "200",
            content = @Content(mediaType = "application/json",
                    array = @ArraySchema( schema = @Schema(implementation = User_Chat.class))))


    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping("/all")
    public ResponseEntity<List<User_Chat>> findAll(){
        return ResponseEntity.ok(service.findAll());
    }

    @Operation(summary = "get chat of user",
            description = "we get user id, chat by id",
            parameters = @Parameter(name = "id", description = "userChat id", schema = @Schema(implementation = UUID.class)),
            tags = "get")
    @ApiResponses({
            @ApiResponse(responseCode = "200",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = User_Chat.class))),
            @ApiResponse(responseCode = "404",
                    description = "content is not exist ")
    })

    @PostMapping("/id")
    public ResponseEntity<User_Chat> findById(@RequestBody Long id){
        Optional<User_Chat> userChat = service.findById(id);

        if(userChat.isEmpty()) throw new  NotResourceException("content is not exist");

        return ResponseEntity.ok(userChat.get());
    }

    @Operation(
            parameters = @Parameter(name = "data", description = "user chat include: user id, chat id", array = @ArraySchema(schema = @Schema (implementation = User_Chat.class))),
            summary = "get all chats of user",
            description = "we get all chats of user by user id, chat id",
            tags = {"get","search"})
    @ApiResponse(responseCode = "200",
            content = @Content(mediaType = "application/json",
                    array = @ArraySchema( schema = @Schema(implementation = User_Chat.class))))


    @PostMapping("/search")
    public ResponseEntity<List<User_Chat>> findBySearch(@RequestBody UserChatSearchDTO dto){
        return ResponseEntity.ok(service.findBySearch(dto.getUserId(), dto.getChatId()));
    }

    @Operation(
            parameters = @Parameter(
                    name = "id",
                    description = "privilege id",
                    schema = @Schema(implementation = UUID.class)),
            summary = "delete chat of user",
            description = "delete relate chat of user by id",
            tags = {"delete"})
    @ApiResponses({
            @ApiResponse(responseCode = "200",
                    description = "if the operation is successful, it will return to delete the relate chat of user",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Long.class)
                    )
            ),
            @ApiResponse(responseCode = "400",
                    content = @Content(
                            mediaType = "application/json"),
                    description = "not valid param"
            ),
            @ApiResponse(responseCode = "404",
                    description = "No content for delete",
                    content = @Content(
                            mediaType = "application/json"
                    )
            )
    })

    @PutMapping("/delete")
    public void deleteById(@RequestBody Long id){
        service.deleteById(id);
    }

    @Operation(summary = "delete chat of user",
            description = "delete relate chat of user by user id, chat, id",
            tags = {"delete"})
    @ApiResponses({
            @ApiResponse(responseCode = "200",
                    description = "if the operation is successful, it will return to delete the relate chat of user",
                    content = @Content(
                            mediaType = "application/json"
                    )
            ),
            @ApiResponse(responseCode = "404",
                    description = "No content for delete",
                    content = @Content(
                            mediaType = "application/json"
                    )
            ),
            @ApiResponse(responseCode = "400",
                    content = @Content(
                            mediaType = "application/json"),
                    description = "not valid param"
            )
    })

    @PutMapping("/delete/user")
    public void delete(@Valid @RequestBody UserChatDTO dto){
        service.deleteByUserId(dto.getUserId(),dto.getChat().getUuid());
    }

    @Operation(parameters = @Parameter(
            name = "user chat data",
            description = "user chat includes: id, user id, chat",
            schema = @Schema(implementation = User_Chat.class)),
            summary = "Add user to chat",
            tags = {"add"})
    @ApiResponses({
            @ApiResponse(responseCode = "201",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = User_Chat.class)
                    )),
            @ApiResponse(responseCode = "400",
                    content = @Content(
                            mediaType = "application/json"),
                    description = "not valid param"
            ),
            @ApiResponse(responseCode = "406",
                    content = @Content(
                            mediaType = "application/json"),
                    description = "user is not exist"
            ),
            @ApiResponse(responseCode = "406",
                    content = @Content(
                            mediaType = "application/json"),
                    description = "user is add chat"
            )
    })


    @PostMapping("/add")
    public ResponseEntity<User_Chat> save(@Valid @RequestBody UserChatDTO dto){
        return ResponseEntity.ok(service.save(dto));
    }
}
