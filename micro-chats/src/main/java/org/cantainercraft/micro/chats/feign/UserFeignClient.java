package org.cantainercraft.micro.chats.feign;


import feign.Response;
import feign.codec.ErrorDecoder;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;
import org.cantainercraft.project.entity.User;
@FeignClient(name ="micro-users")
public interface UserFeignClient {

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