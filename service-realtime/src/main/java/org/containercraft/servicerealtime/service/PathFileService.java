package org.containercraft.servicerealtime.service;

import org.containercraft.servicerealtime.dto.PathFileDTO;
import org.containercraft.servicerealtime.entity.PathFile;
import java.util.List;
import java.util.UUID;

public interface PathFileService {
    List<PathFile> findAll();

    PathFile findById(UUID uuid);

    PathFile findByFilename(String filename);

    PathFile save(PathFileDTO dto);

    PathFile update(PathFileDTO dto);

    void deleteById(UUID uuid);

    void deleteByFilename(String filename);
}


