package org.cantainercraft.micro.chats.service.impl;

import lombok.RequiredArgsConstructor;
import org.cantainercraft.micro.chats.repository.dto.CustomUserDetails;
import org.cantainercraft.micro.chats.webflux.UserWebClient;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService {
    private final UserWebClient userWebClient;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       return new CustomUserDetails(userWebClient.loadedUser(username));
    }
}
