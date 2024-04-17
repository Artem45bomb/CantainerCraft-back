package ru.javabegin.backend.todo.eurekaclient.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.javabegin.backend.todo.eurekaclient.dto.ProfileDTO;
import ru.javabegin.backend.todo.eurekaclient.dto.ProfileSearchDTO;
import ru.javabegin.backend.todo.eurekaclient.service.ProfileService;
import ru.weather.project.entity.Profile;
import ru.weather.project.entity.User;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;


@RestController
@RequestMapping("/profile")
public class ProfileController {

    private final ProfileService profileService;

    public ProfileController(ProfileService profileService){
        this.profileService = profileService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Profile> findById(@PathVariable UUID id){

        Optional<Profile> profile = profileService.findById(id);

        if(profile.isPresent()){
            return ResponseEntity.ok(profileService.findById(id).get());
        }

        return new ResponseEntity("profile is not exist",HttpStatus.NO_CONTENT);
    }

    @PostMapping
    public ResponseEntity<Profile> findByUser(@RequestBody ProfileSearchDTO profileSearchDTO){
        String email = profileSearchDTO.getEmail();
        Long userId = profileSearchDTO.getUserId();

        if(!email.trim().isEmpty() && userId == 0){
            return new ResponseEntity("param missed: userId",HttpStatus.NOT_ACCEPTABLE);
        }

        if(email.isEmpty() && userId != 0 ){
            return new ResponseEntity("param missed: email",HttpStatus.NOT_ACCEPTABLE);
        }

        Optional<Profile> profile = profileService.findByUser(userId, email);

        if(profile.isPresent()){
            return ResponseEntity.ok(profile.get());
        }

        return new ResponseEntity("profile is not exist",HttpStatus.NO_CONTENT);

    }

    @GetMapping("/all")
    public ResponseEntity<List<Profile>> findAll(){
        return ResponseEntity.ok(profileService.findAll());
    }

    @PostMapping("/add")
    public ResponseEntity<Profile> save(@RequestBody ProfileDTO profileDTO){

        if(profileDTO.getUuid() != null){
            return new ResponseEntity("param missed: uuid",HttpStatus.NOT_ACCEPTABLE);
        }

        return ResponseEntity.ok(profileService.save(profileDTO));
    }

    @PutMapping("/update")
    public ResponseEntity<Boolean> update(@RequestBody ProfileDTO profileDTO){
            Optional<Profile> profile = profileService.findById(profileDTO.getUuid());
            if(profile.isPresent()){
                profileService.update(profileDTO);
                return ResponseEntity.ok(true);
            }
            return new ResponseEntity("profile is not exist",HttpStatus.NON_AUTHORITATIVE_INFORMATION);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable UUID id ){
        Optional<Profile> profile = profileService.findById(id);
        if(profile.isPresent()){
            profileService.delete(id);
            return ResponseEntity.ok(true);
        }
        return new ResponseEntity("profile is not exist",HttpStatus.NON_AUTHORITATIVE_INFORMATION);
    }

    @PutMapping("/delete/user")
    public ResponseEntity<Boolean> deleteByUser(@RequestBody ProfileSearchDTO profileSearchDTO){

        String email = profileSearchDTO.getEmail() == null ? null :profileSearchDTO.getEmail().trim();
        Long userId = profileSearchDTO.getUserId() == null ? null :profileSearchDTO.getUserId();

        if(!email.isEmpty() && (userId == 0 || userId==null)){
            return new ResponseEntity("param missed: userId",HttpStatus.NOT_ACCEPTABLE);
        }

        if(email.isEmpty() && (userId != 0 || userId !=null)){
            return new ResponseEntity("param missed: email",HttpStatus.NOT_ACCEPTABLE);
        }

        profileService.deleteByUser(userId,email);
        return ResponseEntity.ok(true);
    }
}
