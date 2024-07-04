package org.containercraft.servicerealtime.service.impl;

import lombok.RequiredArgsConstructor;
import org.containercraft.servicerealtime.dto.PathFileDTO;
import org.containercraft.servicerealtime.entity.PathFile;
import org.containercraft.servicerealtime.repository.PathRepository;
import org.containercraft.servicerealtime.service.PathFileService;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class PathFileServiceImpl implements PathFileService {
    private final PathRepository repository;

    @Override
    public List<PathFile> findAll() {
        return null;
    }

    @Override
    public PathFile findById(UUID uuid) {
        return null;
    }

    @Override
    public PathFile findByFilename(String filename) {
        return null;
    }

    @Override
    public PathFile save(PathFileDTO dto) {
        return null;
    }

    @Override
    public PathFile update(PathFileDTO dto) {
        return null;
    }

    @Override
    public void deleteById(UUID uuid) {

    }

    @Override
    public void deleteByFilename(String filename) {

    }
}
