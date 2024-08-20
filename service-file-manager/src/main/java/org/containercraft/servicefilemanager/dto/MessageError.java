package org.containercraft.servicefilemanager.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MessageError {
    private String message;

    public MessageError(String message){
        this.message = message;
    }
}
