package org.cantainercraft.micro.users.controller;

import com.google.gson.Gson;
import org.cantainercraft.micro.users.convertor.UserDTOConvertor;
import org.cantainercraft.micro.utilits.exception.MessageError;
import org.cantainercraft.micro.users.service.impl.UserServiceImpl;
import org.cantainercraft.project.entity.users.User;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import java.util.Optional;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(UserController.class)

public class UserControllerTest {


    private final Gson gson = new Gson();
    @MockBean
    private UserServiceImpl userService;

    private final UserDTOConvertor convertor = new UserDTOConvertor(new ModelMapper());

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void givenUserById_whenGetExistUser_thenStatus200() throws Exception{
        long id = 1;

        User userTest = new User(id,"Container","Craft","containercraft@gmail.com");

        Mockito.when(userService.findById(Mockito.any()))
                .thenReturn(Optional.of(userTest));

        mockMvc.perform(post("/user/id")
                        .contentType("application/json")
                        .content(gson.toJson(id)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(gson.toJson(userTest)));
    }

    @Test
    public void givenUserById_whenNotExistUser_thenStatus404() throws Exception{
        long id =1;

        Mockito.when(userService.findById(Mockito.any()))
                .thenReturn(Optional.empty());

        String body="user is not exist";

        mockMvc.perform(post("/user/id")
                .contentType("application/json")
                .content(gson.toJson(id)))
                .andDo(print())
                .andExpect(status().is(404))
                .andExpect(content().json(gson.toJson(MessageError.of(body))));
    }

    @Test
    public void deleteUserById_whenExistUser_thenStatus404() throws Exception{
        long deleteId = 1;
        Mockito.when(userService.findById(Mockito.any())).thenReturn(Optional.empty());

        mockMvc.perform(put("/user/delete/id")
                .contentType("application/json")
                .content(gson.toJson(deleteId)))
                .andDo(print())
                .andExpect(status().is(404));
    }

    @Test
    public void deleteUserById_wheNotExistUser_thenStatus200() throws Exception{
        long deleteId =1;

        User user = new User();
        Mockito.when(userService.findById(Mockito.any())).thenReturn(Optional.of(user));

        mockMvc.perform(put("/user/delete/id")
                .contentType("application/json")
                .content(gson.toJson(deleteId)))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void saveUser_whenNotExistUser_thenStatus201() throws Exception{
        long id=1;

        User userRequest = new User(null,"Dima","1111","qwerty@gmail.com");
        User userResponse = User.builder().id(id).build();

        Mockito.when(userService.findByEmail(Mockito.any()))
                .thenReturn(Optional.empty());

        mockMvc.perform(post("/user/add")
                .contentType("application/json")
                .content(gson.toJson(userRequest)))
                .andDo(print())
                .andExpect(status().is(201));

    }

    @Test
    public void saveUser_whenExistUser_thenStatus409() throws Exception{
        long id=1;

        User userRequest = new User(null,"Dima","1111","qwerty@gmail.com");
        User userResponse = User.builder().id(id).build();

        Mockito.when(userService.findByEmail(Mockito.any()))
                .thenReturn(Optional.of(userResponse));

        mockMvc.perform(post("/user/add")
                .contentType("application/json")
                .content(gson.toJson(userRequest)))
                .andDo(print())
                .andExpect(status().is(409))
                .andExpect(content().json(gson.toJson(MessageError.of("user is exist"))));
    }

    @Test
    public void updateUser_whenExistUser_thenStatus200() throws Exception{
        long id=1;

        User userRequest = new User(id,"Dima","1111","qwerty@gmail.com");
        User userResponse = User.builder().id(id).build();

        Mockito.when(userService.findById(Mockito.any()))
                .thenReturn(Optional.of(userRequest));

        mockMvc.perform(put("/user/update")
                .contentType("application/json")
                .content(gson.toJson(convertor.convertUserToUserDTO(userRequest))))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void updateUser_whenMissedParamId_thenStatus406() throws Exception{
        long id=1;

        User userRequest = new User(null,"Dima","1111","qwerty@gmail.com");
        User userResponse = User.builder().id(id).build();

        Mockito.when(userService.findById(Mockito.any()))
                .thenReturn(Optional.of(userResponse));

        mockMvc.perform(put("/user/update")
                        .contentType("application/json")
                        .content(gson.toJson(userRequest)))
                .andDo(print())
                .andExpect(status().is(406))
                .andExpect(content().json(gson.toJson(MessageError.of("missed param: id"))));
    }

    @Test
    public void updateUser_whenNotExist_thenStatus404() throws Exception{
        long id=1;

        User userRequest = new User(23L,"Dima","1111","qwerty@gmail.com");

        Mockito.when(userService.findById(Mockito.any()))
                .thenReturn(Optional.empty());

        mockMvc.perform(put("/user/update")
                        .contentType("application/json")
                        .content(gson.toJson(userRequest)))
                .andDo(print())
                .andExpect(status().is(404))
                .andExpect(content().json(gson.toJson(MessageError.of("user is not exist"))));
    }
}
