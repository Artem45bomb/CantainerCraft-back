package org.cantainercraft.micro.users.controller;

import lombok.RequiredArgsConstructor;
import org.cantainercraft.micro.users.dto.SubscriptionDTO;
import org.cantainercraft.micro.users.dto.SubscriptionUpdateDTO;
import org.cantainercraft.micro.users.service.SubscriptionService;
import org.cantainercraft.micro.utilits.exception.NotResourceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.cantainercraft.project.entity.Subscription;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/subs")
@RequiredArgsConstructor
public class SubscriptionController {

    private final SubscriptionService subscriptionService;


    @GetMapping("/{id}")
    public ResponseEntity<Subscription> findById(@PathVariable Long id) {
        return ResponseEntity.ok(subscriptionService.findByID(id));
    }

    @GetMapping("/all")
    public ResponseEntity<List<Subscription>> findAll(){
        return ResponseEntity.ok(subscriptionService.findAll());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/add")
    public ResponseEntity<Subscription> save(@RequestBody SubscriptionDTO subscriptionDTO){
        return ResponseEntity.ok(subscriptionService.save(subscriptionDTO));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update")
    public ResponseEntity<Boolean> update(@RequestBody SubscriptionUpdateDTO subscriptionUpdateDTO){
        try{
            findById(subscriptionUpdateDTO.getId());
            subscriptionService.update(subscriptionUpdateDTO);
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
            subscriptionService.deleteById(id);
            return ResponseEntity.ok(true);
        }
        catch (NoSuchElementException exception){
            throw  new NotResourceException("element is not exist");
        }
    }
}
