package org.cantainercraft.micro.users.controller;

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

    @PostMapping("/update")
    public JwtAuthResponse accountUpdate(@Valid @RequestBody UserClientDTO userDTO, @NonNull HttpServletResponse response){
        if(!StringUtils.isBlank(userDTO.getPassword()) && userDTO.getPassword().trim().length() < 5){
            throw new NotValidateParamException("The password length must be between 5 and 255 characters");
        }

        return accountService.update(userMapper.toDTO(userDTO),response);
    }

    @PostMapping("/loaded")
    public UserClientDTO userClient(HttpServletRequest httpServletRequest){
        return accountService.loadedUser(httpServletRequest);
    }
}
