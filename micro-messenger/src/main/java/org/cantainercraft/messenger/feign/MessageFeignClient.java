package org.cantainercraft.messenger.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.cantainercraft.messenger.dto.MessageDTO;

@FeignClient(name = "micro-chats-data",url = "http://localhost:8081/micro-chats-data")
public interface MessageFeignClient {

    @PostMapping("/message/add")
    ResponseEntity<MessageDTO> save(@RequestBody MessageDTO messageDTO);
}
