package org.cantainercraft.micro.users.factory;

import lombok.RequiredArgsConstructor;
import org.cantainercraft.micro.users.repository.UserRepository;
import org.cantainercraft.micro.users.service.impl.JwtService;
import org.cantainercraft.project.entity.Token;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class AccessTokenFactory implements Function<Token,String> {
    private final JwtService jwtService;
    @Override
    public String apply(Token refreshToken) {
        return jwtService.GenerateToken(refreshToken);
    }
}
