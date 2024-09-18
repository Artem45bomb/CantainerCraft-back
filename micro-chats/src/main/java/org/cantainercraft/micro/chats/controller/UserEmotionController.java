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
import org.cantainercraft.micro.chats.dto.EmotionAddDTO;
import org.cantainercraft.micro.chats.dto.EmotionDeleteDTO;
import org.cantainercraft.micro.chats.dto.UserEmotionDTO;
import org.cantainercraft.micro.chats.service.UserEmotionService;
import org.cantainercraft.project.entity.chats.User_Emotion;
import org.cantainercraft.project.entity.chats.User_Privilege;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/user_emotion")
@RequiredArgsConstructor
public class UserEmotionController {

    private final UserEmotionService service;

    @Operation(summary = "get all emotions of user",
            description = "we get all emotions of user without input params",
            tags = {"get","all"})
    @ApiResponse(responseCode = "200",
            content = @Content(mediaType = "application/json",
                    array = @ArraySchema( schema = @Schema(implementation = User_Emotion.class))))


    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping("/all")
    public ResponseEntity<List<User_Emotion>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }


    @Operation(summary = "get emotion of user",
            description = "we get user id, message and emotion by id",
            parameters = @Parameter(name = "uuid", description = "userEmotion id", schema = @Schema(implementation = UUID.class)),
            tags = "get")
    @ApiResponses({
            @ApiResponse(responseCode = "200",
                    content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = User_Emotion.class))),
    })

    @PostMapping("/uuid")
    public ResponseEntity<User_Emotion> findById(@RequestBody UUID uuid) {
        return  ResponseEntity.ok(service.findById(uuid));
    }

    @Operation(parameters = @Parameter(
            name = "user Emotion data",
            description = "user Emotion includes: userId, message, emotion",
            schema = @Schema(implementation = User_Emotion.class)),
            summary = "Add user emotion",
            tags = {"add"})
    @ApiResponses({
            @ApiResponse(responseCode = "201",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = User_Emotion.class)
                    )),
            @ApiResponse(responseCode = "406",
                    content = @Content(mediaType = "application/json"),
                    description = "missed param: id"),
            @ApiResponse(responseCode = "400",
                    content = @Content(mediaType = "application/json"),
                    description = "not valid param"),
            @ApiResponse(responseCode = "406",
                    content = @Content(mediaType = "application/json"),
                    description = "user is not exist")
    })

    @PostMapping("/add")
    public ResponseEntity<User_Emotion> save(@Valid @RequestBody UserEmotionDTO dto) {
        return ResponseEntity.ok(service.save(dto));
    }

    @Operation(parameters = @Parameter(
            name = "emotion add data",
            description = "emotion add includes: id, user id, message id, emotion id",
            schema = @Schema(implementation = User_Emotion.class)),
            summary = "Add emotion",
            tags = {"add"})
    @ApiResponses({
            @ApiResponse(responseCode = "201",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = User_Emotion.class)
                    )),
            @ApiResponse(responseCode = "404",
                    content = @Content(
                            mediaType = "application/json"),
                    description = "message is not exist"
            ),
            @ApiResponse(responseCode = "400",
                    content = @Content(
                            mediaType = "application/json"),
                    description = "not valid param"
            ),
            @ApiResponse(responseCode = "404",
                    content = @Content(
                            mediaType = "application/json"),
                    description = "emotion is not exist"
            ),
            @ApiResponse(responseCode = "404",
                    content = @Content(
                            mediaType = "application/json"),
                    description = "user is not exist"
            )
    })

    @PostMapping("/message/add")
    public ResponseEntity<EmotionAddDTO> addEmotion(@Valid @RequestBody EmotionAddDTO dto){
        User_Emotion userEmotion = service.addEmotion(dto);

        return ResponseEntity.ok(EmotionAddDTO.builder()
                        .userId(userEmotion.getUserId())
                        .uuid(userEmotion.getUuid())
                        .emotionId(userEmotion.getEmotion().getUuid())
                        .build());
    }

    @Operation(summary = "delete emotion",
            description = "delete relate emotion of user",
            parameters = @Parameter(name = "id", description = "user emotion id", schema = @Schema(implementation = UUID.class)),
            tags = {"delete"})
    @ApiResponses({
            @ApiResponse(responseCode = "200",
                    description = "if the operation is successful, it will return to delete the emotion",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = UUID.class)
                    )
            ),
            @ApiResponse(responseCode = "404",
                    description = "this user not add emotion",
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

    @PutMapping("/message/delete")
    public void deleteEmotion(@Valid @RequestBody EmotionDeleteDTO dto){
        service.deleteEmotion(dto);
    }

    @Operation(summary = "delete emotion of user",
            description = "delete relate emotion of user",
            parameters = @Parameter(name = "id", description = "user emotion id", schema = @Schema(implementation = UUID.class)),
            tags = {"delete"})
    @ApiResponses({
            @ApiResponse(responseCode = "200",
                    description = "if the operation is successful, it will return to delete the emotion of user",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = UUID.class)
                    )
            ),
            @ApiResponse(responseCode = "404",
                    description = "not is exist",
                    content = @Content(
                            mediaType = "application/json"
                    )
            )
    })

    @PutMapping("/delete")
    public void delete(@RequestBody UUID id) {
        service.delete(id);
    }
}
