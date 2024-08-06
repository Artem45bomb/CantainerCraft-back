package org.cantainercraft.micro.users.service.tokens;

import jakarta.servlet.http.HttpServletResponse;

public interface AccessTokenService {
    String update(String tokenId, HttpServletResponse request);
}
