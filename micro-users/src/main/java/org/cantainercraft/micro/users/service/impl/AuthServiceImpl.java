package org.cantainercraft.micro.users.service.impl;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.cantainercraft.micro.users.convertor.UserDTOConvertor;
import org.cantainercraft.micro.users.dto.*;
import org.cantainercraft.micro.users.service.AuthService;
import org.cantainercraft.micro.users.service.UserService;
import org.cantainercraft.micro.utilits.exception.NotResourceException;
import org.cantainercraft.project.entity.RefreshToken;
import org.cantainercraft.project.entity.Role;
import org.cantainercraft.project.entity.User;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {

    private final JwtService jwtService;
    private final UserService userService;
    private final UserDTOConvertor convertor;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final RefreshTokenService refreshService;

    public JwtAuthResponse signup( SignUpAuthDTO signUpAuthDTO){
            Role roleUser = new Role();
            roleUser.setId(1L);
            roleUser.setRole("ROLE_USER");
            RefreshToken refreshToken = refreshService.createRefreshToken(signUpAuthDTO.getUsername());
            User user = User
                    .builder()
                    .username(signUpAuthDTO.getUsername())
                    .roles(List.of(roleUser))
                    .email(signUpAuthDTO.getEmail())
                    .password(passwordEncoder.encode(signUpAuthDTO.getPassword()))
                    .build();

            userService.save(convertor.convertUserToUserDTO(user));
            String accessToken = jwtService.GenerateToken(new CustomUserDetails(user));
            return  JwtAuthResponse.builder()
                    .accessToken(accessToken)
                    .token(refreshToken.getToken())
                    .build();
    }

   public JwtAuthResponse login(SignInAuthDTO signInAuthDTO, HttpServletResponse response){
       Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
               signInAuthDTO.getUsername(),
               signInAuthDTO.getPassword()
       ));
       if(authentication.isAuthenticated()){
           RefreshToken refreshToken = refreshService.createRefreshToken(signInAuthDTO.getUsername());
           Optional<User> user = userService.findByUsername(signInAuthDTO.getUsername());
           var customUserDetails =new CustomUserDetails(user.get());
           String accessToken = jwtService.GenerateToken(customUserDetails);

           ResponseCookie responseCookie = ResponseCookie.from("accessToken")
                   .value(accessToken)
                   .httpOnly(true)
                   .secure(false)
                   .path("/")
                   .maxAge(10000*60*24)
                   .build();
           response.addHeader(HttpHeaders.SET_COOKIE,responseCookie.toString());
           return JwtAuthResponse.builder()
                   .token(refreshToken.getToken())
                   .accessToken(accessToken)
                   .build();
       }
       else{
           throw new UsernameNotFoundException("invalid user request..!!");
       }
   }

}
