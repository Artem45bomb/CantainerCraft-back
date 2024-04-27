package org.cantainercraft.micro.users.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.cantainercraft.micro.users.convertor.UserDTOConvertor;
import org.cantainercraft.micro.users.dto.*;
import org.cantainercraft.micro.users.service.AuthService;
import org.cantainercraft.micro.users.service.UserService;
import org.cantainercraft.micro.utilits.exception.NotResourceException;
import org.cantainercraft.project.entity.Role;
import org.cantainercraft.project.entity.User;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

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
    private final UserServiceDetailsImpl userServiceDetails;

    public JwtAuthResponse signup( SignUpAuthDTO signUpAuthDTO){
        Role roleUser = new Role();
        roleUser.setId(1L);
        roleUser.setRole("USER");
        User user = User
                .builder()
                .name(signUpAuthDTO.getUsername())
                .roles(List.of(roleUser))
                .email(signUpAuthDTO.getEmail())
                .password(passwordEncoder.encode(signUpAuthDTO.getPassword()))
                .build();

        userService.save(convertor.convertUserToUserDTO(user));
        CustomUserDetails customUserDetails = new CustomUserDetails(user);
        return new JwtAuthResponse(jwtService.GenerateToken(customUserDetails));
    }

    public JwtAuthResponse login( SignInAuthDTO request){
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getUsername(),
                request.getPassword()
        ));

        var user = userServiceDetails
                .loadUserByUsername(request.getUsername());
        log.info(user.toString());
        return new JwtAuthResponse(jwtService.GenerateToken(user));
    }

}
