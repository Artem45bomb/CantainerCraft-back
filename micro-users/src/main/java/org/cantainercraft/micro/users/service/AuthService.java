package org.cantainercraft.micro.users.service;

import jakarta.servlet.http.HttpServletResponse;
import org.cantainercraft.micro.users.dto.tokens.JwtAuthResponse;
import org.cantainercraft.micro.users.dto.SignInAuthDTO;
import org.cantainercraft.micro.users.dto.SignUpAuthDTO;

public interface AuthService {
    JwtAuthResponse signup(SignUpAuthDTO dto, HttpServletResponse response);

    JwtAuthResponse login(SignInAuthDTO dto,HttpServletResponse response);
}
