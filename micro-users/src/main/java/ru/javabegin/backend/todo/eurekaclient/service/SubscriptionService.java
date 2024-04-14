package ru.javabegin.backend.todo.eurekaclient.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;
import ru.javabegin.backend.todo.eurekaclient.convertor.SubscriptionDTOConvertor;
import ru.javabegin.backend.todo.eurekaclient.dto.SubscriptionDTO;
import ru.javabegin.backend.todo.eurekaclient.dto.SubscriptionUpdateDTO;
import ru.weather.project.entity.Subscription;
import ru.javabegin.backend.todo.eurekaclient.repository.SubscriptionRepository;
import java.util.List;

@Component
@Transactional
public class SubscriptionService {
    private final SubscriptionRepository subscriptionRepository;

    private final SubscriptionDTOConvertor subscriptionDTOConvertor;

    public  SubscriptionService(SubscriptionRepository subscriptionRepository, SubscriptionDTOConvertor subscriptionDTOConvertor) {
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

    public void delete(Long id){
        subscriptionRepository.deleteById(id);
    }


}
