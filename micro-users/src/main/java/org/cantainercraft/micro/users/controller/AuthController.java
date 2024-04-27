package org.cantainercraft.micro.users.controller;


import jakarta.validation.Valid;
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
public class AuthController {

    private final AuthServiceImpl authService;

    @PostMapping("/signup")
    private JwtAuthResponse signup(@RequestBody @Valid SignUpAuthDTO authDTO){
        return authService.signup(authDTO);
    }

    @PostMapping("/login")
    private JwtAuthResponse login(@RequestBody @Valid SignInAuthDTO signInAuthDTO){
        return authService.login(signInAuthDTO);
    }

}
