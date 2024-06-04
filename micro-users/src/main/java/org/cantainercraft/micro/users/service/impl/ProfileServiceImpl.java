package org.cantainercraft.micro.users.service.impl;

import lombok.RequiredArgsConstructor;
import org.cantainercraft.micro.users.service.ProfileService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.cantainercraft.micro.users.convertor.ProfileDTOConvertor;
import org.cantainercraft.micro.users.dto.ProfileDTO;
import org.cantainercraft.micro.users.repository.ProfileRepository;
import org.cantainercraft.project.entity.users.Profile;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProfileServiceImpl implements ProfileService {
    public final ProfileRepository profileRepository;
    public final ProfileDTOConvertor profileDTOConvertor;


    @Override
    @Cacheable(value = "profiles",key = "#result.uuid")
    public Profile save (ProfileDTO profileDTO){
        Profile profile = profileDTOConvertor.convertDTOToEntity(profileDTO);
        return profileRepository.save(profile);
    }

    @Override
    @CachePut(value = "profiles",key = "#profileDTO.uuid")
    public Profile update(ProfileDTO profileDTO){
        Profile profile = profileDTOConvertor.convertDTOToEntity(profileDTO);
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
        profileRepository.deleteById(uuid);
    }

    @Override
    @CacheEvict(value = "profiles",allEntries = true)
    public void deleteByUser(Long userId,String email){
        profileRepository.deleteByUserIdOrUserEmail(userId,email);
    }
}
