package org.cantainercraft.micro.users.aop;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.cantainercraft.micro.users.repository.ProfileRepository;
import org.cantainercraft.micro.users.repository.UserOnlineRepository;
import org.cantainercraft.micro.users.repository.UserRepository;
import org.cantainercraft.project.entity.users.Profile;
import org.cantainercraft.project.entity.users.User;
import org.cantainercraft.project.entity.users.User_Online;
import org.springframework.stereotype.Component;

import java.util.Optional;


@Slf4j
@Aspect
@Component
@RequiredArgsConstructor
public class UserAOP {
    private final UserRepository userRepository;
    private final ProfileRepository profileRepository;
    private final UserOnlineRepository userOnlineRepository;

    @AfterReturning(value = "execution(* org.cantainercraft.micro.users.service.UserService.save(*))",returning = "userSave")
    public void initServiceForUser(User userSave){
        Optional<User> userFind = userRepository.findById(userSave.getId());

        userFind.ifPresent(user -> {
            profileRepository.save(Profile.builder()
                    .user(user)
                    .build());
            userOnlineRepository.save(User_Online.builder()
                    .is_online(false)
                    .user(user)
                    .build());
        });

    }
}
