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
import org.cantainercraft.micro.chats.dto.PrivilegeDTO;
import org.cantainercraft.micro.chats.dto.PrivilegeSearchDTO;
import org.cantainercraft.micro.chats.service.PrivilegeService;
import org.cantainercraft.micro.utilits.exception.NotResourceException;
import org.cantainercraft.project.entity.chats.Privilege;
import org.cantainercraft.project.entity.chats.User_Chat;
import org.cantainercraft.project.entity.chats.User_Emotion;
import org.cantainercraft.project.entity.chats.User_Privilege;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/privileges")
@RequiredArgsConstructor
public class PrivilegeController {

    private final PrivilegeService service;

    @Operation(summary = "get all privileges",
            description = "we get all privileges without input params",
            tags = {"get","all"})
    @ApiResponse(responseCode = "200",
            content = @Content(mediaType = "application/json",
                    array = @ArraySchema( schema = @Schema(implementation = Privilege.class))))


    @PostMapping("/all")
    public ResponseEntity<List<Privilege>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @Operation(summary = "get chat of user",
            description = "we get chat and name role",
            parameters = @Parameter(name = "id", description = "privilege id", schema = @Schema(implementation = UUID.class)),
            tags = "get")
    @ApiResponses({
            @ApiResponse(responseCode = "200",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Privilege.class))),
            @ApiResponse(responseCode = "404",
                    description = "content is not exist "),
            @ApiResponse(responseCode = "406",
                    description = "This privileges does not exist")
    })

    @PostMapping("/uuid")
    public ResponseEntity<Privilege> findById(@RequestBody UUID id) {
        Optional<Privilege> privilege = service.findById(id);

        if (privilege.isEmpty()) {
            throw new NotResourceException("This privilege does not exist");
        }

        return ResponseEntity.ok(privilege.get());
    }

    @Operation(summary = "get privileges of chat",
            description = "we get privileges by chat id",
            parameters = @Parameter(name = "id", description = "chat id", schema = @Schema(implementation = UUID.class)),
            tags = "get")
    @ApiResponses({
            @ApiResponse(responseCode = "200",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = User_Privilege.class)))),
            @ApiResponse(responseCode = "404",
                    description = "No content")
    })

    @PostMapping("/chat")
    public ResponseEntity<List<Privilege>> findByChat( @RequestBody PrivilegeSearchDTO dto) {
        return ResponseEntity.ok(service.findByChat(dto.getChatId()));
    }

    @Operation(parameters = @Parameter(
        name = " data",
        description = "privilege includes: chat, name role, list of users",
        schema = @Schema(implementation = Privilege.class)),
        summary = "Add privilege",
        tags = {"add"})
    @ApiResponses({
            @ApiResponse(responseCode = "201",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Privilege.class)
                    )),
            @ApiResponse(responseCode = "400",
                    content = @Content(
                            mediaType = "application/json"),
                    description = "not valid param"
            ),
            @ApiResponse(responseCode = "406",
                    content = @Content(
                            mediaType = "application/json"),
                    description = "this privilege already exists"
            )
    })

    @PostMapping("/add")
    public ResponseEntity<Privilege> save(@Valid @RequestBody PrivilegeDTO dto) {
        return ResponseEntity.ok(service.save(dto));
    }

    @Operation(parameters = @Parameter(
            name = " data",
            description = "privilege includes: id, chat, name role, list of users",
            schema = @Schema(implementation = Privilege.class)),
            summary = "update privilege",
            tags = {"update"}
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Privilege.class))
            ),
            @ApiResponse(responseCode = "400",
                    content = @Content(
                            mediaType = "application/json"),
                    description = "not valid param"
            ),
            @ApiResponse(responseCode = "406",
                    content = @Content(
                            mediaType = "application/json"
                    ),
                    description = "Privilege not exists"
            ),
            @ApiResponse(responseCode = "409",
            content = @Content(
                    mediaType = "application/json"),
            description = "This privilege already exists"
            )
    })

    @PutMapping("/update")
    public ResponseEntity<Privilege> update(@Valid @RequestBody PrivilegeDTO dto) {
        return ResponseEntity.ok(service.update(dto));
    }

    @Operation(summary = "delete privilege",
            description = "delete privilege by id",
            parameters = @Parameter(name = "id", description = "privilege id", schema = @Schema(implementation = UUID.class)),
            tags = {"delete"})
    @ApiResponses({
            @ApiResponse(responseCode = "200",
                    description = "if the operation is successful, it will return to delete privilege",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = UUID.class)
                    )
            ),
            @ApiResponse(responseCode = "400",
                    content = @Content(
                            mediaType = "application/json"),
                    description = "not valid param"
            ),
            @ApiResponse(responseCode = "404",
                    description = "This privilege does not exist",
                    content = @Content(
                            mediaType = "application/json"
                    )
            )
    })

    @PutMapping("/delete")
    public void deleteById(@RequestBody UUID uuid) {
        service.deleteById(uuid);
    }
}
