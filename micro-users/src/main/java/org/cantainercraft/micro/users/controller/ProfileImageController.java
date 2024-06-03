package org.cantainercraft.micro.users.controller;

import jakarta.ws.rs.Path;
import lombok.RequiredArgsConstructor;
import org.cantainercraft.micro.users.dto.ProfileImageDTO;
import org.cantainercraft.micro.users.service.ProfileImageService;
import org.cantainercraft.micro.utilits.exception.MessageError;
import org.cantainercraft.micro.utilits.exception.NotResourceException;
import org.cantainercraft.project.entity.users.Profile_Image;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/profileImage")
@RequiredArgsConstructor
public class ProfileImageController {

    private final ProfileImageService profileImageService;

    @GetMapping("/all")
    public ResponseEntity<List<Profile_Image>> findAll(){
        return ResponseEntity.ok(profileImageService.findAll());
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<Profile_Image> findById(@PathVariable UUID uuid) {
        return ResponseEntity.ok(profileImageService.findById(uuid));
    }

    @PostMapping("/add")
    public ResponseEntity<Profile_Image> save(@RequestBody ProfileImageDTO profileImageDTO){
            return ResponseEntity.ok(profileImageService.save(profileImageDTO));
    }


    @PutMapping("/update")
    public ResponseEntity<Profile_Image> update(@RequestBody ProfileImageDTO profileImageDTO){
        return ResponseEntity.ok(profileImageService.update(profileImageDTO));
    }


    @DeleteMapping("/delete/{uuid}")
    public void deleteById(@PathVariable UUID uuid ){
        profileImageService.deleteById(uuid);
    }

}
