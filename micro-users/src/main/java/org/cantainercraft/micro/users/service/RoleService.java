package org.cantainercraft.micro.users.service;

import org.cantainercraft.micro.users.dto.RoleDTO;
import org.cantainercraft.project.entity.users.Role;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface RoleService {
      List<Role> findAll();

      Role findById(Long id);

      Role save(RoleDTO roleDTO);

      Role update(RoleDTO roleDTO);

      void deleteById(Long id);

      Optional<Role> findByRole(String role);
}
