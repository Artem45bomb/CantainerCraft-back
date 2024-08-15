package org.containercraft.servicefilemanager.config;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;


@Getter
@ConfigurationProperties("storage")
public class StorageProperties {
    private final String location = "files";
}

