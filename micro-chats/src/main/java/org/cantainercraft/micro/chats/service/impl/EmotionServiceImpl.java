//package org.cantainercraft.micro.chats.service.impl;
//
//import lombok.RequiredArgsConstructor;
//import org.cantainercraft.micro.chats.convertor.EmotionDTOConvertor;
//import org.cantainercraft.micro.chats.dto.EmotionDTO;
//import org.cantainercraft.micro.chats.reactive.EmotionRepository;
//import org.cantainercraft.micro.chats.service.EmotionService;
//import org.cantainercraft.micro.utilits.exception.ExistResourceException;
//import org.cantainercraft.micro.utilits.exception.NotResourceException;
//import org.cantainercraft.project.entity.chats.Emotion;
//import org.springframework.stereotype.Service;
//import reactor.core.publisher.Flux;
//import reactor.core.publisher.Mono;
//
//import java.util.Optional;
//import java.util.UUID;
//
//@Service
//@RequiredArgsConstructor
//public class EmotionServiceImpl implements EmotionService {
//    private final EmotionRepository repository;
//    private final EmotionDTOConvertor convertor;
//
//    @Override
//    public Mono<Emotion> findByUnicode(String unicode) {
//        return repository
//                .findByUnicode(unicode);
//
//    }
//
//    @Override
//    public Mono<Void> deleteByUnicode(String unicode) {
//        repository
//                .findByUnicode(unicode)
//                .blockOptional()
//                .orElseThrow(() -> {throw new NotResourceException("emotion is not exist");});
//        return repository.deleteByUnicode(unicode);
//    }
//
//    @Override
//    public Mono<Void> deleteById(UUID uuid) {
//        repository
//                .findById(uuid)
//                .blockOptional()
//                .orElseThrow(() ->{throw new NotResourceException("");});
//        return repository.deleteById(uuid);
//    }
//
//    @Override
//    public Mono<Emotion> save(EmotionDTO emotionDTO) {
//        Optional<Emotion> emotion = repository
//                .findByUnicode(emotionDTO.getUnicode())
//                .blockOptional();
//
//        if(emotion.isEmpty()){
//            return repository
//                    .save(convertor.convertEmotionDTOToEmotion(emotionDTO));
//        }
//        else
//            throw new ExistResourceException("emotion is exist");
//    }
//
//    @Override
//    public Mono<Emotion> update(EmotionDTO emotionDTO) {
//        repository
//                .findById(emotionDTO.getUuid())
//                .blockOptional()
//                .orElseThrow(() -> {throw new NotResourceException("emotion is not exist");});
//        return repository
//                .save(convertor.convertEmotionDTOToEmotion(emotionDTO));
//    }
//
//    @Override
//    public Flux<Emotion> findAll() {
//        return repository.findAll();
//    }
//
//    @Override
//    public Mono<Emotion> findById(UUID uuid){
//        return repository.findById(uuid);
//    }
//
//}
