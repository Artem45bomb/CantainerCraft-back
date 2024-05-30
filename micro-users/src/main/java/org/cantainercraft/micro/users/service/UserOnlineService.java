package org.cantainercraft.micro.users.service;

import org.cantainercraft.micro.users.dto.UserOnlineDTO;
import org.cantainercraft.project.entity.users.User_Online;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserOnlineService {
    List<User_Online> findAll();

    Optional<User_Online> findById(int id);

    User_Online save(UserOnlineDTO UserOnlineDTO);

    void update(UserOnlineDTO UserOnlineDTO);

    void deleteById(int id);


}
