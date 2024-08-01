package org.cantainercraft.micro.users.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.cantainercraft.micro.users.repository.UserRepository;
import org.cantainercraft.micro.users.service.ProfileService;
import org.cantainercraft.micro.utilits.exception.ExistResourceException;
import org.cantainercraft.micro.utilits.exception.NotResourceException;
import org.cantainercraft.project.entity.users.User;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.cantainercraft.micro.users.convertor.ProfileDTOConvertor;
import org.cantainercraft.micro.users.dto.ProfileDTO;
import org.cantainercraft.micro.users.repository.ProfileRepository;
import org.cantainercraft.project.entity.users.Profile;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class ProfileServiceImpl implements ProfileService {
    public final ProfileRepository profileRepository;
    public final ProfileDTOConvertor profileDTOConvertor;
    public final UserRepository userRepository;

    @Override
    public Profile save (ProfileDTO profileDTO){
        Profile profile = profileDTOConvertor.convertDTOToEntity(profileDTO);

        if(profileRepository.existsByUser(profile.getUser())) throw new ExistResourceException("profile for user is create");

        return profileRepository.save(profile);
    }

    @Override
    public Profile update(ProfileDTO profileDTO) {

        if(!profileRepository.existsById(profileDTO.getUuid())) throw new NotResourceException("profile is not exist");

        Profile profile = profileDTOConvertor.convertDTOToEntity(profileDTO);
        Optional<User> user = userRepository.findById(profileDTO.getUser().getId());

        user.map((e) -> {
            profile.setUser(e);
            return null;
        });

        return profileRepository.save(profile);
    }

    @Override
    @Cacheable(value = "profiles",key = "#uuid")
    public Optional<Profile> findById(UUID uuid){
        return profileRepository.findById(uuid);
    }

    @Override
    public Optional<Profile> findByUser(Long userId,String email){
        return  profileRepository.findByUserIdOrUserEmail(userId,email);
    }


    @Override
    public List<Profile> findAll(){
        return profileRepository.findAll();
    }

    @Override
    @CacheEvict(value = "profiles",key = "#uuid")
    public void deleteById(UUID uuid){
        if(!profileRepository.existsById(uuid)) throw new NotResourceException("profile is not exist");

        profileRepository.deleteById(uuid);
    }

    @Override
    @CacheEvict(value = "profiles",allEntries = true)
    public void deleteByUser(Long userId,String email){
        if(profileRepository.existsByUserIdOrUserEmail(userId,email)){
            throw new NotResourceException("profile is not exist");
        }

        profileRepository.deleteByUserIdOrUserEmail(userId,email);
    }
}
