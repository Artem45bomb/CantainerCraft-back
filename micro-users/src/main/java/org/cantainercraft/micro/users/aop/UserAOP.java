package org.cantainercraft.micro.users.aop;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.cantainercraft.micro.users.dto.ProfileDTO;
import org.cantainercraft.micro.users.dto.UserOnlineDTO;
import org.cantainercraft.micro.users.service.ProfileService;
import org.cantainercraft.micro.users.service.UserOnlineService;
import org.cantainercraft.micro.users.service.UserService;
import org.cantainercraft.project.entity.users.User;
import org.springframework.stereotype.Component;

import java.util.Optional;


@Slf4j
@Aspect
@Component
@RequiredArgsConstructor
public class UserAOP {
    private final UserService userService;
    private final ProfileService profileService;
    private final UserOnlineService userOnlineService;

    @AfterReturning(value = "execution(* org.cantainercraft.micro.users.service.UserService.save(*))",returning = "userSave")
    public void initServiceForUser(User userSave){
        Optional<User> userFind = userService.findById(userSave.getId());

        userFind.ifPresent(user -> {
            profileService.save(ProfileDTO.builder()
                    .user(user)
                    .build());
            userOnlineService.save(UserOnlineDTO.builder()
                    .is_online(false)
                    .user(user)
                    .build());
        });

    }
}
