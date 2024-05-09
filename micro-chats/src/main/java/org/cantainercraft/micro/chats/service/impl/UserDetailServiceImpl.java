package org.cantainercraft.micro.chats.service.impl;

import lombok.RequiredArgsConstructor;
import org.cantainercraft.micro.chats.dto.CustomUserDetails;
import org.cantainercraft.micro.chats.feign.UserFeignClient;
import org.cantainercraft.micro.chats.webflux.UserWebClient;
import org.cantainercraft.project.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService {
    private final UserWebClient userWebClient;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {


        UserDetails userDetails = new CustomUserDetails(userWebClient.loadedUser(username));

        if(userDetails != null){
            return userDetails;
        }
        else{
            throw new UsernameNotFoundException("user is not exist");
        }
    }
}
