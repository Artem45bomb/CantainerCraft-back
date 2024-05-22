package org.cantainercraft.micro.users.service.impl;

import lombok.RequiredArgsConstructor;
import org.cantainercraft.micro.users.service.ProfileService;
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

    
    public Profile save (ProfileDTO profileDTO){
        Profile profile = profileDTOConvertor.convertProfileDTOToProfile(profileDTO);
        return profileRepository.save(profile);
    }
    
    public Profile update(ProfileDTO profileDTO){
        Profile profile = profileDTOConvertor.convertProfileDTOToProfile(profileDTO);
        return profileRepository.save(profile);
    }
    
    public Optional<Profile> findById(UUID uuid){
        return profileRepository.findById(uuid);
    }
    
    public Optional<Profile> findByUser(Long userId,String email){
        return  profileRepository.findByUserIdOrUserEmail(userId,email);
    }


    public List<Profile> findAll(){
        return profileRepository.findAll();
    }
    
    public void deleteById(UUID uuid){
        profileRepository.deleteById(uuid);
    }
    
    public void deleteByUser(Long userId,String email){
        profileRepository.deleteByUserIdOrUserEmail(userId,email);
    }
}
