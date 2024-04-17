package ru.javabegin.backend.todo.eurekaclient.service;

import org.springframework.stereotype.Service;
import ru.javabegin.backend.todo.eurekaclient.convertor.ProfileDTOConvertor;
import ru.javabegin.backend.todo.eurekaclient.dto.ProfileDTO;
import ru.javabegin.backend.todo.eurekaclient.repository.ProfileRepository;
import ru.weather.project.entity.Profile;
import ru.weather.project.entity.User;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProfileService {
    public final ProfileRepository profileRepository;
    public final ProfileDTOConvertor profileDTOConvertor;
    
    public ProfileService(ProfileRepository profileRepository, ProfileDTOConvertor profileDTOConvertor) {
        this.profileRepository = profileRepository;
        this.profileDTOConvertor = profileDTOConvertor;
    }
    
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
    
    public void delete(UUID uuid){
        profileRepository.deleteById(uuid);
    }
    
    public void deleteByUser(Long userId,String email){
        profileRepository.deleteByUserIdOrUserEmail(userId,email);
    }
}
