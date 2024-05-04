package org.cantainercraft.micro.users.controller;

import lombok.RequiredArgsConstructor;
import org.cantainercraft.micro.users.dto.ProfileDTO;
import org.cantainercraft.micro.users.dto.ProfileSearchDTO;
import org.cantainercraft.micro.utilits.exception.ExistResourceException;
import org.cantainercraft.micro.utilits.exception.MessageError;
import org.cantainercraft.micro.users.service.ProfileService;
import org.cantainercraft.micro.utilits.exception.NotResourceException;
import org.cantainercraft.micro.utilits.exception.NotValidateParamException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.cantainercraft.project.entity.Profile;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@RestController
@RequestMapping("/profile")
@RequiredArgsConstructor
public class ProfileController {

    private final ProfileService profileService;

    @PostMapping("/id")
    public ResponseEntity<Profile> findById(@RequestBody UUID id){

        Optional<Profile> profile = profileService.findById(id);

        if(profile.isEmpty()){
            throw  new NotResourceException("profile is not exist");
        }

        return ResponseEntity.ok(profileService.findById(id).get());
    }

    @PostMapping("/user")
    public ResponseEntity<Profile> findByUser(@RequestBody ProfileSearchDTO profileSearchDTO){
        String email = profileSearchDTO.getEmail();
        Long userId = profileSearchDTO.getUserId();

        if(!email.trim().isEmpty() && userId <= 0){
            throw new NotValidateParamException("param missed: userId");
        }

        if(email.isEmpty() && userId >0 ){
            throw new NotValidateParamException("param missed: email");
        }

        Optional<Profile> profile = profileService.findByUser(userId, email);

        if(profile.isEmpty()){
            throw new NotResourceException("profile is not exist");
        }
        return ResponseEntity.ok(profile.get());

    }

    @GetMapping("/all")
    public ResponseEntity<List<Profile>> findAll(){
        return ResponseEntity.ok(profileService.findAll());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/add")
    public ResponseEntity<Profile> save(@RequestBody ProfileDTO profileDTO){
        Long userId = profileDTO.getUser() == null? 0 : profileDTO.getUser().getId();
        Optional<Profile> profile = profileService.findByUser(userId,"");

        if(profile.isPresent()){
            throw new ExistResourceException("profile is exist");
        }

        if(profileDTO.getUuid() != null){
            throw new NotValidateParamException("param missed: uuid");
        }

        return ResponseEntity
                .status(201)
                .body(profileService.save(profileDTO));
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PutMapping("/update")
    public ResponseEntity<Boolean> update(@RequestBody ProfileDTO profileDTO){
            Optional<Profile> profile = profileService.findById(profileDTO.getUuid());
            if(profile.isPresent()){
                profileService.update(profileDTO);
                return ResponseEntity.ok(true);
            }
            return new ResponseEntity(MessageError.of("profile is not exist"),HttpStatus.NOT_ACCEPTABLE);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/delete/id")
    public ResponseEntity<Boolean> deleteById(@RequestBody UUID id ){
        Optional<Profile> profile = profileService.findById(id);
        if(profile.isPresent()){
            profileService.deleteById(id);
            return ResponseEntity.ok(true);
        }
        return new ResponseEntity(MessageError.of("profile is not exist"),HttpStatus.NOT_ACCEPTABLE);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/delete/user")
    public ResponseEntity<Boolean> deleteByUser(@RequestBody ProfileSearchDTO profileSearchDTO){

        String email = profileSearchDTO.getEmail() == null ? null :profileSearchDTO.getEmail().trim();
        Long userId = profileSearchDTO.getUserId() == null ? null :profileSearchDTO.getUserId();

        if(!email.trim().isEmpty() && userId <= 0){
            return new ResponseEntity(MessageError.of("param missed: userId"),HttpStatus.NOT_ACCEPTABLE);
        }

        if(email.isEmpty() && userId >0 ){
            return new ResponseEntity(MessageError.of("param missed: email"),HttpStatus.NOT_ACCEPTABLE);
        }

        profileService.deleteByUser(userId,email);
        return ResponseEntity.ok(true);
    }
}
