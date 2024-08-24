package org.cantainercraft.micro.users.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.cantainercraft.micro.users.dto.ProfileImageDTO;
import org.cantainercraft.micro.users.service.ProfileImageService;
import org.cantainercraft.project.entity.users.Profile_Image;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/profileImage")
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
    public ResponseEntity<Profile_Image> save(@Valid @RequestBody ProfileImageDTO dto){
            return ResponseEntity.ok(profileImageService.save(dto));
    }


    @PutMapping("/update")
    public ResponseEntity<Profile_Image> update(@Valid @RequestBody ProfileImageDTO dto){
        return ResponseEntity.ok(profileImageService.update(dto));
    }


    @DeleteMapping("/delete/{uuid}")
    public void deleteById(@PathVariable UUID uuid ){
        profileImageService.deleteById(uuid);
    }

}
