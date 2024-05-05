package org.cantainercraft.micro.users.controller;

import lombok.RequiredArgsConstructor;
import org.cantainercraft.micro.users.dto.RoleDTO;
import org.cantainercraft.micro.users.dto.RoleUpdateDTO;
import org.cantainercraft.micro.users.service.RoleService;
import org.cantainercraft.micro.utilits.exception.NotResourceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import org.cantainercraft.project.entity.Role;


@RestController
@RequestMapping("/roles")
@RequiredArgsConstructor
public class RoleController {

    private final RoleService roleService;


    @GetMapping("/{id}")
    public ResponseEntity<Role> findById(@PathVariable Long id){
        return ResponseEntity.ok(roleService.findById(id));
    }

    @GetMapping("/all")
    public ResponseEntity<List<Role>> findAll(){
        return ResponseEntity.ok(roleService.findAll());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/add")
    public ResponseEntity<Role> save(@RequestBody RoleDTO RoleDTO){
        return ResponseEntity.ok(roleService.save(RoleDTO));
    }

    @PutMapping
    public ResponseEntity<Boolean> update(@RequestBody RoleUpdateDTO RoleUpdateDTO){
        try{
            findById(RoleUpdateDTO.getId());
            roleService.update(RoleUpdateDTO);
            return ResponseEntity.ok(true);
        }
        catch (NoSuchElementException exception){
            throw  new NotResourceException("element is not exist");
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> deleteById(@PathVariable Long id ){
        try{
            findById(id);
            roleService.deleteById(id);
            return ResponseEntity.ok(true);
        }
        catch (NoSuchElementException exception){
            throw  new NotResourceException("element is not exist");
        }
    }

}
