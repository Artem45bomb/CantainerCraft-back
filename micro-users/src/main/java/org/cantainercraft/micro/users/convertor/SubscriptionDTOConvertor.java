package org.cantainercraft.micro.users.convertor;

import lombok.RequiredArgsConstructor;
import org.cantainercraft.micro.utilits.service.ConvertorDTO;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.cantainercraft.micro.users.dto.SubscriptionDTO;
import org.cantainercraft.project.entity.users.Subscription;

@Component
@RequiredArgsConstructor
public class SubscriptionDTOConvertor implements ConvertorDTO<SubscriptionDTO,Subscription> {


    private final ModelMapper modelMapper;

    @Override
    public SubscriptionDTO convertEntityToDTO(Subscription subscription) {
        return modelMapper.map(subscription, SubscriptionDTO.class);
    }

    @Override
    public Subscription convertDTOToEntity(SubscriptionDTO subscriptionDTO) {
        return modelMapper.map(subscriptionDTO, Subscription.class);
    }
}
