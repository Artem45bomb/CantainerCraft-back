package org.cantainercraft.micro.users.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class JwtAuthResponse {
    private String accessToken;
    private String token;
}
