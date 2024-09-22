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
import org.cantainercraft.micro.users.dto.ProfileDTO;
import org.cantainercraft.micro.users.dto.ProfileSearchDTO;
import org.cantainercraft.micro.users.service.ProfileService;
import org.cantainercraft.micro.utilits.exception.NotResourceException;
import org.cantainercraft.micro.utilits.exception.NotValidateParamException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.cantainercraft.project.entity.users.Profile;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@RestController
@RequestMapping("/api/profile")
@RequiredArgsConstructor
@Tag(name = "profile")
public class ProfileController {
    private final ProfileService service;


    @Operation(summary = "get profile",
                description = "we get the user's profile with all its settings by profile id",
                parameters = @Parameter(name = "id",description = "Profile id",schema = @Schema(implementation = UUID.class)),
                tags = "get")
    @ApiResponses({
            @ApiResponse(responseCode = "200",
                    content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Profile.class))),
            @ApiResponse(responseCode = "404",
                    description = "profile is not exist",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400",
                    description = "not valid param",
                    content = @Content(mediaType = "application/json")),
    })
    @PostMapping("/id")
    public ResponseEntity<Profile> findById(@RequestBody UUID id){

        Optional<Profile> profile = service.findById(id);

        if(profile.isEmpty()) throw  new NotResourceException("profile is not exist");

        return ResponseEntity.ok(profile.get());
    }

    @Operation(summary = "get profile",
            description = "we get the user's profile with all its settings by user id or email user",
            parameters = @Parameter(name = "object param",schema = @Schema(implementation= ProfileSearchDTO.class)),
            tags = "get")
    @ApiResponses({
            @ApiResponse(responseCode = "200",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Profile.class))),
            @ApiResponse(responseCode = "404",
                    description = "profile is not exist",
                    content = @Content(schema = @Schema)),
            @ApiResponse(responseCode = "406",
                    description = "param is not validate",
                    content = @Content(schema = @Schema)),
            @ApiResponse(responseCode = "406",
                    content = @Content(mediaType = "application/json"),
                    description = "param missed: userId"),
            @ApiResponse(responseCode = "406",
                    content = @Content(mediaType = "application/json"),
                    description = "param missed: email")
    })
    @PostMapping("/user")
    public ResponseEntity<Profile> findByUser(@Valid @RequestBody ProfileSearchDTO profileSearchDTO){
        String email = profileSearchDTO.getEmail();
        Long userId = profileSearchDTO.getUserId();

        if(!email.trim().isEmpty() && userId <= 0){
            throw new NotValidateParamException("param missed: userId");
        }

        if(email.isEmpty() && userId >0 ){
            throw new NotValidateParamException("param missed: email");
        }

        Optional<Profile> profile = service.findByUser(userId, email);

        if(profile.isEmpty()){
            throw new NotResourceException("profile is not exist");
        }
        return ResponseEntity.ok(profile.get());

    }

    @Operation(summary = "get all profiles",
            tags = {"get","all"})
    @ApiResponse(responseCode = "200",
            content = @Content(mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = Profile.class))))
    @GetMapping("/all")
    public ResponseEntity<List<Profile>> findAll(){
        return ResponseEntity.ok(service.findAll());
    }

    @Operation(parameters = @Parameter(
            name = "profile data",
            schema = @Schema(implementation = ProfileDTO.class)),
            summary = "update profile",
            tags = {"update"})
    @ApiResponses({
            @ApiResponse(responseCode = "200",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Profile.class))),
            @ApiResponse(responseCode = "404",
                    description = "if profile is not exist",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400",
                    description = "not valid param",
                    content = @Content(mediaType = "application/json")),
    })
    @PutMapping("/update")
    public ResponseEntity<Profile> update(@Valid @RequestBody ProfileDTO profileDTO){
            return ResponseEntity.ok(service.update(profileDTO));
    }
}
