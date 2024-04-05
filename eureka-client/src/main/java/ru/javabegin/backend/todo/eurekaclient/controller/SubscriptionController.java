package ru.javabegin.backend.todo.eurekaclient.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.javabegin.backend.todo.eurekaclient.dto.RoleDTO;
import ru.javabegin.backend.todo.eurekaclient.dto.RoleUpdateDTO;
import ru.javabegin.backend.todo.eurekaclient.dto.SubscriptionDTO;
import ru.javabegin.backend.todo.eurekaclient.dto.SubscriptionUpdateDTO;
import ru.javabegin.backend.todo.eurekaclient.entity.Role;
import ru.javabegin.backend.todo.eurekaclient.entity.Subscription;
import ru.javabegin.backend.todo.eurekaclient.service.SubscriptionService;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/subs")
public class SubscriptionController {

    private final SubscriptionService subscriptionService;

    public SubscriptionController(SubscriptionService subscriptionService) { this.subscriptionService = subscriptionService; }

    @GetMapping("{/id}")
    public ResponseEntity<Subscription> findById(@PathVariable Long id) {
        return ResponseEntity.ok(subscriptionService.findByID(id));
    }

    @GetMapping("/all")
    public ResponseEntity<List<Subscription>> findAll(){
        return ResponseEntity.ok(subscriptionService.findAll());
    }

    @PostMapping("/create")
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

    @DeleteMapping("/{id}")
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
