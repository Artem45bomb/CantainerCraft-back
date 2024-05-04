package org.cantainercraft.micro.users.service;

import jakarta.servlet.http.HttpServletResponse;
import org.cantainercraft.micro.users.dto.JwtAuthResponse;
import org.cantainercraft.micro.users.dto.UserDTO;

public interface AccountService {
    JwtAuthResponse update(UserDTO userDTO, HttpServletResponse response);
}
