package org.containercraft.servicedbstore.repository;


import org.containercraft.servicedbstore.entity.Role;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends ReactiveCrudRepository<Role,Long> {
}
