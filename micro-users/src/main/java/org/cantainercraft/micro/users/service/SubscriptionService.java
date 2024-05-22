package org.cantainercraft.micro.users.service;

import org.cantainercraft.micro.users.dto.SubscriptionDTO;
import org.cantainercraft.micro.users.dto.SubscriptionUpdateDTO;
import org.cantainercraft.project.entity.users.Subscription;

import java.util.List;

public interface SubscriptionService {
     List<Subscription> findAll();

     Subscription findByID(Long id);

     Subscription save(SubscriptionDTO subscriptionDTO);
     boolean update(SubscriptionUpdateDTO subscriptionUpdateDTO);

     void deleteById(Long id);
}
