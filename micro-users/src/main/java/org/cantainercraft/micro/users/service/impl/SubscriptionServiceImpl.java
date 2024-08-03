package org.cantainercraft.micro.users.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.cantainercraft.micro.users.convertor.SubscriptionDTOConvertor;
import org.cantainercraft.micro.users.service.SubscriptionService;
import org.cantainercraft.micro.utilits.exception.ExistResourceException;
import org.cantainercraft.micro.utilits.exception.NotResourceException;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import org.cantainercraft.micro.users.dto.SubscriptionDTO;
import org.cantainercraft.micro.users.dto.SubscriptionUpdateDTO;
import org.cantainercraft.project.entity.users.Subscription;
import org.cantainercraft.micro.users.repository.SubscriptionRepository;
import java.util.List;
import java.util.Optional;

@Component
@Transactional
@RequiredArgsConstructor
public class SubscriptionServiceImpl implements SubscriptionService {
    private final SubscriptionRepository repository;
    private final SubscriptionDTOConvertor convertor;

    @Override
    public List<Subscription> findAll() { return repository.findAll(); }
    
    @Override
    @Cacheable(value = "subscriptions",key = "id")
    public Subscription findById(Long id) {
        Optional<Subscription> subscription = repository.findById(id);

        if(subscription.isEmpty()){
            throw new NotResourceException("subscription is not exist");
        }

        return subscription.get();
    }

    @Override
    public Subscription save(SubscriptionDTO dto){
        Subscription subscription = convertor.convertDTOToEntity(dto);
        
        if(!repository.existsByName(dto.getName())){
            throw new ExistResourceException("subscription is exist");
        }
        
        return repository.save(subscription);
    }
    
    @Override
    @CachePut(value = "subscriptions",key = "dto.id")
    public Subscription update(SubscriptionUpdateDTO dto){
        Subscription subscription = convertor.convertDTOToEntity(dto);
        subscription.setId(dto.getId());
        
        if(!repository.existsById(subscription.getId())){
            throw new NotResourceException("subscription is not exist");
        }
        
        return repository.save(subscription);
    }

    @Override
    @CacheEvict(value = "subscriptions",key = "id")
    public void deleteById(Long id){
        if(!repository.existsById(id)){
            throw new NotResourceException("subscription is exist");
        }

        repository.deleteById(id);
    }


}
