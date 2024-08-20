package org.containercraft.servicefilemanager.service.files;


import org.containercraft.servicefilemanager.dto.ContentDTO;
import org.containercraft.servicefilemanager.entity.Content;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ContentService {

    Content save(ContentDTO dto);
    
    Optional<Content> findBySrc(String src);

    Content update(Content dto);

    void delete(UUID uuid);

    List<Content> findAll();

    Optional<Content> findById(UUID uuid);
}
