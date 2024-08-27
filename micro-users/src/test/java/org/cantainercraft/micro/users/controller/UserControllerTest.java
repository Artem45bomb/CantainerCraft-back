package org.cantainercraft.micro.users.controller;

import com.google.gson.Gson;
import org.bouncycastle.util.Arrays;
import org.cantainercraft.micro.users.advice.ExceptionApiHandler;
import org.cantainercraft.micro.users.convertor.UserDTOConvertor;
import org.cantainercraft.micro.users.dto.UserLoadDTO;
import org.cantainercraft.micro.users.repository.UserRepository;
import org.cantainercraft.micro.users.service.UserService;
import org.cantainercraft.micro.users.service.impl.JwtService;
import org.cantainercraft.micro.users.service.impl.UserServiceDetailsImpl;
import org.cantainercraft.micro.users.service.impl.UserServiceImpl;
import org.cantainercraft.micro.utilits.service.ConvertorDTO;
import org.mockito.MockitoAnnotations;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;
import org.cantainercraft.micro.utilits.exception.MessageError;
import org.cantainercraft.project.entity.users.User;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.Optional;
import org.cantainercraft.micro.users.configuration.SecurityConfig;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(value = UserController.class)
@Import({SecurityConfig.class, UserServiceImpl.class, ExceptionApiHandler.class})
public class UserControllerTest {
    private final Gson gson = new Gson();

    @MockBean
    private JwtService jwtService;
    @MockBean
    private UserServiceDetailsImpl userDetailsService;
    @MockBean
    private ConvertorDTO<UserLoadDTO,User> convertorLoad;
    @MockBean
    private UserDTOConvertor convertor;

    @MockBean
    private UserRepository repository;
    @Autowired
    private MockMvc mockMvc;


    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    //findById
    @Test
    @WithMockUser
    public void findById_whenUserNotExist_thenStatus404() throws Exception{
        Mockito.when(repository.findById(Mockito.any())).thenReturn(Optional.empty());

        mockMvc.perform(post("/api/user/id")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(gson.toJson(1L)))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(content().json(gson.toJson(MessageError.of("user is not exist"))));

    }

    @Test
    @WithMockUser
    public void findById_whenUserExist_thenStatus200() throws Exception{
        User user = new User(1L,"Dima12","11111","qwerty@gmail.com");

        Mockito.when(repository.findById(Mockito.any())).thenReturn(Optional.of(user));

        mockMvc.perform(post("/api/user/id")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(gson.toJson(1L)))
                .andDo(print())
                .andExpect(status().isOk());

    }

    //findByEmail

    @Test
    @WithMockUser
    public void findByEmail_whenUserNotExist_thenStatus404() throws Exception{
        String email = "test@gmail.com";

        mockMvc.perform(post("/api/user/email")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(email))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(content().json(gson.toJson(MessageError.of("user is not exist"))));

    }

    @Test
    @WithMockUser
    public void findByEmail_whenUserExist_thenStatus200() throws Exception{
        String email = "test@gmail.com";
        User user = new User(1L,"Dima12","11111","qwerty@gmail.com");

        Mockito.when(repository.findByEmail(Mockito.any())).thenReturn(Optional.of(user));

        mockMvc.perform(post("/api/user/email")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(email))
                .andDo(print())
                .andExpect(status().isOk());

    }

    @Test
    @WithMockUser
    public void findByEmail_whenEmailIsNull_thenStatus409() throws Exception{
        mockMvc.perform(post("/api/user/email")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("    "))
                .andDo(print())
                .andExpect(content().json(gson.toJson(MessageError.of("email is null"))))
                .andExpect(status().isNotAcceptable());

    }

    //findByName

    @Test
    @WithMockUser
    public void findByName_whenUserNotExist_thenStatus404() throws Exception{
        String name = "Test12";

        mockMvc.perform(post("/api/user/name")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(name))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(content().json(gson.toJson(MessageError.of("user is not exist"))));

    }

    @Test
    @WithMockUser
    public void findByName_whenUserExist_thenStatus200() throws Exception{
        String name = "Test21";
        User user = new User(1L,"Dima12","11111","qwerty@gmail.com");

        Mockito.when(repository.findByUsername(Mockito.any())).thenReturn(Optional.of(user));

        mockMvc.perform(post("/api/user/name")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(name))
                .andDo(print())
                .andExpect(status().isOk());

    }

    @Test
    @WithMockUser
    public void findByName_whenNameIsNull_thenStatus409() throws Exception{
        mockMvc.perform(post("/api/user/name")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("    "))
                .andDo(print())
                .andExpect(content().json(gson.toJson(MessageError.of("name is null"))))
                .andExpect(status().isNotAcceptable());

    }

    //findAll

    @Test
    @WithMockUser
    public void findAll_whenUsersExist_thenStatus200() throws Exception{
        User user = User.builder().build();
        Mockito.when(repository.findAll()).thenReturn(Collections.singletonList(user));

        mockMvc.perform(post("/api/user/all")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(jsonPath("$",hasSize(1)))
                .andExpect(status().isOk());

    }

    @Test
    @WithMockUser
    public void findAll_whenUsersNotExist_thenEmptyList() throws Exception{
        Mockito.when(repository.findAll()).thenReturn(Collections.emptyList());

        mockMvc.perform(post("/api/user/all")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(jsonPath("$",hasSize(0)))
                .andExpect(status().isOk());

    }

    //save
    @Test
    @WithMockUser
    public void saveUser_whenNotExistUser_thenStatus201() throws Exception{
        User userRequest = new User(null,"Dima12","11111","qwerty@gmail.com");

        Mockito.when(repository.existsByUsername(Mockito.any()))
                .thenReturn(false);
        Mockito.when(repository.existsByEmail(Mockito.any()))
                .thenReturn(false);
        Mockito.when(repository.save(Mockito.any())).thenReturn(userRequest);

        mockMvc.perform(post("/api/user/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(gson.toJson(userRequest)))
                .andDo(print())
                .andExpect(status().is(201));

    }

    @Test
    @WithMockUser
    public void saveUser_whenExistUserByUsername_thenStatus409() throws Exception{
        User userRequest = new User(null,"Dima12","11111","qwerty@gmail.com");

        Mockito.when(repository.existsByUsername(Mockito.any()))
                .thenReturn(true);

        mockMvc.perform(post("/api/user/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(gson.toJson(userRequest)))
                .andDo(print())
                .andExpect(status().is(409))
                .andExpect(content().json(gson.toJson(MessageError.of("username is exist"))));
    }

    @Test
    @WithMockUser
    public void saveUser_whenNotValidId_thenStatus406() throws Exception{
        User userRequest = new User(1L,"Dima12","11111","qwerty@gmail.com");

        Mockito.when(repository.existsByUsername(Mockito.any()))
                .thenReturn(true);

        mockMvc.perform(post("/api/user/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(gson.toJson(userRequest)))
                .andDo(print())
                .andExpect(status().is(406))
                .andExpect(content().json(gson.toJson(MessageError.of("missed param:id"))));
    }


    //update
    @Test
    @WithMockUser
    public void updateUser_whenExistUser_thenStatus200() throws Exception{
        User userRequest = new User(1L,"Test12","11111","qwerty@gmail.com");

        Mockito.when(repository.existsById(Mockito.any()))
                .thenReturn(true);
        Mockito.when(repository.save(Mockito.any()))
                        .thenReturn(userRequest);

        mockMvc.perform(put("/api/user/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content(gson.toJson(userRequest)))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    public void updateUser_whenMissedParamId_thenStatus406() throws Exception{
        User userRequest = new User(null,"Test12","11111","qwerty@gmail.com");

        mockMvc.perform(put("/api/user/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(gson.toJson(userRequest)))
                .andDo(print())
                .andExpect(status().is(406))
                .andExpect(content().json(gson.toJson(MessageError.of("missed param: id"))));
    }

    @Test
    @WithMockUser
    public void updateUser_whenNotExist_thenStatus404() throws Exception{
        User userRequest = new User(1L,"Test12","11111","qwerty@gmail.com");

        Mockito.when(repository.existsById(Mockito.any()))
                .thenReturn(false);

        mockMvc.perform(put("/api/user/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(gson.toJson(userRequest)))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(content().json(gson.toJson(MessageError.of("user is not exist"))));
    }

    //deleteById
    @Test
    @WithMockUser
    public void deleteUserById_whenExistUser_thenStatus404() throws Exception{
        long deleteId = 1;
        Mockito.when(repository.existsById(Mockito.any())).thenReturn(false);

        mockMvc.perform(put("/api/user/delete/id")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(gson.toJson(deleteId)))
                .andDo(print())
                .andExpect(content().json(gson.toJson(MessageError.of("user is not exist"))))
                .andExpect(status().isNotFound());
    }

    @Test
    @WithMockUser
    public void deleteUserById_whenNotExistUser_thenStatus200() throws Exception{
        long deleteId =1;
        Mockito.when(repository.existsById(Mockito.any())).thenReturn(true);
        Mockito.doNothing().when(repository).deleteById(Mockito.any());

        mockMvc.perform(put("/api/user/delete/id")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(gson.toJson(deleteId)))
                .andDo(print())
                .andExpect(status().isOk());
    }

    //deleteByEmail
    @Test
    @WithMockUser
    public void deleteUserByEmail_whenExistUser_thenStatus200() throws Exception{
        String email = "test@gmail.com";
        Mockito.when(repository.existsByEmail(Mockito.any())).thenReturn(true);
        Mockito.doNothing().when(repository).deleteById(Mockito.any());

        mockMvc.perform(put("/api/user/delete/email")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(email))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    public void deleteUserByEmail_whenNotExistUser_thenStatus404() throws Exception{
        String email = "test@gmail.com";
        Mockito.when(repository.existsByEmail(Mockito.any())).thenReturn(false);
        Mockito.doNothing().when(repository).deleteById(Mockito.any());

        mockMvc.perform(put("/api/user/delete/email")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(email))
                .andDo(print())
                .andExpect(content().json(gson.toJson( MessageError.of("user is not exist"))))
                .andExpect(status().isNotFound());
    }

    //existById
    @Test
    @WithMockUser
    public void existsById_whenExistUser_thenStatus200() throws Exception{
        long id = 1;
        Mockito.when(repository.existsById(Mockito.any())).thenReturn(true);
        Mockito.doNothing().when(repository).deleteById(Mockito.any());

        mockMvc.perform(post("/api/user/exist/id")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(gson.toJson(id)))
                .andDo(print())
                .andExpect(jsonPath("$").value(true))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    public void existsById_whenNotExistUser_thenStatus200() throws Exception{
        long id = 1;
        Mockito.when(repository.existsById(Mockito.any())).thenReturn(false);
        Mockito.doNothing().when(repository).deleteById(Mockito.any());

        mockMvc.perform(post("/api/user/exist/id")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(gson.toJson(id)))
                .andDo(print())
                .andExpect(jsonPath("$").value(false))
                .andExpect(status().isOk());
    }

    //isPermission
    @Test
    @WithMockUser
    public void isPermission_whenUserAuthenticated_thenStatus200() throws Exception{
        mockMvc.perform(get("/api/user/permission")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void isPermission_whenUserNoyAuthenticated_thenStatus403() throws Exception{
        mockMvc.perform(get("/api/user/permission")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is(403));
    }

}
