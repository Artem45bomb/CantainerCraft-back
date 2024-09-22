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
import org.cantainercraft.micro.chats.dto.ChatSecuredDTO;
import org.cantainercraft.micro.chats.service.ChatSecuredService;
import org.cantainercraft.micro.utilits.service.ConvertorDTO;
import org.cantainercraft.project.entity.chats.Chat_Secured;
import org.cantainercraft.project.entity.chats.Chat_Settings;
import org.cantainercraft.project.entity.chats.Emotion;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/chat_secured")
@RequiredArgsConstructor
public class ChatSecuredController {
    private final ChatSecuredService service;
    private final ConvertorDTO<ChatSecuredDTO, Chat_Secured> convertor;

    @Operation(parameters = @Parameter(
            name = "chat secured data",
            description = "chat secured includes: user id, chat id",
            schema = @Schema(implementation = Chat_Secured.class)),
            summary = "Add message",
            tags = {"add"})
    @ApiResponses({
            @ApiResponse(responseCode = "201",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Chat_Secured.class)
                    )),
            @ApiResponse(responseCode = "400",
                    content = @Content(mediaType = "application/json"),
                    description = "not valid param"),
            @ApiResponse(responseCode = "406",
                    description = "chat is not exist",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "406",
                    description = "user is not exist",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "409",
                    description = "chat is secured",
                    content = @Content(mediaType = "application/json"))
    })
    @PostMapping("/add")
    public ChatSecuredDTO save(@Valid @RequestBody ChatSecuredDTO dto){
        Chat_Secured entity = service.save(dto);
        ChatSecuredDTO chatSecured = convertor.convertEntityToDTO(entity);

        //mapper is not convert chatId
        chatSecured.setChatId(entity.getChat().getUuid());
        return chatSecured;
    }

    @Operation(summary = "delete chat secured",
            description = "delete chat secured",
            parameters = @Parameter(name = "id", description = "chat secured id", schema = @Schema(implementation = UUID.class)),
            tags = {"delete"})
    @ApiResponses({
            @ApiResponse(responseCode = "200",
                    description = "if the operation is successful, it will return to delete the chat secured",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = UUID.class))),
            @ApiResponse(responseCode = "406",
                    description = "chat secured is not exist",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400",
                    description = "if param is not correct",
                    content = @Content(mediaType = "application/json")),
    })

    @PutMapping("/id")
    public void deleteById(@RequestBody UUID id){
        service.deleteById(id);
    }

    @Operation(summary = "delete chat secured",
            description = "delete chat secured",
            parameters = @Parameter(
                    name = "chat secured data",
                    description = "chat secured includes: id, user id, chat id"
            ),
            tags = {"delete"})
    @ApiResponses({
            @ApiResponse(responseCode = "200",
                    description = "if the operation is successful, it will return to delete the chat secured",
                    content = @Content(
                            mediaType = "application/json",
                            array = @ArraySchema( schema = @Schema(implementation = UUID.class)))),
            @ApiResponse(responseCode = "400",
                    description = "not valid param",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "406",
                    description = "chat secured is not exist",
                    content = @Content(mediaType = "application/json"))
    })

    @PutMapping
    public void delete(@Valid @RequestBody ChatSecuredDTO dto){
        service.delete(dto);
    }

    @Operation(summary = "get all chat secured",
            description = "we get all chat secured without input params",
            tags = {"get","all"})
    @ApiResponse(responseCode = "200",
            content = @Content(mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = Chat_Secured.class))))


    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping("/all")
    public List<ChatSecuredDTO> findAll(){
        return service.findAll();
    }

    @Operation(summary = "get chat secured",
            description = "we get id, user id, chat id",
            parameters = @Parameter(name = "userId", description = "user id", schema = @Schema(implementation = Long.class)),
            tags = "get")
    @ApiResponses({
            @ApiResponse(responseCode = "200",
                    content = @Content(mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = Chat_Settings.class)))),
            @ApiResponse(responseCode = "400",
                    description = "not valid param",
                    content = @Content(mediaType = "application/json")),
    })

//    @PreAuthorize("hasAnyRole('USER')")
    @GetMapping("/user/{userId}")
    public List<ChatSecuredDTO> findByUserId(@PathVariable Long userId){
        return service.findByUserId(userId);
    }
}
