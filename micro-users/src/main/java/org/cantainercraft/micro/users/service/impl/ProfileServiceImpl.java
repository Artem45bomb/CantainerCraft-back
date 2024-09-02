package org.cantainercraft.micro.users.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.cantainercraft.micro.users.repository.UserRepository;
import org.cantainercraft.micro.users.service.ProfileService;
import org.cantainercraft.micro.utilits.exception.ExistResourceException;
import org.cantainercraft.micro.utilits.exception.NotResourceException;
import org.cantainercraft.project.entity.users.User;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
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
    public final ProfileRepository repository;
    public final ProfileDTOConvertor convertor;
    public final UserRepository userRepository;

    @Override
    public Profile save (ProfileDTO dto){
        Profile profile = convertor.convertDTOToEntity(dto);

        if(repository.existsByUser(profile.getUser())) throw new ExistResourceException("profile for user is create");

        return repository.save(profile);
    }

    @Override
    @CachePut(value = "profiles",key = "#dto.uuid")
    public Profile update(ProfileDTO dto) {
        Profile profile = convertor.convertDTOToEntity(dto);
        if(!repository.existsById(dto.getUuid())) throw new NotResourceException("profile is not exist");

        return repository.save(profile);
    }

    @Override
    @Cacheable(value = "profiles",key = "#uuid",unless = "#result == null")
    public Optional<Profile> findById(UUID uuid){
        return repository.findById(uuid);
    }

    @Override
    public Optional<Profile> findByUser(Long userId,String email){
        return  repository.findByUserIdOrUserEmail(userId,email);
    }


    @Override
    public List<Profile> findAll(){
        return repository.findAll();
    }

    @Override
    @CacheEvict(value = "profiles",key = "#uuid")
    public void deleteById(UUID uuid){
        if(!repository.existsById(uuid)) throw new NotResourceException("profile is not exist");

        repository.deleteById(uuid);
    }

    @Override
    @CacheEvict(value = "profiles",allEntries = true)
    public void deleteByUser(Long userId,String email){
        if(repository.existsByUserIdOrUserEmail(userId,email)){
            throw new NotResourceException("profile is not exist");
        }

        repository.deleteByUserIdOrUserEmail(userId,email);
    }
}
