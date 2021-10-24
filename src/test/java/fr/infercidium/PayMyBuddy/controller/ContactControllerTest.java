package fr.infercidium.PayMyBuddy.controller;

import fr.infercidium.PayMyBuddy.configuration.UserComponent;
import fr.infercidium.PayMyBuddy.model.BankAccount;
import fr.infercidium.PayMyBuddy.model.User;
import fr.infercidium.PayMyBuddy.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.ui.Model;

import java.time.LocalDate;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = {ContactController.class})
class ContactControllerTest {

    @MockBean
    private UserComponent userComponent;

    @MockBean
    private UserService userS;

    @MockBean
    private Model model;

    @Autowired
    private ContactController contactController;

    BankAccount bankAccount = new BankAccount("test1", "Jean Jean", LocalDate.now().plusMonths(2L), "123456789", "123", "FR123", "C123", "89");
    User user = new User("Jean@email.com", "123", "JeanJ", bankAccount);

    @BeforeEach
    private void setUpPerTest() {
        when(userComponent.saveUser()).thenReturn(user);
        when(userS.getKnowUser(isA(String.class), isA(Pageable.class))).thenReturn(new PageImpl<>(Collections.singletonList(user)));
    }

    @Test
    void contact() {
        String result = contactController.contact(model, 1);
        assertEquals("contact", result);
    }
}