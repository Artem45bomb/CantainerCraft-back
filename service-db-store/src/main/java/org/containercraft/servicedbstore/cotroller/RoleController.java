package org.containercraft.servicedbstore.cotroller;

import lombok.RequiredArgsConstructor;
import org.containercraft.servicedbstore.entity.Role;
import org.containercraft.servicedbstore.service.RoleService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/roles")
@RequiredArgsConstructor
public class RoleController {
    private final RoleService service;
    private final RabbitTemplate template;

     @GetMapping
     public void test(){
         template.convertAndSend("processMessage-out-1","hi");
     }

    @GetMapping("/all")
    public Flux<Role> findAll(){
        return service.findAll();
    }
}
