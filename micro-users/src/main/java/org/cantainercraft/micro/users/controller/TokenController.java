package org.cantainercraft.micro.users.controller;


import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.cantainercraft.micro.users.dto.tokens.AccessTokenDTO;
import org.cantainercraft.micro.users.dto.tokens.TokenUpdateDTO;
import org.cantainercraft.micro.users.service.tokens.AccessTokenService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/token")
@RequiredArgsConstructor
public class TokenController  {
    private final AccessTokenService accessTokenService;
    @PostMapping("/access/update")
    public AccessTokenDTO accessTokenUpdate(@RequestBody TokenUpdateDTO dto, HttpServletResponse response){
        return AccessTokenDTO.builder()
                .token(accessTokenService.update(dto.getTokenRefresh(),response))
                .build();
    }
}
