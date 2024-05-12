package org.cantainercraft.micro.chats.feign;


import feign.Headers;
import feign.Response;
import feign.codec.ErrorDecoder;
import org.cantainercraft.micro.chats.dto.ServiceUserDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;
import org.cantainercraft.project.entity.User;

import java.util.Optional;
;
@FeignClient(name ="micro-users")
@Headers("micro-service-key: ${service.key}")
public interface UserFeignClient {


    @PostMapping("/user/loadedUser")
    ResponseEntity<Optional<User>> loadedUser(@RequestBody String username);

    @PostMapping("/user/id")
    ResponseEntity<User> userExist(@RequestBody Long id);
}


@Component
class  UserErrorDecoder implements ErrorDecoder{

    @Override
    public Exception decode(String methodKey, Response response) {
        switch(response.status()){
            case 406: {
                return new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE,"error");
            }
            case 204 : {
                return new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE,"error decode");
            }
        }
        return null;
    }

}