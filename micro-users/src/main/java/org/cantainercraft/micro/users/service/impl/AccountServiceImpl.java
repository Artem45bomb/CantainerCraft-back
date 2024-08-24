package org.cantainercraft.micro.users.service.impl;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.cantainercraft.micro.users.dto.tokens.JwtAuthResponse;
import org.cantainercraft.micro.users.dto.UserClientDTO;
import org.cantainercraft.micro.users.dto.UserDTO;
import org.cantainercraft.micro.users.service.AccountService;
import org.cantainercraft.micro.users.service.UserService;
import org.cantainercraft.micro.utilits.exception.NotResourceException;
import org.cantainercraft.micro.utilits.service.ConvertorDTO;
import org.cantainercraft.project.entity.users.Token;
import org.cantainercraft.project.entity.users.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.function.Function;

/*
    updates user account information
*/
@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final UserService userService;
    private final RefreshTokenService refreshService;
    private final Function<Token,String> accessTokenFactory;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final ConvertorDTO<UserClientDTO,User> userClientConvertor;

    @Value("${duration.time.cookie}")
    private Long cookieTime;
    public JwtAuthResponse update(UserDTO userDTO, HttpServletResponse response){
        Optional<User> user = userService.findByUsername(userDTO.getUsername());

        //create an authentication token
        var authentication = new UsernamePasswordAuthenticationToken(userDTO.getUsername(),userDTO.getPassword());

        if(user.isEmpty()){
            throw new NotResourceException("user is not exist");
        }

        //create jwt tokens
        Token token = refreshService.createRefreshToken(authentication);
        String accessToken =  accessTokenFactory.apply(token);



        if(userDTO.getPassword() == null || userDTO.getPassword().isEmpty()){
            userDTO.setPassword(user.get().getPassword());
        }
        else{
            userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        }

        System.out.println("user pass:"+user.get().getPassword()+",userDTO pass"+userDTO.getPassword());
        //update user account
        userService.update(userDTO);

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

    @Override
    public UserClientDTO loadedUser(HttpServletRequest request) {
        String accessToken = null;
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("accessToken")) {
                accessToken = cookie.getValue();
            }
        }

        Optional<User> user = userService.findByUsername(jwtService.extractUsername(accessToken));

        if(user.isEmpty()){
            throw new NotResourceException("user is not exist");
        }

        return userClientConvertor.convertEntityToDTO(user.get());
    }
}
