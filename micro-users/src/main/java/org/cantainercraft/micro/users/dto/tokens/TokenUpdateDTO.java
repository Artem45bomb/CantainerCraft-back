package org.cantainercraft.micro.users.dto.tokens;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TokenUpdateDTO implements Serializable {

    @Min(value = 1,message = "token length greater than 0")
    @NotBlank(message = "token length is 0")
    private String tokenRefresh;
}
