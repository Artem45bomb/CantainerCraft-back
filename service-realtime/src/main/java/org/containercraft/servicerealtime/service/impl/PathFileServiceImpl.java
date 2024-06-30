package org.containercraft.servicerealtime.service.impl;

import lombok.RequiredArgsConstructor;
import org.containercraft.servicerealtime.repository.PathRepository;
import org.containercraft.servicerealtime.service.PathFileService;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PathFileServiceImpl implements PathFileService {
    private final PathRepository repository;
}
