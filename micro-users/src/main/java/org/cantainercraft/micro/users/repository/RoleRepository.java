package org.cantainercraft.micro.users.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.cantainercraft.project.entity.users.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {
}
