package org.cantainercraft.micro.users.service.impl;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.cantainercraft.micro.users.dto.*;
import org.cantainercraft.micro.users.dto.tokens.JwtAuthResponse;
import org.cantainercraft.micro.users.factory.AccessTokenFactory;
import org.cantainercraft.micro.users.service.AuthService;
import org.cantainercraft.micro.users.service.UserAuthService;
import org.cantainercraft.project.entity.users.Token;
import org.cantainercraft.project.entity.users.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {
    private final AccessTokenFactory accessTokenFactory;
    private final UserAuthService userAuthService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final RefreshTokenService refreshService;
    @Value("${duration.time.cookie}")
    private Long cookieTime;

    @Override
    public JwtAuthResponse signup(SignUpAuthDTO signUpAuthDTO, HttpServletResponse response){

            User user = User.builder()
                    .username(signUpAuthDTO.getUsername())
                    .email(signUpAuthDTO.getEmail())
                    .password(passwordEncoder.encode(signUpAuthDTO.getPassword()))
                    .build();

            //create an authentication token
            var authentication = new UsernamePasswordAuthenticationToken(user.getUsername()
                    ,user.getPassword());

            userAuthService.authUser(user);

            //create jwt tokens
            Token token = refreshService.createRefreshToken(authentication);
            String accessToken = accessTokenFactory.apply(token);

            //add accessToken to cookies so that the user has access to services
            ResponseCookie cookie = ResponseCookie.from("accessToken")
                    .value(accessToken)
                    .secure(true)
                    .httpOnly(true)
                    .path("/")
                    .build();
            response.addHeader(HttpHeaders.SET_COOKIE,cookie.toString());

            return  JwtAuthResponse.builder()
                    .accessToken(accessToken)
                    .token(token.getToken())
                    .build();
    }

    @Override
   public JwtAuthResponse login(SignInAuthDTO signInAuthDTO, HttpServletResponse response){
       var authenticationToken  = new UsernamePasswordAuthenticationToken(
               signInAuthDTO.getUsername(),
               signInAuthDTO.getPassword()
       );
       var authentication = authenticationManager.authenticate(authenticationToken);
       if(authentication.isAuthenticated()){
           Token token = refreshService.createRefreshToken(authenticationToken);
           String accessToken = accessTokenFactory.apply(token);

           ResponseCookie responseCookie = ResponseCookie.from("accessToken")
                   .value(accessToken)
//                   .httpOnly(true)
//                   .secure(false)
                   .path("/")
                   .maxAge(1000*24*60*cookieTime)
                   .build();
           response.addHeader(HttpHeaders.SET_COOKIE,responseCookie.toString());
           return JwtAuthResponse.builder()
                   .token(token.getToken())
                   .accessToken(accessToken)
                   .build();
       }
       else{
           throw new UsernameNotFoundException("invalid user request..!!");
       }
   }

}
