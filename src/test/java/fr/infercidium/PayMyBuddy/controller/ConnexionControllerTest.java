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

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = {ConnexionController.class})
class ConnexionControllerTest {

    @MockBean
    private UserComponent userComponent;

    @MockBean
    private UserService userS;

    @Autowired
    private ConnexionController connexionController;

    BankAccount bankAccount = new BankAccount("test1", "Jean Jean", LocalDate.now().plusMonths(2L), "123456789", "123", "FR123", "C123", "89");
    User user = new User("Jean@email.com", "123", "JeanJ", bankAccount);

    @BeforeEach
    private void setUpPerTest() {
       when(userComponent.saveUser()).thenReturn(user);
    }

    @Test
    void removecontact() {
        String result = connexionController.removecontact(user.getId());
        assertEquals("redirect:/contact?remove", result);
    }

    @Test
    void addcontactC() {
        String result = connexionController.addcontactC(user.getEmail());
        assertEquals("redirect:/contact?addMiss", result);

        when(userS.getUser(isA(String.class))).thenReturn(user);
        String result2 = connexionController.addcontactC(user.getEmail());
        assertEquals("redirect:/contact?addError", result2);

        when(userS.getUser(isA(String.class))).thenReturn(new User());
        String result3 = connexionController.addcontactC(user.getEmail());
        assertEquals("redirect:/contact?addCo", result3);
    }

    @Test
    void addcontactT() {
        String result = connexionController.addcontactT(user.getEmail());
        assertEquals("redirect:/transfer?addMiss", result);

        when(userS.getUser(isA(String.class))).thenReturn(user);
        String result2 = connexionController.addcontactT(user.getEmail());
        assertEquals("redirect:/transfer?addError", result2);

        when(userS.getUser(isA(String.class))).thenReturn(new User());
        String result3 = connexionController.addcontactT(user.getEmail());
        assertEquals("redirect:/transfer?addCo", result3);
    }
}