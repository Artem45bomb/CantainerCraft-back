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

    @PostMapping("/uuid")
    public ResponseEntity<Optional<Profile_Image>> findById(@RequestBody UUID uuid) {

        Optional<Profile_Image> profileImage = profileImageService.findById(uuid);

        if (profileImage.isEmpty()) {
            throw new NotResourceException("Profile image not found");
        }
        return ResponseEntity.ok(profileImageService.findById(uuid));
    }

    @PostMapping("/add")
    public ResponseEntity<Profile_Image> save(@RequestBody ProfileImageDTO profileImageDTO){
        Optional<Profile_Image> profileImage = profileImageService.findById(profileImageDTO.getUuid());
        if (profileImage.isEmpty()) {
            throw new NotResourceException("Profile id not founded");
        }
        else{
            return ResponseEntity.ok(profileImageService.save(profileImageDTO));
        }
    }


    @PutMapping("/update")
    public ResponseEntity<Boolean> update(@RequestBody ProfileImageDTO profileImageDTO){
        Optional<Profile_Image> profileImage = profileImageService.findById(profileImageDTO.getUuid());
        if(profileImage.isPresent()){
            profileImageService.update(profileImageDTO);
            return ResponseEntity.ok(true);
        }
        return new ResponseEntity(MessageError.of("profile is not exist"),HttpStatus.NOT_ACCEPTABLE);
    }


    @PutMapping("/delete/id")
    public ResponseEntity<Boolean> deleteById(@RequestBody UUID uuid ){
        Optional<Profile_Image> profileImage = profileImageService.findById(uuid);
        if(profileImage.isPresent()){
            profileImageService.deleteById(uuid);
            return ResponseEntity.ok(true);
        }
        return new ResponseEntity(MessageError.of("profile is not exist"),HttpStatus.NOT_ACCEPTABLE);
    }

}
