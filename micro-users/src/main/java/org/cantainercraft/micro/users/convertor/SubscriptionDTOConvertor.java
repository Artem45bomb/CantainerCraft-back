package org.cantainercraft.micro.users.convertor;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.cantainercraft.micro.users.dto.SubscriptionDTO;
import org.cantainercraft.project.entity.Subscription;

@Component
@RequiredArgsConstructor
public class SubscriptionDTOConvertor {


    private final ModelMapper modelMapper;

    public SubscriptionDTO convertSubscriptionToSubscriptionDTO(Subscription subscription){
        return modelMapper.map(subscription, SubscriptionDTO.class);
    }
    public Subscription convertSubscriptionDTOToSubscription(SubscriptionDTO subscriptionDTO){
        return modelMapper.map(subscriptionDTO, Subscription.class);
    }

}
