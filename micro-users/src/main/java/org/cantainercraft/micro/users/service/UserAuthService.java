package org.cantainercraft.micro.users.service;
import org.cantainercraft.project.entity.users.User;

public interface UserAuthService {
    User authUser(User user);
}
