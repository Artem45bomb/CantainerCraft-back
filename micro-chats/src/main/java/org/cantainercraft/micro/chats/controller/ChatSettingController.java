package org.cantainercraft.micro.chats.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.cantainercraft.micro.chats.service.ChatSettingsService;
import org.cantainercraft.micro.utilits.exception.ExistResourceException;
import org.cantainercraft.micro.utilits.exception.NotResourceException;
import org.cantainercraft.project.entity.chats.Chat_Settings;
import org.cantainercraft.project.entity.chats.Emotion;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/chat_settings")
@RequiredArgsConstructor
public class ChatSettingController {
    private final ChatSettingsService service;

    @Operation(summary = "get chat setting",
            description = "we get id, allow theme, chat setting group, chat setting channel, chat",
            parameters = @Parameter(name = "uuid", description = "chat setting id", schema = @Schema(implementation = UUID.class)),
            tags = "get")
    @ApiResponses({
            @ApiResponse(responseCode = "200",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Chat_Settings.class))),
            @ApiResponse(responseCode = "400",
                    description = "not valid param",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404",
                    description = "No Settings",
                    content = @Content(mediaType = "application/json"))
    })

    @GetMapping("/{uuid}")
    public ResponseEntity<Chat_Settings> findById(@PathVariable UUID uuid) {
        Optional<Chat_Settings> chatSettings = service.findByUUID(uuid);
        if (chatSettings.isEmpty()) {
            throw new ExistResourceException("No Settings");
        }
        return ResponseEntity.ok(chatSettings.get());
    }

    @Operation(summary = "get chat setting",
            description = "we get id, allow theme, chat setting group, chat setting channel, chat",
            parameters = @Parameter(name = "uuid", description = "chat id", schema = @Schema(implementation = UUID.class)),
            tags = "get")
    @ApiResponses({
            @ApiResponse(responseCode = "200",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Chat_Settings.class))),
            @ApiResponse(responseCode = "400",
                    description = "not valid param",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404",
                    description = "settings is not exist",
                    content = @Content(mediaType = "application/json"))
    })

    @PostMapping("/chat/{uuid}")
    public ResponseEntity<Chat_Settings> findByChatId(@PathVariable UUID uuid) {
        Optional<Chat_Settings> settings = service.findByChatId(uuid);

        if(settings.isEmpty())
            throw new NotResourceException("settings is not exist");

        return ResponseEntity.ok(settings.get());
    }

    @Operation(summary = "get all chat settings",
            description = "we get all chat settings without input params",
            tags = {"get","all"})
    @ApiResponse(responseCode = "200",
            content = @Content(mediaType = "application/json",
                    array = @ArraySchema( schema = @Schema(implementation = Chat_Settings.class))))

    @PostMapping("/all")
    public List<Chat_Settings> findAll(){
        return service.findAll();
    }

}

