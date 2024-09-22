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
import org.cantainercraft.micro.users.dto.ProfileImageDTO;
import org.cantainercraft.micro.users.service.ProfileImageService;
import org.cantainercraft.project.entity.users.Profile;
import org.cantainercraft.project.entity.users.Profile_Image;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/profile-image")
@RequiredArgsConstructor
public class ProfileImageController {

    private final ProfileImageService service;

    @Operation(summary = "get all profile images",
                tags = "all")
    @ApiResponses({
            @ApiResponse(
                    description = "if operation success",
                    responseCode = "200",
                    content = @Content(
                            mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Profile_Image.class))
                    )
            )
    })
    @GetMapping("/all")
    public ResponseEntity<List<Profile_Image>> findAll(){
        return ResponseEntity.ok(service.findAll());
    }

    @Operation(
            summary = "get image profile by id",
            parameters = @Parameter(
                    name = "uuid",
                    description = "uuid profile image",
                    schema = @Schema(implementation = UUID.class)
            ),
            tags = "get"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Profile_Image.class))),
            @ApiResponse(responseCode = "404",
                    description = "Profile image already exists",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400",
                    description = "not valid param",
                    content = @Content(mediaType = "application/json"))
    })
    @GetMapping("/{uuid}")
    public ResponseEntity<Profile_Image> findById(@PathVariable UUID uuid) {
        return ResponseEntity.ok(service.findById(uuid));
    }

    @Operation(
            summary = "add image",
            description = "add image for profile of user",
            parameters = @Parameter(
                    required = true,
                    description = "data for add image in profile",
                    schema = @Schema(implementation = ProfileImageDTO.class)
            )
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Profile_Image.class))),
            @ApiResponse(responseCode = "404",
                    description = "src is not exist",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400",
                    description = "not valid param",
                    content = @Content(mediaType = "application/json"))
    })
    @PostMapping("/add")
    public ResponseEntity<Profile_Image> save(@Valid @RequestBody ProfileImageDTO dto){
            return ResponseEntity.ok(service.save(dto));
    }

    @Operation(
            summary = "update image",
            description = "update image for profile of user",
            parameters = @Parameter(
                    required = true,
                    description = "data for update image in profile",
                    schema = @Schema(implementation = ProfileImageDTO.class)
            )
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Profile_Image.class))),
            @ApiResponse(responseCode = "409",
                    description = "Profile Image already exists",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404",
                    description = "src is not exist",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400",
                    description = "not valid param",
                    content = @Content(mediaType = "application/json"))
    })

    @PutMapping("/update")
    public ResponseEntity<Profile_Image> update(@RequestBody @Valid ProfileImageDTO dto){
        return ResponseEntity.ok(service.update(dto));
    }

    @Operation(
            summary = "delete image",
            description = "delete image for profile of user",
            parameters = @Parameter(
                    required = true,
                    description = "data for delete image in profile"
            )
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "409",
                    description = "Profile Image already exists",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400",
                    description = "not valid param",
                    content = @Content(mediaType = "application/json"))
    })

    @DeleteMapping("/delete/{uuid}")
    public void deleteById(@PathVariable UUID uuid ){
        service.deleteById(uuid);
    }

}
