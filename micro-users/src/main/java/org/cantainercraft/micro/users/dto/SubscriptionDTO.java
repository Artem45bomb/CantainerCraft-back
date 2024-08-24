package org.cantainercraft.micro.users.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SubscriptionDTO {
    @Min(value = 1,message = "String length must be greater than 0")
    @NotNull(message = "Name is empty")
    private String name;
    @NotNull(message = "Cost is empty")
    private Long cost;
}
