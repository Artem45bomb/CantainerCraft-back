package org.cantainercraft.micro.users.service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.cantainercraft.micro.users.dto.tokens.JwtAuthResponse;
import org.cantainercraft.micro.users.dto.UserClientDTO;
import org.cantainercraft.micro.users.dto.UserDTO;

public interface AccountService {
    JwtAuthResponse update(UserDTO userDTO, HttpServletResponse response);

    UserClientDTO loadedUser(HttpServletRequest httpServletRequest);
}
