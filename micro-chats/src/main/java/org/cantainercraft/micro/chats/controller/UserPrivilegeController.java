package org.cantainercraft.micro.chats.controller;

import lombok.RequiredArgsConstructor;
import org.cantainercraft.micro.chats.dto.UserPrivilegeDTO;
import org.cantainercraft.micro.chats.service.UserPrivilegeService;
import org.cantainercraft.project.entity.chats.User_Privilege;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/user-privileges")
@RequiredArgsConstructor
public class UserPrivilegeController {

    private final UserPrivilegeService service;

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping("/all")
    public ResponseEntity<List<User_Privilege>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @PostMapping("/uuid")
    public ResponseEntity<Optional<User_Privilege>> findById(@RequestBody UUID id) {
//        Optional<User_Privilege> userPrivilege = service.findById(id);
//        if(userPrivilege.isEmpty()){
//            throw new NotResourceException("No content");
//        }
//        return ResponseEntity.ok(userPrivilege.get());
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping("/userId")
    public ResponseEntity<Optional<User_Privilege>> findByUserId(Long id) {
        return ResponseEntity.ok(service.findByUserId(id));
    }

    @PostMapping("/add")
    public ResponseEntity<User_Privilege> create(@RequestBody UserPrivilegeDTO dto) {
        return ResponseEntity.ok(service.save(dto));
    }

    @PutMapping("/update")
    public ResponseEntity<User_Privilege> update(@RequestBody UserPrivilegeDTO dto) {
        return ResponseEntity.ok(service.update(dto));
    }

    @PutMapping("/delete")
    public void delete(@RequestBody UUID id) {
        service.delete(id);
    }


    // что удалить Привелегию? в чем разница верхниго и нижнего?
    @PutMapping("/delete/user")
    public void deleteByUserId(@RequestBody Long id) {

        service.deleteByUserId(id);
    }
}
