package org.cantainercraft.micro.users.service.impl;

import jakarta.transaction.Transactional;
import org.cantainercraft.micro.users.convertor.SubscriptionDTOConvertor;
import org.cantainercraft.micro.users.service.SubscriptionService;
import org.springframework.stereotype.Component;
import org.cantainercraft.micro.users.dto.SubscriptionDTO;
import org.cantainercraft.micro.users.dto.SubscriptionUpdateDTO;
import org.cantainercraft.project.entity.Subscription;
import org.cantainercraft.micro.users.repository.SubscriptionRepository;
import java.util.List;

@Component
@Transactional
public class SubscriptionServiceImpl implements SubscriptionService {
    private final SubscriptionRepository subscriptionRepository;

    private final SubscriptionDTOConvertor subscriptionDTOConvertor;

    public SubscriptionServiceImpl(SubscriptionRepository subscriptionRepository, SubscriptionDTOConvertor subscriptionDTOConvertor) {
        this.subscriptionRepository = subscriptionRepository;
        this.subscriptionDTOConvertor = subscriptionDTOConvertor;
    }
    public List<Subscription> findAll() { return subscriptionRepository.findAll(); }

    public Subscription findByID(Long id) { return subscriptionRepository.findById(id).get(); }

    public Subscription save(SubscriptionDTO subscriptionDTO){
        Subscription subscription = subscriptionDTOConvertor.convertSubscriptionDTOToSubscription(subscriptionDTO);
        return subscriptionRepository.save(subscription);
    }
    public boolean update(SubscriptionUpdateDTO subscriptionUpdateDTO){
        Subscription subscription = subscriptionDTOConvertor.convertSubscriptionDTOToSubscription(subscriptionUpdateDTO);
        subscription.setId(subscriptionUpdateDTO.getId());
        subscriptionRepository.save(subscription);
        return true;
    }

    public void deleteById(Long id){
        subscriptionRepository.deleteById(id);
    }


}
