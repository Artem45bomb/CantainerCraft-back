package org.containercraft.servicedbstore.service.impl;

import lombok.RequiredArgsConstructor;
import org.containercraft.servicedbstore.entity.Role;
import org.containercraft.servicedbstore.repository.RoleRepository;
import org.containercraft.servicedbstore.service.RoleService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository repository;


    @Override
    public Flux<Role> findAll(){
        return repository.findAll();
    }
}
