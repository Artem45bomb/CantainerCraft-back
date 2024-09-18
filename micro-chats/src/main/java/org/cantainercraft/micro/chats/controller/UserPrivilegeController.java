package org.cantainercraft.micro.chats.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.cantainercraft.micro.chats.dto.UserPrivilegeDTO;
import org.cantainercraft.micro.chats.service.UserPrivilegeService;
import org.cantainercraft.micro.utilits.exception.NotResourceException;
import org.cantainercraft.project.entity.chats.User_Privilege;
import org.springframework.http.ResponseEntity;
import org.springframework.integration.config.EnablePublisher;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/user-privileges")
@RequiredArgsConstructor
public class UserPrivilegeController {

    private final UserPrivilegeService service;

    @Operation(summary = "get all privileges of user",
            description = "we get all profiles of user without input params",
            tags = {"get","all"})
    @ApiResponse(responseCode = "200",
            content = @Content(mediaType = "application/json",
                    array = @ArraySchema( schema = @Schema(implementation = User_Privilege.class))))

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping("/all")
    public ResponseEntity<List<User_Privilege>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @Operation(summary = "get privilege of user",
                description = "we get user id and privilege by id",
                parameters = @Parameter(name = "id", description = "userPrivilege id", schema = @Schema(implementation = UUID.class)),
                tags = "get")
    @ApiResponses({
            @ApiResponse(responseCode = "200",
                    content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = User_Privilege.class))),
            @ApiResponse(responseCode = "404",
                     description = "No content")
    })

    @PostMapping("/uuid")
    public ResponseEntity<User_Privilege> findById(@RequestBody UUID id) {
        Optional<User_Privilege> userPrivilege = service.findById(id);
        if(userPrivilege.isEmpty()){
            throw new NotResourceException("No content");
        }
        return ResponseEntity.ok(userPrivilege.get());
    }

    @Operation(summary = "get privilege of user",
            description = "we get id and privilege by user id",
            parameters = @Parameter(name = "id", description = "user id", schema = @Schema(implementation = Long.class)),
            tags = "get")
    @ApiResponses({
            @ApiResponse(responseCode = "200",
                    content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = User_Privilege.class))),
            @ApiResponse(responseCode = "404",
                    description = "No content")
    })

    @PostMapping("/userId")
    public ResponseEntity<User_Privilege> findByUserId(Long id) {
        Optional<User_Privilege> userPrivilege = service.findByUserId(id);
        if(userPrivilege.isEmpty()){
            throw new NotResourceException("No content");
        }

        return ResponseEntity.ok(userPrivilege.get());
    }

    @Operation(parameters = @Parameter(
            name = "user Privilege data",
            description = "user Privilege includes: userId, privilege",
            schema = @Schema(implementation = User_Privilege.class)),
                summary = "Add user privilege",
                tags = {"add"})
    @ApiResponses({
            @ApiResponse(responseCode = "201",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = User_Privilege.class)
            )),
            @ApiResponse(responseCode = "400",
                    content = @Content(mediaType = "application/json"),
                    description = "not valid param"),
            @ApiResponse(responseCode = "406",
                    content = @Content(mediaType = "application/json"),
                    description = "user not found"),
            @ApiResponse(responseCode = "409",
                    content = @Content(mediaType = "application/json"),
                    description = "This add privilege for the user")
    })

    @PostMapping("/add")
    public ResponseEntity<User_Privilege> save(@Valid @RequestBody UserPrivilegeDTO dto) {
        return ResponseEntity.ok(service.save(dto));
    }

    @Operation(summary = "delete privilege of user",
                description = "delete relate privilege of user",
                parameters = @Parameter(name = "id", description = "user privilege id", schema = @Schema(implementation = UUID.class)),
                tags = {"delete"})
    @ApiResponses({
            @ApiResponse(responseCode = "200",
                    description = "if the operation is successful, it will return to delete the user",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404",
                    description = "if privilege of user is not exist",
                    content = @Content(mediaType = "application/json"))
    })
    @PutMapping("/delete")
    public void delete(@RequestBody UUID id) {
        service.delete(id);
    }

}
