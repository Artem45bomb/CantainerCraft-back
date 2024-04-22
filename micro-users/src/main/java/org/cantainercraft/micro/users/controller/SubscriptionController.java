package org.cantainercraft.micro.users.controller;

import org.cantainercraft.micro.users.dto.SubscriptionDTO;
import org.cantainercraft.micro.users.dto.SubscriptionUpdateDTO;
import org.cantainercraft.micro.users.service.SubscriptionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.cantainercraft.project.entity.Subscription;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/subs")
public class SubscriptionController {

    private final SubscriptionService subscriptionService;

    public SubscriptionController(SubscriptionService subscriptionService) { this.subscriptionService = subscriptionService; }

    @GetMapping("{id}")
    public ResponseEntity<Subscription> findById(@PathVariable Long id) {
        return ResponseEntity.ok(subscriptionService.findByID(id));
    }

    @GetMapping("/all")
    public ResponseEntity<List<Subscription>> findAll(){
        return ResponseEntity.ok(subscriptionService.findAll());
    }

    @PostMapping("/add")
    public ResponseEntity<Subscription> save(@RequestBody SubscriptionDTO subscriptionDTO){
        return ResponseEntity.ok(subscriptionService.save(subscriptionDTO));
    }

    @PutMapping
    public ResponseEntity<Boolean> update(@RequestBody SubscriptionUpdateDTO subscriptionUpdateDTO){
        try{
            findById(subscriptionUpdateDTO.getId());
            subscriptionService.update(subscriptionUpdateDTO);
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
            subscriptionService.delete(id);
            return ResponseEntity.ok(true);
        }
        catch (NoSuchElementException exception){
            return new ResponseEntity("element is not exist",HttpStatus.NOT_ACCEPTABLE);
        }
    }
}
