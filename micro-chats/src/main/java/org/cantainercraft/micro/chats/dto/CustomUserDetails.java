package org.cantainercraft.micro.chats.dto;


import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.cantainercraft.project.entity.users.Role;
import org.cantainercraft.project.entity.users.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RequiredArgsConstructor
public class CustomUserDetails extends User implements UserDetails, Serializable {
    private final String password;
    private final String username;
    @Setter
    Collection<? extends GrantedAuthority> authorities;


    public CustomUserDetails(User user){
        this.password = user.getPassword();
        this.username = user.getUsername();
        List<GrantedAuthority> auth = new ArrayList<>();

        for(Role role : user.getRoles()){
            auth.add(new SimpleGrantedAuthority(role.getRole().toUpperCase()));
        }
        this.authorities = auth;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword(){
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
