package org.cantainercraft.micro.users.controller;

import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.cantainercraft.micro.users.dto.JwtAuthResponse;
import org.cantainercraft.micro.users.dto.UserDTO;
import org.cantainercraft.micro.users.service.AccountService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/account")
@RequiredArgsConstructor
public class UserAccountController {
    private final AccountService accountService;


    @PostMapping("/update")
    public JwtAuthResponse accountUpdate(@RequestBody UserDTO userDTO, @NonNull HttpServletResponse response){
        return accountService.update(userDTO,response);
    }
}
