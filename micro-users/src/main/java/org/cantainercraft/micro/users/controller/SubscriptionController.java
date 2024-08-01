package org.cantainercraft.micro.users.controller;

import lombok.RequiredArgsConstructor;
import org.cantainercraft.micro.users.dto.SubscriptionDTO;
import org.cantainercraft.micro.users.dto.SubscriptionUpdateDTO;
import org.cantainercraft.micro.users.service.SubscriptionService;
import org.cantainercraft.micro.utilits.exception.NotResourceException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.cantainercraft.project.entity.users.Subscription;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/subs")
@RequiredArgsConstructor
public class SubscriptionController {

    private final SubscriptionService service;

    @GetMapping("/{id}")
    public ResponseEntity<Subscription> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping("/all")
    public ResponseEntity<List<Subscription>> findAll(){
        return ResponseEntity.ok(service.findAll());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/add")
    public ResponseEntity<Subscription> save(@RequestBody SubscriptionDTO subscriptionDTO){
        return ResponseEntity.ok(service.save(subscriptionDTO));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update")
    public ResponseEntity<Subscription> update(@RequestBody SubscriptionUpdateDTO subscriptionUpdateDTO){
            return ResponseEntity.ok(service.update(subscriptionUpdateDTO));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable Long id ){
            service.deleteById(id);
    }
}
