package org.cantainercraft.micro.users.service;

import org.cantainercraft.micro.users.dto.RoleDTO;
import org.cantainercraft.micro.users.dto.RoleUpdateDTO;
import org.cantainercraft.project.entity.Role;

import java.util.List;

public interface RoleService {
      List<Role> findAll();

      Role findById(Long id);

      Role save(RoleDTO RoleDTO);

      boolean update(RoleUpdateDTO RoleUpdateDTO);

      void deleteById(Long id);
}
