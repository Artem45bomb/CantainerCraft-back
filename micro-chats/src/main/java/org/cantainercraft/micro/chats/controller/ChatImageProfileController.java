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
import org.cantainercraft.micro.chats.dto.ChatImageProfileDTO;
import org.cantainercraft.micro.chats.service.ChatImageProfileService;
import org.cantainercraft.micro.utilits.exception.NotResourceException;
import org.cantainercraft.micro.utilits.exception.NotValidateParamException;
import org.cantainercraft.project.entity.chats.Chat_Image_Profile;
import org.cantainercraft.project.entity.chats.Chat_Secured;
import org.cantainercraft.project.entity.chats.Chat_Settings;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/chat-image-profiles")
@RequiredArgsConstructor
public class ChatImageProfileController {
    private final ChatImageProfileService service;

    @Operation(summary = "get all chat images",
            description = "we get all chat images without input params",
            tags = {"get","all"})
    @ApiResponse(responseCode = "200",
            content = @Content(mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = Chat_Image_Profile.class))))

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping("/all")
    public ResponseEntity<List<Chat_Image_Profile>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @Operation(summary = "get chat image",
            description = "we get id, src content, chat",
            parameters = @Parameter(name = "chatId", description = "chat id", schema = @Schema(implementation = UUID.class)),
            tags = "get")
    @ApiResponses({
            @ApiResponse(responseCode = "200",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Chat_Image_Profile.class)))),
            @ApiResponse(responseCode = "400",
                    description = "not valid param",
                    content = @Content(mediaType = "application/json")),
    })

    @GetMapping("/{chatId}")
    public List<Chat_Image_Profile> findByChatId(@PathVariable UUID chatId){
        return service.findByChatId(chatId);
    }

    @Operation(summary = "get chat image",
            description = "we get id, src content, chat",
            parameters = @Parameter(name = "uuid", description = "chat image id", schema = @Schema(implementation = UUID.class)),
            tags = "get")
    @ApiResponses({
            @ApiResponse(responseCode = "200",
                    content = @Content(mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = Chat_Image_Profile.class)))),
            @ApiResponse(responseCode = "400",
                    description = "not valid param",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "406",
                    description = "No content",
                    content = @Content(mediaType = "application/json"))
    })

    @PostMapping("/uuid")
    public ResponseEntity<Chat_Image_Profile> findById(@RequestBody UUID uuid) {
        Optional<Chat_Image_Profile> chatImageProfile = service.findById(uuid);

        if(chatImageProfile.isEmpty()) throw new NotResourceException("No content");

        return ResponseEntity.ok(chatImageProfile.get());
    }

    @Operation(parameters = @Parameter(
            name = "chat image data",
            description = "chat image includes: srcContent, chat",
            schema = @Schema(implementation = Chat_Image_Profile.class)),
            summary = "Add chat image",
            tags = {"add"})
    @ApiResponses({
            @ApiResponse(responseCode = "201",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Chat_Image_Profile.class)
                    )),
            @ApiResponse(responseCode = "400",
                    content = @Content(mediaType = "application/json"),
                    description = "not valid param"),
            @ApiResponse(responseCode = "406",
                    description = "chat is not exist",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "406",
                    description = "src is not exist",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "406",
                    description = "missed param: content",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "406",
                    description = "missed param: chat uuid",
                    content = @Content(mediaType = "application/json"))
    })
    @PostMapping("/add")
    public ResponseEntity<Chat_Image_Profile> save(@Valid @RequestBody ChatImageProfileDTO dto) {

        if(dto.getSrcContent() == null) {
            throw new NotValidateParamException("missed param: content");
        }

        if(dto.getChat() == null || dto.getChat().getUuid() == null){
            throw new NotValidateParamException("missed param: chat uuid");
        }
        
        return ResponseEntity.ok(service.save(dto));
    }

    @Operation(summary = "delete chat Image",
            description = "delete chat Image",
            parameters = @Parameter(name = "uuid", description = "chat image id"),
            tags = {"delete"})
    @ApiResponses({
            @ApiResponse(responseCode = "200",
                    description = "if the operation is successful, it will return to delete the chat image",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400",
                    description = "not valid param",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "406",
                    description = "No content to delete",
                    content = @Content(mediaType = "application/json"))
    })

    @PutMapping("/delete")
    public void delete(@RequestBody UUID id) {
        service.delete(id);
    }
}
