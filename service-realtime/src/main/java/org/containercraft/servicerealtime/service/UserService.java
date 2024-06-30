package org.containercraft.servicerealtime.service;

import org.containercraft.servicerealtime.dto.UserDTO;
import org.containercraft.servicerealtime.entity.User;

import java.util.List;
import java.util.UUID;

public interface UserService {
    List<User> findAll();

    User findById(Long userId);

    User save(UserDTO dto);

    User update(UserDTO dto);

    void deleteById(Long userId);
}
