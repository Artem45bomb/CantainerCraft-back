package org.cantainercraft.micro.users.service.impl;

import lombok.RequiredArgsConstructor;
import org.cantainercraft.micro.users.dto.UserDTO;
import org.cantainercraft.micro.users.service.RoleService;
import org.cantainercraft.micro.users.service.UserAuthService;
import org.cantainercraft.micro.users.service.UserService;
import org.cantainercraft.micro.utilits.exception.NotResourceException;
import org.cantainercraft.micro.utilits.service.ConvertorDTO;
import org.cantainercraft.project.entity.users.Role;
import org.cantainercraft.project.entity.users.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserAuthServiceImpl implements UserAuthService {
    private final UserService userService;
    private final RoleService roleService;
    private final ConvertorDTO<UserDTO, User> userConvertorDTO;

    @Override
    public User authUser(User user) {
        Optional<Role> role = roleService.findByRole("User");

        if(role.isEmpty()) throw new NotResourceException("service not create role");

        user.setRoles(List.of(role.get()));
        return userService.save(userConvertorDTO.convertEntityToDTO(user));
    }
}
