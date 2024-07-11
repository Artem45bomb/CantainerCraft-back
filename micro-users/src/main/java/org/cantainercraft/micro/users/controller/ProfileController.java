package org.cantainercraft.micro.users.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.cantainercraft.micro.users.dto.ProfileDTO;
import org.cantainercraft.micro.users.dto.ProfileSearchDTO;
import org.cantainercraft.micro.users.dto.UserDTO;
import org.cantainercraft.micro.utilits.exception.ExistResourceException;
import org.cantainercraft.micro.utilits.exception.MessageError;
import org.cantainercraft.micro.users.service.ProfileService;
import org.cantainercraft.micro.utilits.exception.NotResourceException;
import org.cantainercraft.micro.utilits.exception.NotValidateParamException;
import org.cantainercraft.project.entity.users.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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

    private final ProfileService profileService;


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
            content = @Content(schema = @Schema)),
    })
    @PostMapping("/id")
    public ResponseEntity<Profile> findById(@RequestBody UUID id){

        Optional<Profile> profile = profileService.findById(id);

        if(profile.isEmpty()){
            throw  new NotResourceException("profile is not exist");
        }

        return ResponseEntity.ok(profileService.findById(id).get());
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
    })
    @PostMapping("/user")
    public ResponseEntity<Profile> findByUser(@RequestBody ProfileSearchDTO profileSearchDTO){
        String email = profileSearchDTO.getEmail();
        Long userId = profileSearchDTO.getUserId();

        if(!email.trim().isEmpty() && userId <= 0){
            throw new NotValidateParamException("param missed: userId");
        }

        if(email.isEmpty() && userId >0 ){
            throw new NotValidateParamException("param missed: email");
        }

        Optional<Profile> profile = profileService.findByUser(userId, email);

        if(profile.isEmpty()){
            throw new NotResourceException("profile is not exist");
        }
        return ResponseEntity.ok(profile.get());

    }

    @Operation(summary = "get all profiles",
            tags = {"get","all"})
    @ApiResponse(responseCode = "200",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Profile.class)))
    @GetMapping("/all")
    public ResponseEntity<List<Profile>> findAll(){
        return ResponseEntity.ok(profileService.findAll());
    }



    @Operation(parameters = @Parameter(
            name = "profile data",
            schema = @Schema(implementation = ProfileDTO.class)),
            summary = "Add profile",
            tags = {"add"})
    @ApiResponses({
            @ApiResponse(responseCode = "201",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Profile.class))),
            @ApiResponse(responseCode = "409",
                    description = "if profile exist",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema())
            ),
            @ApiResponse(responseCode = "406",
                    description = "not validate param",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema()))
    })
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/add")
    public ResponseEntity<Profile> save(@RequestBody ProfileDTO profileDTO){
        Long userId = profileDTO.getUser() == null? 0 : profileDTO.getUser().getId();
        Optional<Profile> profile = profileService.findByUser(userId,"");

        if(profile.isPresent()){
            throw new ExistResourceException("profile is exist");
        }

        if(profileDTO.getUuid() != null){
            throw new NotValidateParamException("param missed: uuid");
        }

        return ResponseEntity
                .status(201)
                .body(profileService.save(profileDTO));
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
                    content = @Content(mediaType = "application/json",
                            schema = @Schema()))
    })
    @PreAuthorize("hasAnyRole('ADMIN')")
    @PutMapping("/update")
    public ResponseEntity<Profile> update(@RequestBody ProfileDTO profileDTO){
            Optional<Profile> profile = profileService.findById(profileDTO.getUuid());

            if(profile.isEmpty()) throw new NotResourceException("profile is not exist");

            return ResponseEntity.ok(profileService.update(profileDTO));
    }

    @Operation(parameters = @Parameter(
            name = "profile id",
            schema = @Schema(implementation = UUID.class),required = true),
            summary = "delete profile",
            tags = {"delete"})
    @ApiResponses({
            @ApiResponse(responseCode = "200",
                    description = "if the operation is successful,return true",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Boolean.class))),
            @ApiResponse(responseCode = "404",
                    description = "if profile is not exist",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema()))
    })
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/delete/id")
    public ResponseEntity<Boolean> deleteById(@RequestBody UUID id ){
        Optional<Profile> profile = profileService.findById(id);

        if(profile.isEmpty()) throw new NotResourceException("profile is not exist");

        profileService.deleteById(id);
        return ResponseEntity.ok(true);
    }

    @Operation(parameters = @Parameter(
            name = "object parma",
            description = "object param includes:email,userId",
            schema = @Schema(implementation = ProfileSearchDTO.class),required = true),
            summary = "delete profile",
            tags = {"delete"})
    @ApiResponses({
            @ApiResponse(responseCode = "200",
                    description = "if the operation is successful,return true",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Boolean.class))),
            @ApiResponse(responseCode = "409",
                    description = "if param is not validate",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema()))
    })
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/delete/user")
    public ResponseEntity<Boolean> deleteByUser(@RequestBody ProfileSearchDTO profileSearchDTO){

        String email = profileSearchDTO.getEmail() == null ? null :profileSearchDTO.getEmail().trim();
        Long userId = profileSearchDTO.getUserId();

        if(email != null && !email.isEmpty() && (userId == null || userId<= 0)) {
            throw new NotValidateParamException("param missed: userId");
        }

        if(userId != null && userId > 0 && (email == null || email.isEmpty() )){
            throw new NotValidateParamException("param missed: email");
        }

        profileService.deleteByUser(userId,email);
        return ResponseEntity.ok(true);
    }
}
