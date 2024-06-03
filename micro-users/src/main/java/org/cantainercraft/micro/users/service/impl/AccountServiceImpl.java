package org.cantainercraft.micro.users.service.impl;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.cantainercraft.micro.users.convertor.UserDTOConvertor;
import org.cantainercraft.micro.users.dto.JwtAuthResponse;
import org.cantainercraft.micro.users.dto.UserDTO;
import org.cantainercraft.micro.users.repository.UserRepository;
import org.cantainercraft.micro.users.service.AccountService;
import org.cantainercraft.micro.utilits.exception.NotResourceException;
import org.cantainercraft.project.entity.users.Token;
import org.cantainercraft.project.entity.users.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.function.Function;

/*
    updates user account information
*/
@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {


    private final UserRepository userRepository;
    private final RefreshTokenService refreshService;
    private final Function<Token,String> accessTokenFactory;
    private final UserDTOConvertor convertor;
    @Value("${duration.time.cookie}")
    private Long cookieTime;
    public JwtAuthResponse update(UserDTO userDTO, HttpServletResponse response){
        Optional<User> user = userRepository.findByUsername(userDTO.getUsername());

        //create an authentication token
        var authentication = new UsernamePasswordAuthenticationToken(userDTO.getUsername(),userDTO.getPassword());

        if(user.isEmpty()){
            throw new NotResourceException("user is not exist");
        }

        //create jwt tokens
        Token token = refreshService.createRefreshToken(authentication);
        String accessToken =  accessTokenFactory.apply(token);

        //update user account
        userRepository.save(convertor.convertDTOToEntity(userDTO));

        //add accessToken to cookies so that the user has access to services
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
                .token(token.getToken())
                .build();
    }
}
