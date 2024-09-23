package org.cantainercraft.micro.users.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.cantainercraft.micro.users.convertor.mapper.UserMapperImpl;
import org.cantainercraft.micro.users.dto.tokens.JwtAuthResponse;
import org.cantainercraft.micro.users.dto.UserClientDTO;
import org.cantainercraft.micro.users.service.AccountService;
import org.cantainercraft.micro.utilits.exception.NotValidateParamException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/user/account")
@RequiredArgsConstructor
public class UserAccountController {
    private final AccountService accountService;
    private final UserMapperImpl userMapper;

    @Operation(summary = "account update",
            parameters = @Parameter(name = "User client data",
                    schema = @Schema(implementation = UserClientDTO.class)),
            tags = {"update"})
    @ApiResponses({
            @ApiResponse(responseCode = "200",
                    description = "success operation",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404",
                    description = "user is not exist",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404",
                    description = "the password length must be between 5 and 255 characters",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400",
                    description = "not valid param",
                    content = @Content(mediaType = "application/json")),
    })
    @PostMapping("/update")
    public JwtAuthResponse accountUpdate(@Valid @RequestBody UserClientDTO userDTO, @NonNull HttpServletResponse response){
        if(!StringUtils.isBlank(userDTO.getPassword()) && userDTO.getPassword().trim().length() < 5){
            throw new NotValidateParamException("The password length must be between 5 and 255 characters");
        }

        return accountService.update(userMapper.toDTO(userDTO),response);
    }

    @Operation(summary = "loaded user",
            parameters = @Parameter(name = "request",
                    description = "http request",
                    schema = @Schema(implementation = HttpServletRequest.class)),
            tags = {"loaded"})
    @ApiResponses({
            @ApiResponse(responseCode = "200",
                    description = "success operation",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404",
                    description = "user is not exist",
                    content = @Content(mediaType = "application/json"))
    })
    @PostMapping("/loaded")
    public UserClientDTO userClient(HttpServletRequest httpServletRequest){
        return accountService.loadedUser(httpServletRequest);
    }
}
