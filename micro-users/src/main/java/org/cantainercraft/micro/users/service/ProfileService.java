package org.cantainercraft.micro.users.service;

import org.cantainercraft.micro.users.dto.ProfileDTO;
import org.cantainercraft.project.entity.users.Profile;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProfileService {
     Profile save (ProfileDTO profileDTO);

     Profile update(ProfileDTO profileDTO);

     Optional<Profile> findById(UUID uuid);

     Optional<Profile> findByUser(Long userId,String email);

     List<Profile> findAll();

     void deleteById(UUID uuid);

     void deleteByUser(Long userId,String email);
}
