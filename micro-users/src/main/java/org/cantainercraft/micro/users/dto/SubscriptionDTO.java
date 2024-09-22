package org.cantainercraft.micro.users.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SubscriptionDTO {
    @Size(min = 1,message = "String length must be greater than 0")
    @NotNull(message = "Name is empty")
    private String name;
    @NotNull(message = "Cost is empty")
    private Long cost;
}
