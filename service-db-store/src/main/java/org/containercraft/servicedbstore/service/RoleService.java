package org.containercraft.servicedbstore.service;

import org.containercraft.servicedbstore.entity.Role;
import reactor.core.publisher.Flux;

public interface RoleService {
    Flux<Role> findAll();
}
