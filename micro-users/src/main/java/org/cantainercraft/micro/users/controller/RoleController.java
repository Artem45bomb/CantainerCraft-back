package org.cantainercraft.micro.users.controller;

import org.cantainercraft.micro.users.dto.RoleDTO;
import org.cantainercraft.micro.users.dto.RoleUpdateDTO;
import org.cantainercraft.micro.users.service.RoleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import org.cantainercraft.project.entity.Role;


@RestController
@RequestMapping("/roles")
public class RoleController {

    private final org.cantainercraft.micro.users.service.RoleService RoleService;

    public RoleController(RoleService RoleService){
        this.RoleService = RoleService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Role> findById(@PathVariable Long id){
        return ResponseEntity.ok(RoleService.findById(id));
    }

    @GetMapping("/all")
    public ResponseEntity<List<Role>> findAll(){
        return ResponseEntity.ok(RoleService.findAll());
    }

    @PostMapping("/add")
    public ResponseEntity<Role> save(@RequestBody RoleDTO RoleDTO){
        return ResponseEntity.ok(RoleService.save(RoleDTO));
    }

    @PutMapping
    public ResponseEntity<Boolean> update(@RequestBody RoleUpdateDTO RoleUpdateDTO){
        try{
            findById(RoleUpdateDTO.getId());
            RoleService.update(RoleUpdateDTO);
            return ResponseEntity.ok(true);
        }
        catch (NoSuchElementException exception){
            return new ResponseEntity("element is not exist",HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Long id ){
        try{
            findById(id);
            RoleService.delete(id);
            return ResponseEntity.ok(true);
        }
        catch (NoSuchElementException exception){
            return new ResponseEntity("element is not exist",HttpStatus.NOT_ACCEPTABLE);
        }
    }
}
