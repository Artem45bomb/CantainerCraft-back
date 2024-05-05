package org.cantainercraft.micro.users.service.impl;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.cantainercraft.micro.users.convertor.UserDTOConvertor;
import org.cantainercraft.micro.users.dto.CustomUserDetails;
import org.cantainercraft.micro.users.dto.JwtAuthResponse;
import org.cantainercraft.micro.users.dto.UserDTO;
import org.cantainercraft.micro.users.repository.UserRepository;
import org.cantainercraft.micro.users.service.AccountService;
import org.cantainercraft.micro.users.service.UserService;
import org.cantainercraft.micro.utilits.exception.NotResourceException;
import org.cantainercraft.project.entity.RefreshToken;
import org.cantainercraft.project.entity.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {


    private final UserRepository userRepository;
    private final RefreshTokenService refreshService;
    private final JwtService jwtService;
    private final UserDTOConvertor convertor;
    @Value("${duration.time.cookie}")
    private Long cookieTime;
    public JwtAuthResponse update(UserDTO userDTO, HttpServletResponse response){

        System.out.println(userDTO.getUsername());
        Optional<User> user = userRepository.findByUsername(userDTO.getUsername());

        if(user.isEmpty()){
            throw new NotResourceException("user is not exist");
        }

        RefreshToken refreshToken = refreshService.createRefreshToken(user.get().getUsername());
        String accessToken =  jwtService.GenerateToken(new CustomUserDetails(convertor.convertUserDTOToUser(userDTO)));
        userRepository.save(convertor.convertUserDTOToUser(userDTO));

        ResponseCookie cookie = ResponseCookie.from("accessToken")
                .value(accessToken)
                .httpOnly(true)
                .path("/")
                .secure(true)
                .maxAge(10000*24*60*cookieTime)
                .build();
        response.addHeader(HttpHeaders.SET_COOKIE,cookie.toString());

        return JwtAuthResponse.builder()
                .accessToken(accessToken)
                .token(refreshToken.getToken())
                .build();
    }
}
