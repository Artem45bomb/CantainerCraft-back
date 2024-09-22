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
import org.apache.commons.lang.StringUtils;
import org.cantainercraft.micro.utilits.exception.NotResourceException;
import org.cantainercraft.micro.utilits.exception.NotValidateParamException;
import org.cantainercraft.micro.utilits.service.ConvertorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.cantainercraft.micro.users.dto.UserDTO;
import org.cantainercraft.micro.users.dto.UserSearchDTO;
import org.cantainercraft.project.entity.users.User;
import org.cantainercraft.micro.users.service.UserService;
import java.util.*;


@Tag(name = "users",description = "API for User")
@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;


    @Operation(parameters = {@Parameter(name = "id",description = "User Id",schema = @Schema(implementation = Long.class),required = true)},
            summary = "Retrieve a User for by Id",
            description = "We get the user by Id. If the request is successful, we get the user with information about him including personal information",
            tags = {"get"})
    @ApiResponses({
            @ApiResponse(responseCode = "200",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = User.class)
                    )),
            @ApiResponse(responseCode = "404",
                    description = "user is not exist",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400",
                    description = "not valid param",
                    content = @Content(mediaType = "application/json"))
    })
    @PostMapping("/id")
    public ResponseEntity<User> findById(@RequestBody Long id){
        Optional<User> user = userService.findById(id);

        if(user.isEmpty()){
            throw new NotResourceException("user is not exist");
        }

        return ResponseEntity.ok(user.get());
    }


    @Operation(
            parameters = {@Parameter(description="object with parameters to define",schema = @Schema(implementation = UserSearchDTO.class),required = true)},
            summary = "search users",
            description = "search for similar users using parameters such as email and password",
            tags = {"search","users"}
    )
    @ApiResponse(responseCode = "200",
                        content = @Content(
                                mediaType = "application/json",
                                array = @ArraySchema(
                                        schema = @Schema(
                                                implementation = UserDTO.class))))
    @PostMapping("/search")
    public ResponseEntity<List<User>> findBySearch(@RequestBody @Valid UserSearchDTO dto){

        String email = dto.getEmail() == null ? null : dto.getEmail();
        String password = dto.getPassword() ==null ? null :dto.getPassword();

        return ResponseEntity.ok(userService.findBySearch(email,password));
    }

    @Operation(
            parameters = @Parameter(name = "email",description = "User email",schema = @Schema(implementation = String.class)),
            summary = "search user by email",
            tags = {"get"}
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = User.class)
                    )),
            @ApiResponse(responseCode = "404",
                    description = "email is null",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404",
                    description = "if user is not exist",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400",
                    description = "not valid param",
                    content = @Content(mediaType = "application/json"))
    })
    @PostMapping("/email")
    public ResponseEntity<User> findByEmail( @RequestBody String email){
        if(StringUtils.isBlank(email)){
            throw new NotValidateParamException("email is null");
        }

        Optional<User> user = userService.findByEmail(email);

        if(user.isEmpty()) throw new NotResourceException("user is not exist");

        return ResponseEntity.ok(user.get());

    }

    @Operation(
            parameters = @Parameter(name = "name",description = "User name",schema = @Schema(implementation = String.class)),
            summary = "search user by name",
            tags = {"get"}
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = User.class)
                    )),
            @ApiResponse(responseCode = "404",
                    description = "user is not exist",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400",
                    description = "name is null",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400",
                    description = "not valid param",
                    content = @Content(mediaType = "application/json"))
    })
    @PostMapping("/name")
    public ResponseEntity<User> findByName(@RequestBody String name){
        if(StringUtils.isBlank(name)){
            throw  new NotValidateParamException("name is null");
        }
        Optional<User> user = userService.findByUsername(name);

        if(user.isEmpty()){
            throw new NotResourceException("user is not exist");
        }

        return ResponseEntity.ok(user.get());
    }

    @Operation(summary = "get all users",
            description = "we get all users without input params",
            tags = {"get","all"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "user get all",
                    content = {@Content(mediaType = "application/json",
                                    array=@ArraySchema(schema = @Schema(implementation = User.class))
                            )
                    }
            )
    })
    @PostMapping("/all")
    public ResponseEntity<List<User>> findAll(){
        return ResponseEntity.ok(userService.findAll());
    }

    @Operation(parameters = @Parameter(
        name = "user data",
        description = "User data includes: email address, password, username.",
        schema = @Schema(implementation = UserDTO.class)
    ),
            summary = "Add user",
            tags = {"add"})
    @ApiResponses({
            @ApiResponse(responseCode = "201",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = User.class)
                    )),
            @ApiResponse(responseCode = "409",
                    description = "username is exist",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "406",
                    description = "not validate param",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "406",
                    description = "missed param: id",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "409",
                    description = "email is exist",
                    content = @Content(mediaType = "application/json"))
    })
    @PostMapping("/add")
    public ResponseEntity<User> save(@Valid @RequestBody UserDTO dto){

        if(dto.getId() != null){
            throw new NotValidateParamException("missed param:id");
        }


        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(userService.save(dto));
    }

    @Operation(parameters = @Parameter(
            name = "user date",
            description = "User data includes: email address, password, username,roles ,subscription.",
            schema = @Schema(implementation = UserDTO.class)
    ),
            summary = "Update user",
            tags = {"update"})
    @ApiResponses({
            @ApiResponse(responseCode = "200",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = User.class)
                    )),
            @ApiResponse(responseCode = "404",
                    description = "user is not exist",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "406",
                    description = "not validate param",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "406",
                    description = "missed param: id",
                    content = @Content(mediaType = "application/json")),
    })
    @PutMapping("/update")
    public ResponseEntity<User> update(@Valid @RequestBody UserDTO dto){
            if(dto.getId() == null){
                throw new NotValidateParamException("missed param: id");
            }

            return ResponseEntity.ok(userService.update(dto));
    }

    @Operation(summary = "delete user",
    description = "delete user by email",
    parameters = @Parameter(name = "email",description = "User email",schema = @Schema(implementation = String.class)),
    tags = {"delete"})
    @ApiResponses({
            @ApiResponse(responseCode = "200",
                    description = "if the operation is successful, it will return to delete the user",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = String.class)

                    )),
            @ApiResponse(responseCode = "404",
                    description = "user is not exist",
                    content = @Content(mediaType = "application/json"))
    })
    @PutMapping("/delete/email")
    public void deleteByEmail(@RequestBody String email){
            userService.deleteByEmail(email);
    }


    @Operation(summary = "delete user",
            description = "delete user by id",
            parameters = @Parameter(name = "id",description = "User id",schema = @Schema(implementation = Long.class)),
            tags = {"delete"})
    @ApiResponses({
            @ApiResponse(responseCode = "200",
                    description = "if the operation is successful, it will return to delete the user",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404",
                    description = "user is not exist",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "406",
                    description = "not validate param",
                    content = @Content(mediaType = "application/json"))
    })
    @PutMapping("/delete/id")
    public void deleteById(@RequestBody Long id ){
            userService.deleteById(id);
    }

    @Operation(summary = "exist user",
            description = "exist user by id",
            parameters = {
                @Parameter(name = "id",description = "id",schema = @Schema(implementation = Long.class)),
                @Parameter(
                        name = "header",
                        description = "The header(micro-service-key) is needed to access the request. The header is an access key",
                        schema = @Schema(implementation = String.class)
                )
            }
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200",
                    description = "if the operation is successful, it will return true",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Boolean.class)

                    )),
            @ApiResponse(responseCode = "403",
                    description = "if the service does not have an access key to the request",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400",
                    description = "not valid param",
                    content = @Content(mediaType = "application/json"))
    })
    @PostMapping("/exist/id")
    public ResponseEntity<Boolean> existById(@RequestBody Long id){
        return ResponseEntity.ok(userService.existById(id));
    }

    @GetMapping("/permission")
    public void isPermission() {}
}
