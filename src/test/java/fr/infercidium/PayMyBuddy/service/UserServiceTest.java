package fr.infercidium.PayMyBuddy.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest(classes = {UserService.class})
class UserServiceTest {

    @Autowired
    private UserService userS;

    @BeforeEach
    private void setUpPerTest() {

    }

    @Test
    void removeConnection() {
    }

    @Test
    void addConnection() {
    }

    @Test
    void editUser() {
    }

    @Test
    void postUser() {
    }

    @Test
    void updateUser() {
    }

    @Test
    void getUser() {
    }

    @Test
    void testGetUser() {
    }

    @Test
    void getKnowUser() {
    }

    @Test
    void testGetKnowUser() {
    }
}