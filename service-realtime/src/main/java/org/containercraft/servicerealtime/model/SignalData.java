package org.containercraft.servicerealtime.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SignalData {
    private Long userId;
    private String body;
    private String typeSignal;
}
