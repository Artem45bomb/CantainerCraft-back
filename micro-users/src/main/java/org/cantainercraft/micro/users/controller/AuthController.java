package org.cantainercraft.micro.users.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.cantainercraft.micro.users.dto.JwtAuthResponse;
import org.cantainercraft.micro.users.dto.SignInAuthDTO;
import org.cantainercraft.micro.users.dto.SignUpAuthDTO;
import org.cantainercraft.micro.users.service.impl.AuthServiceImpl;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Tag(name = "Auth",description = "api for user registration and authorization")
public class AuthController {

    private final AuthServiceImpl authService;

    @Operation(summary = "signup user",
    description = "registration user",
    parameters = @Parameter(
            name = "data user:name,email,password",
            schema =@Schema(implementation = SignUpAuthDTO.class),required = true),
            tags = {"signup"}
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200",
                    description = "return access and refresh token",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = JwtAuthResponse.class))),
            @ApiResponse(responseCode = "404",
                    description = "user is exist",
                    content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "403",
                    description = "error on server",
                    content = @Content(schema = @Schema()))
    })
    @PostMapping("/signup")
    private JwtAuthResponse signup(@RequestBody @Valid SignUpAuthDTO authDTO,HttpServletResponse response){
        return authService.signup(authDTO,response);
    }

    @Operation(summary = "login user",
            description = "authentication user",
            parameters = @Parameter(
                    name = "data user:name,password",
                    schema =@Schema(implementation = SignInAuthDTO.class),required = true),
            tags = {"login"}
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200",
                    description = "return access and refresh token",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = JwtAuthResponse.class))),
            @ApiResponse(responseCode = "404",
                    description = "user is not exist",
                    content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "403",
                    description = "error on server",
                    content = @Content(schema = @Schema()))
    })
    @PostMapping("/login")
    private JwtAuthResponse login(@RequestBody @Valid SignInAuthDTO signInAuthDTO, @NonNull HttpServletResponse response){
        return authService.login(signInAuthDTO,response);
    }

}
