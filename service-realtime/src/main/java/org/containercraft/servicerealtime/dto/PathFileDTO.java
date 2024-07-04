package org.containercraft.servicerealtime.dto;

import lombok.*;

import java.util.UUID;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PathFileDTO {
    private String pathToFile;
    private String filename;

    private UUID uuid;
}
