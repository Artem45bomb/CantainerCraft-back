package org.cantainercraft.micro.users.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.cantainercraft.micro.users.dto.tokens.JwtAuthResponse;
import org.cantainercraft.micro.users.dto.UserClientDTO;
import org.cantainercraft.micro.users.dto.UserDTO;
import org.cantainercraft.micro.users.service.AccountService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/user/account")
@RequiredArgsConstructor
public class UserAccountController {
    private final AccountService accountService;

    @PostMapping("/update")
    public JwtAuthResponse accountUpdate(@Valid @RequestBody UserDTO userDTO, @NonNull HttpServletResponse response){
        return accountService.update(userDTO,response);
    }

    @PostMapping("/loaded")
    public UserClientDTO userClient(HttpServletRequest httpServletRequest){
        return accountService.loadedUser(httpServletRequest);
    }
}
