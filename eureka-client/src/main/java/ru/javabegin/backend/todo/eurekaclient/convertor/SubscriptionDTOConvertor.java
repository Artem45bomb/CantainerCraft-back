package ru.javabegin.backend.todo.eurekaclient.convertor;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.javabegin.backend.todo.eurekaclient.dto.SubscriptionDTO;
import ru.javabegin.backend.todo.eurekaclient.entity.Subscription;

@Component
public class SubscriptionDTOConvertor {

    @Autowired
    private ModelMapper modelMapper;

    public SubscriptionDTO convertSubscriptionToSubscriptionDTO(Subscription subscription){
        return modelMapper.map(subscription, SubscriptionDTO.class);
    }
    public Subscription convertSubscriptionDTOToSubscription(SubscriptionDTO subscriptionDTO){
        return modelMapper.map(subscriptionDTO, Subscription.class);
    }

}
