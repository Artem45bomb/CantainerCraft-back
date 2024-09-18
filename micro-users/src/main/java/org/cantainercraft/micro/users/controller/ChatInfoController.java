package org.cantainercraft.micro.users.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.cantainercraft.micro.users.dto.ChatInfoDTO;
import org.cantainercraft.micro.users.service.ChatInfoService;
import org.cantainercraft.project.entity.users.Chat_Info;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.UUID;

@Tag(name = "chat-info")
@RestController
@RequestMapping("/api/chat-info")
@RequiredArgsConstructor
public class ChatInfoController  {

    private final ChatInfoService service;

    @Operation(
            summary = "add info of chat",
            description = "add user chat info. contains user chat data such as pinned etc.",
            parameters = @Parameter(name = "chat info data",
                        description = "without uuid",
                        schema = @Schema(implementation = ChatInfoDTO.class)),
            tags = {"add"}
    )
    @ApiResponses({
            @ApiResponse(
                    description = "if operation success",
                    responseCode = "200",
                    content = @Content(mediaType = "application/json",
                        schema = @Schema(implementation = Chat_Info.class))
            ),
            @ApiResponse(
                    description = "if chat info already exist",
                    responseCode = "409"
            )
    })
    @PostMapping("/add")
    public ResponseEntity<Chat_Info> save(@Valid @RequestBody ChatInfoDTO dto){
        return ResponseEntity.ok(service.save(dto));
    }

    @Operation(summary = "find chat info",
                description = "find by id chat info",
    parameters =@Parameter(description = "uuid",content = @Content(
            schema = @Schema(implementation = UUID.class)
    )),
    tags = {"uuid"})
    @ApiResponses({
            @ApiResponse(
                    description = "if chat info exist",
                    responseCode = "200",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation =Chat_Info.class
                            ))
            ),
            @ApiResponse(
                    description = "not found",
                    responseCode = "404"
            )
    })
    @GetMapping("/{uuid}")
    public ResponseEntity<Chat_Info> findById(@PathVariable UUID uuid){
        return ResponseEntity.ok(service.findById(uuid));
    }

    @Operation(summary = "find all",
            description = "get all entities",
            tags = {"all"})
    @ApiResponses({
            @ApiResponse(
                    description = "get all array entities chat info",
                    responseCode = "200",
                    content = @Content(
                            mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation =Chat_Info.class ))
                    )
            )
    })
    @GetMapping("/all")
    public ResponseEntity<List<Chat_Info>> findAll(){
        return ResponseEntity.ok(service.findAll());
    }

    @Operation(summary = "update chat info",
        parameters = @Parameter(name = "data for update chat info",
        required = true,
        schema = @Schema(implementation = ChatInfoDTO.class)),
        tags = {"update"})
    @ApiResponses({
            @ApiResponse(
                    description = "if chat info exist get update data chat info",
                    responseCode = "200",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Chat_Info.class)
                    )
            ),
            @ApiResponse(
                    description = "if not exist chat info",
                    responseCode = "404"
            )
    })
    @PutMapping("/update")
    public ResponseEntity<Chat_Info> update(@Valid @RequestBody ChatInfoDTO dto) {
        return ResponseEntity.ok(service.update(dto));
    }

    @Operation(summary = "delete chat info",
            parameters = @Parameter(name = "uuid entity",
                    required = true,
                    schema = @Schema(implementation = UUID.class)),
            tags = {"delete"})
    @ApiResponses({
            @ApiResponse(
                    description = "if chat info exist",
                    responseCode = "200"
            ),
            @ApiResponse(
                    description = "if not exist chat info",
                    responseCode = "404"
            )
    })
    @DeleteMapping("/delete/{uuid}")
    public void deleteById(@PathVariable UUID uuid ){
        service.deleteById(uuid);
    }



}
