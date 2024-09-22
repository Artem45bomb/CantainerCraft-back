package org.cantainercraft.micro.users.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.cantainercraft.micro.users.dto.UserDTO;
import org.cantainercraft.micro.users.dto.UserOnlineDTO;
import org.cantainercraft.micro.users.service.UserOnlineService;
import org.cantainercraft.micro.utilits.exception.NotResourceException;
import org.cantainercraft.project.entity.users.User;
import org.cantainercraft.project.entity.users.User_Online;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user_online")
public class UserOnlineController {
    private final UserOnlineService service;

    @Operation(summary = "get all online users",
            description = "we get all online users without input params",
            tags = {"get","all"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "user online get all",
                    content = {@Content(mediaType = "application/json",
                            array=@ArraySchema(schema = @Schema(implementation = User_Online.class)))}
            )
    })

    @GetMapping("/all")
    public ResponseEntity<List<User_Online>> findAll(){
        return ResponseEntity.ok(service.findAll());
    }

    @Operation(parameters = {@Parameter(name = "id",description = "User online Id",schema = @Schema(implementation = Long.class),required = true)},
            summary = "get user online",
            description = "We get the user online by Id",
            tags = {"get"})
    @ApiResponses({
            @ApiResponse(responseCode = "200",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = User_Online.class)
                    )),
            @ApiResponse(responseCode = "404",
                    description = "User online not found",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400",
                    description = "not valid param",
                    content = @Content(mediaType = "application/json"))
    })

    @GetMapping("/{uuid}")
    public ResponseEntity<User_Online> findById(@PathVariable UUID uuid) {
        Optional<User_Online> userOnline =service.findById(uuid);

        if (userOnline.isEmpty()) {
            throw new NotResourceException("User online not found");
        }

        return ResponseEntity.ok(userOnline.get());
    }

    @Operation(parameters = @Parameter(
            name = "user online data",
            description = "User online data includes: user online id, user, is online",
            schema = @Schema(implementation = UserDTO.class)
    ),
            summary = "update user",
            tags = {"add"})
    @ApiResponses({
            @ApiResponse(responseCode = "201",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = User_Online.class)
                    )),
            @ApiResponse(responseCode = "409",
                    description = "UserOnline not exists",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "406",
                    description = "not validate param",
                    content = @Content(mediaType = "application/json"))
    })

    @PutMapping("/update")
    public ResponseEntity<User_Online> update(@Valid @RequestBody UserOnlineDTO dto){
        return ResponseEntity.ok(service.update(dto));
    }
}
