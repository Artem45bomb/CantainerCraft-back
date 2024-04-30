package org.cantainercraft.micro.users.service.impl;

import lombok.RequiredArgsConstructor;
import org.cantainercraft.micro.users.dto.CustomUserDetails;
import org.cantainercraft.micro.users.repository.UserRepository;

import org.cantainercraft.micro.utilits.exception.NotResourceException;
import org.cantainercraft.project.entity.Role;
import org.cantainercraft.project.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class UserServiceDetailsImpl implements UserDetailsService {
    public final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(username);

        if(user.isEmpty()){
            throw new NotResourceException("user is not exist");
        }
        CustomUserDetails customUserDetails = new CustomUserDetails(user.get());
        List<GrantedAuthority> grantedAuthorities  = new ArrayList<>();
        for(Role role : user.get().getRoles()){
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getRole().toUpperCase()));
        }
        customUserDetails.setAuthorities(grantedAuthorities);
        return customUserDetails;


    }
}
