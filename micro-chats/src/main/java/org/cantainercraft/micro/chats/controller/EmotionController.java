
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
import org.cantainercraft.micro.chats.dto.EmotionDTO;
import org.cantainercraft.micro.chats.service.EmotionService;
import org.cantainercraft.micro.utilits.exception.NotResourceException;
import org.cantainercraft.project.entity.chats.Chat;
import org.cantainercraft.project.entity.chats.Emotion;
import org.cantainercraft.project.entity.chats.Message;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/emotions")
@RequiredArgsConstructor
public class EmotionController {
    public final EmotionService service;

    @Operation(summary = "delete emotion",
            description = "delete emotion",
            parameters = @Parameter(name = "uuid", description = "emotion id", schema = @Schema(implementation = UUID.class)),
            tags = {"delete"})
    @ApiResponses({
            @ApiResponse(responseCode = "200",
                    description = "if the operation is successful, it will return to delete the emotion",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = UUID.class))),
            @ApiResponse(responseCode = "404",
                    description = "No content for delete",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400",
                    description = "if param is not correct",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404",
                    description = "emotion is not exist",
                    content = @Content(mediaType = "application/json"))
    })

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PutMapping("/delete/id")
    public void deleteById(@RequestBody UUID uuid){
        Optional<Emotion> emotion = service.findById(uuid);
        if(emotion.isEmpty()) {
            throw new NotResourceException("No content for delete");
        }
        service.deleteById(uuid);
    }

    @Operation(summary = "delete emotion",
            description = "delete emotion",
            parameters = @Parameter(name = "unicode", description = "unicode code", schema = @Schema(implementation = String.class)),
            tags = {"delete"})
    @ApiResponses({
            @ApiResponse(responseCode = "200",
                    description = "if the operation is successful, it will return to delete the emotion",
                    content = @Content(mediaType = "application/json")
            ),
            @ApiResponse(responseCode = "404",
                    description = "No content for delete",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400",
                    description = "not valid param",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404",
                    description = "emotion is not exist",
                    content = @Content(mediaType = "application/json"))
    })

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PutMapping("/delete")
    public void deleteByUnicode(@RequestBody String unicode) {
        Optional<Emotion> emotion = service.findByUnicode(unicode);
        if(emotion.isEmpty()) {
            throw new NotResourceException("No content for delete");
        }
        service.deleteByUnicode(unicode);
    }

    @Operation(parameters = @Parameter(
            name = "message data",
            description = "message includes: text, type, date, isPinned, user id, chat, user emotions, contents",
            schema = @Schema(implementation = Emotion.class)),
            summary = "Add message",
            tags = {"add"})
    @ApiResponses({
            @ApiResponse(responseCode = "201",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Emotion.class))),
            @ApiResponse(responseCode = "400",
                    content = @Content(mediaType = "application/json"),
                    description = "not valid param"),
            @ApiResponse(responseCode = "406",
                    description = "emotion is exist",
                    content = @Content(mediaType = "application/json"))
    })

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping("/add")
    public ResponseEntity<Emotion> save(@Valid @RequestBody EmotionDTO dto){
        return ResponseEntity.ok(service.save(dto));
    }

    @Operation(
            summary = "update chat",
            parameters = @Parameter(
                    name = "emotion",
                    description = "each emotion is unique",
                    schema = @Schema(implementation = EmotionDTO.class)
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
                    description = "emotion is not exist"),
            @ApiResponse(responseCode = "409",
                    description = "unicode is exist",
                    content = @Content(mediaType = "application/json")),
    })
    @PreAuthorize("hasAnyRole('ADMIN')")
    @PutMapping("/update")
    public ResponseEntity<Emotion> update(@Valid @RequestBody EmotionDTO dto){
        Optional<Emotion> emotion = service.findById(dto.getUuid());
        if(emotion.isEmpty()) {
            throw new NotResourceException("No content for update");
        }
        return ResponseEntity.ok(service.update(dto));
    }

    @Operation(summary = "get all emotions",
            description = "we get all emotions without input params",
            tags = {"get","all"})
    @ApiResponse(responseCode = "200",
            content = @Content(mediaType = "application/json",
                    array = @ArraySchema( schema = @Schema(implementation = Emotion.class))))


    @GetMapping("/all")
    public ResponseEntity<List<Emotion>> findAll() { return ResponseEntity.ok(service.findAll()); }

    @Operation(summary = "get emotion",
            description = "we get id, unicode, emotions of user",
            parameters = @Parameter(name = "uuid", description = "emotion id", schema = @Schema(implementation = UUID.class)),
            tags = "get")
    @ApiResponses({
            @ApiResponse(responseCode = "200",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Emotion.class))),
            @ApiResponse(responseCode = "400",
                    description = "not valid param",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404",
                    description = "No content",
                    content = @Content(mediaType = "application/json"))
    })

    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping("/id/{uuid}")
    public ResponseEntity<Emotion> findById(@PathVariable UUID uuid){
        Optional<Emotion> emotion = service.findById(uuid);
        if (emotion.isEmpty()) {
            throw new NotResourceException("No content");
        }
        return ResponseEntity.ok(emotion.get());
    }

    @Operation(summary = "get emotion",
            description = "we get id, unicode, emotions of user",
            parameters = @Parameter(name = "unicode", description = "unicode code", schema = @Schema(implementation = String.class)),
            tags = "get")
    @ApiResponses({
            @ApiResponse(responseCode = "200",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Emotion.class))),
            @ApiResponse(responseCode = "400",
                    description = "not valid param",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404",
                    description = "No content",
                    content = @Content(mediaType = "application/json"))
    })

    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping("/unicode/{unicode}")
    public ResponseEntity<Emotion> findByUnicode(@PathVariable String unicode){
        Optional<Emotion> emotion = service.findByUnicode(unicode);
        if(emotion.isEmpty()) {
            throw new NotResourceException("No content");
        }
        return ResponseEntity.ok(emotion.get());
    }
}

