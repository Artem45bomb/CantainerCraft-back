//package org.cantainercraft.micro.chats.reactive;
//
//import org.cantainercraft.project.entity.chats.Emotion;
//import org.springframework.data.repository.reactive.ReactiveCrudRepository;
//import reactor.core.publisher.Flux;
//import reactor.core.publisher.Mono;
//
//import java.util.UUID;
//
//public interface EmotionRepository extends ReactiveCrudRepository<Emotion, UUID> {
//
//    Mono<Emotion> findByUnicode(String unicode);
//
//    Mono<Void> deleteByUnicode(String unicode);
//}
