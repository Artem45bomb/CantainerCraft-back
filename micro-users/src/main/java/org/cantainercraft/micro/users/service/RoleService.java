package org.cantainercraft.micro.users.service;

import org.cantainercraft.micro.users.dto.RoleDTO;
import org.cantainercraft.project.entity.users.Role;

import java.util.List;

public interface RoleService {
      List<Role> findAll();

      Role findById(Long id);

      Role save(RoleDTO roleDTO);

      Role update(RoleDTO roleDTO);

      void deleteById(Long id);
}
