package org.cantainercraft.micro.users.controller;

import com.google.gson.Gson;
import org.aspectj.lang.annotation.Before;
import org.cantainercraft.micro.users.convertor.UserDTOConvertor;
import org.cantainercraft.micro.users.dto.UserDTO;
import org.cantainercraft.micro.users.service.impl.UserServiceImpl;
import org.cantainercraft.project.entity.User;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



@WebMvcTest(UserController.class)
public class UserControllerTest {

    private long id = 6;
    private final Gson gson = new Gson();
    @MockBean
    private UserServiceImpl userService;

    private final UserDTOConvertor convertor = new UserDTOConvertor(new ModelMapper());

    @Autowired
    private MockMvc mockMvc;


    @Test
    @Order(2)
    public void findUserById() throws Exception{
        User userTest = new User();
        userTest.setId(id);

        Mockito.when(userService.findById(id)).thenReturn(userTest);
        mockMvc
                .perform(post("/user/id")
                        .contentType("application/json")
                        .content(gson.toJson(id))
                )
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @Order(1)
    public void saveUser() throws Exception{
        User userTest = new User("Container","Craft","containercraft@gmail.com");
        UserDTO dto = convertor.convertUserToUserDTO(userTest);

        Mockito.when(userService.save(dto))
                .thenReturn(userTest);

        mockMvc.perform(post("/user/add")
                .contentType("application/json")
                .content(gson.toJson(dto)))
                .andDo(print())
                .andExpect(status().isOk());
    }


}
