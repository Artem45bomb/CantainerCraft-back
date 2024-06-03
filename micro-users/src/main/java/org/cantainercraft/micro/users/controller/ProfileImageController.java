package org.cantainercraft.micro.users.controller;

import lombok.RequiredArgsConstructor;
import org.cantainercraft.micro.users.dto.ProfileImageDTO;
import org.cantainercraft.micro.users.service.ProfileImageService;
import org.cantainercraft.micro.utilits.exception.MessageError;
import org.cantainercraft.micro.utilits.exception.NotResourceException;
import org.cantainercraft.project.entity.users.Profile_Image;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/profileImage")
@RequiredArgsConstructor
public class ProfileImageController {

    private final ProfileImageService profileImageService;

    @GetMapping("/uuid")
    public ResponseEntity<Optional<Profile_Image>> findById(@RequestBody UUID uuid) {
        return ResponseEntity.ok(profileImageService.findById(uuid));
    }

    @PostMapping("/add")
    public ResponseEntity<Profile_Image> save(@RequestBody ProfileImageDTO profileImageDTO){
            return ResponseEntity.ok(profileImageService.save(profileImageDTO));
    }


    @PutMapping("/update")
    public ResponseEntity<Boolean> update(@RequestBody ProfileImageDTO profileImageDTO){
        return new ResponseEntity(MessageError.of("profile is not exist"),HttpStatus.NOT_ACCEPTABLE);
    }


    @DeleteMapping("/delete/id")
    public ResponseEntity<Boolean> deleteById(@RequestBody UUID uuid ){
        return new ResponseEntity(MessageError.of("profile is not exist"),HttpStatus.NOT_ACCEPTABLE);
    }

}
