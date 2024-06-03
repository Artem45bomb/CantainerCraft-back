package org.cantainercraft.micro.users.service;

import org.cantainercraft.micro.users.dto.UserOnlineDTO;
import org.cantainercraft.project.entity.users.User_Online;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserOnlineService {
    List<User_Online> findAll();

    User_Online findById(UUID uuid);

    User_Online save(UserOnlineDTO UserOnlineDTO);

    User_Online update(UserOnlineDTO UserOnlineDTO);

    void deleteById(UUID uuid);


}
