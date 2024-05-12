//package org.cantainercraft.micro.chats.controller;
//
//import lombok.RequiredArgsConstructor;
//import org.cantainercraft.micro.chats.dto.EmotionDTO;
//import org.cantainercraft.micro.chats.service.EmotionService;
//import org.cantainercraft.project.entity.chats.Emotion;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import reactor.core.publisher.Flux;
//import reactor.core.publisher.Mono;
//
//import java.util.UUID;
//
//@RestController
//@RequestMapping("/emotions")
//@RequiredArgsConstructor
//public class EmotionController {
//    public final EmotionService service;
//
//    @PutMapping("/delete/id")
//    public Mono<Void> deleteById(@RequestBody UUID uuid){
//        return service.deleteById(uuid);
//    }
//
//    @PutMapping("/delete")
//    public Mono<Void> deleteByUnicode(@RequestBody String unicode){
//        return service.deleteByUnicode(unicode);
//    }
//
//    @PostMapping("/add")
//    public Mono<ResponseEntity<Emotion>> save(@RequestBody EmotionDTO emotion){
//        return service
//                .save(emotion)
//                .map(ResponseEntity::ok);
//    }
//
//    @PutMapping("/update")
//    public Mono<ResponseEntity<Emotion>> update(@RequestBody EmotionDTO emotion){
//        return service
//                .update(emotion)
//                .map(ResponseEntity::ok);
//    }
//
//    @GetMapping("/all")
//    public Flux<ResponseEntity<Emotion>> findAll(){
//        return service
//                .findAll()
//                .flatMap(e -> Mono.just(ResponseEntity.ok(e)));
//    }
//
//    @GetMapping("/id")
//    public Mono<ResponseEntity<Emotion>> findById(UUID uuid){
//        return service
//                .findById(uuid)
//                .map(ResponseEntity::ok);
//    }
//
//    @GetMapping
//    public Mono<ResponseEntity<Emotion>> findByUnicode(String unicode){
//        return service
//                .findByUnicode(unicode)
//                .map(ResponseEntity::ok);
//    }
//}
