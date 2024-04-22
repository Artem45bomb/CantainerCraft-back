package org.cantainercraft.micro.users.service;

import org.cantainercraft.project.entity.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("org.cantainercraft.micro.users.repository")
class UserServiceTest {

    @BeforeEach
    void setUp() {
        User user1 = new User("Artem","1111","artem@30gmail.com");
        User user2 = new User("Dima","4321","dima@30gmail.com");
        User user3 = new User("Ilya","1234","ilya@30gmail.com");
    }

    @AfterEach
    void tearDown() {
    }
}