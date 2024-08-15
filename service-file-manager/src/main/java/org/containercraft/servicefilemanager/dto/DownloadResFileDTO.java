package org.containercraft.servicefilemanager.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DownloadResFileDTO {
    private String filename;
    private byte[] filePart;

    private int startByte;
    private int endByte;
}
