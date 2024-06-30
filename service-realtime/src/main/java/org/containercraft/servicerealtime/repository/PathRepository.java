package org.containercraft.servicerealtime.repository;

import org.containercraft.servicerealtime.entity.PathFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PathRepository extends JpaRepository<PathFile, UUID> {
}
