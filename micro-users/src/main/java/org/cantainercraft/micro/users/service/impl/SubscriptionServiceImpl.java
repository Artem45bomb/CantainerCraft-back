package org.cantainercraft.micro.users.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.cantainercraft.micro.users.convertor.SubscriptionDTOConvertor;
import org.cantainercraft.micro.users.service.SubscriptionService;
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
    private final SubscriptionRepository subscriptionRepository;
    private final SubscriptionDTOConvertor subscriptionDTOConvertor;

    @Override
    public List<Subscription> findAll() { return subscriptionRepository.findAll(); }
    
    @Override
    @Cacheable(value = "subscriptions",key = "#id")
    public Subscription findById(Long id) {
        Optional<Subscription> subscription = subscriptionRepository.findById(id);

        if(subscription.isEmpty()) throw new NotResourceException("subscription is not exist");

        return subscription.get();
    }

    @Override
    //@Cacheable(value = "subscriptions",key = "#result.id")
    public Subscription save(SubscriptionDTO subscriptionDTO){
        Subscription subscription = subscriptionDTOConvertor.convertDTOToEntity(subscriptionDTO);
        return subscriptionRepository.save(subscription);
    }
    
    @Override
    @CachePut(value = "subscriptions",key = "dto.id")
    public boolean update(SubscriptionUpdateDTO dto){
        Subscription subscription = subscriptionDTOConvertor.convertDTOToEntity(dto);
        subscription.setId(dto.getId());
        subscriptionRepository.save(subscription);
        return true;
    }

    @Override
    @CacheEvict(value = "subscriptions",key = "#id")
    public void deleteById(Long id){
        subscriptionRepository.deleteById(id);
    }


}
