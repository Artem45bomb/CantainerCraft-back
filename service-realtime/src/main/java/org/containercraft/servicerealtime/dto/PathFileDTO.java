package org.containercraft.servicerealtime.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.UUID;


@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
public class PathFileDTO {
    private String pathToFile;
    private String filename;

    private UUID uuid;
}
