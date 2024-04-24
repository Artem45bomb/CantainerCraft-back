package org.cantainercraft.micro.users.controller;

import org.cantainercraft.micro.users.dto.ProfileDTO;
import org.cantainercraft.micro.users.dto.ProfileSearchDTO;
import org.cantainercraft.micro.users.exception.MessageError;
import org.cantainercraft.micro.users.service.ProfileService;
import org.cantainercraft.micro.users.service.impl.ProfileServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.cantainercraft.project.entity.Profile;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@RestController
@RequestMapping("/profile")
public class ProfileController {

    private final ProfileService profileService;

    @Autowired
    public ProfileController(ProfileService profileService){
        this.profileService = profileService;
    }

    @PostMapping("/id")
    public ResponseEntity<Profile> findById(@RequestBody UUID id){

        Optional<Profile> profile = profileService.findById(id);

        if(profile.isPresent()){
            return ResponseEntity.ok(profileService.findById(id).get());
        }

        return new ResponseEntity(MessageError.of("profile is not exist"),HttpStatus.NOT_FOUND);
    }

    @PostMapping("/user")
    public ResponseEntity<Profile> findByUser(@RequestBody ProfileSearchDTO profileSearchDTO){
        String email = profileSearchDTO.getEmail();
        Long userId = profileSearchDTO.getUserId();

        if(!email.trim().isEmpty() && userId <= 0){
            return new ResponseEntity(MessageError.of("param missed: userId"),HttpStatus.NOT_ACCEPTABLE);
        }

        if(email.isEmpty() && userId >0 ){
            return new ResponseEntity(MessageError.of("param missed: email"),HttpStatus.NOT_ACCEPTABLE);
        }

        Optional<Profile> profile = profileService.findByUser(userId, email);

        if(profile.isPresent()){
            return ResponseEntity.ok(profile.get());
        }

        return new ResponseEntity( MessageError.of("profile is not exist"),HttpStatus.NOT_ACCEPTABLE);

    }

    @GetMapping("/all")
    public ResponseEntity<List<Profile>> findAll(){
        return ResponseEntity.ok(profileService.findAll());
    }

    @PostMapping("/add")
    public ResponseEntity<Profile> save(@RequestBody ProfileDTO profileDTO){
        Long userId = profileDTO.getUser() == null? 0 : profileDTO.getUser().getId();
        Optional<Profile> profile = profileService.findByUser(userId,"");

        if(profile.isPresent()){
            return new ResponseEntity(MessageError.of("profile is exist"),HttpStatus.NOT_ACCEPTABLE);
        }

        if(profileDTO.getUuid() != null){
            return new ResponseEntity(MessageError.of("param missed: uuid"),HttpStatus.NOT_ACCEPTABLE);
        }

        return ResponseEntity
                .status(201)
                .body(profileService.save(profileDTO));
    }

    @PutMapping("/update")
    public ResponseEntity<Boolean> update(@RequestBody ProfileDTO profileDTO){
            Optional<Profile> profile = profileService.findById(profileDTO.getUuid());
            if(profile.isPresent()){
                profileService.update(profileDTO);
                return ResponseEntity.ok(true);
            }
            return new ResponseEntity(MessageError.of("profile is not exist"),HttpStatus.NOT_ACCEPTABLE);
    }

    @PutMapping("/delete/id")
    public ResponseEntity<Boolean> deleteById(@RequestBody UUID id ){
        Optional<Profile> profile = profileService.findById(id);
        if(profile.isPresent()){
            profileService.deleteById(id);
            return ResponseEntity.ok(true);
        }
        return new ResponseEntity(MessageError.of("profile is not exist"),HttpStatus.NOT_ACCEPTABLE);
    }

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
