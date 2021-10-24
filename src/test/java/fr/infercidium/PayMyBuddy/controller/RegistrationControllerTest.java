package fr.infercidium.PayMyBuddy.controller;

import fr.infercidium.PayMyBuddy.dto.UserRegistrationDto;
import fr.infercidium.PayMyBuddy.mapper.UserMapper;
import fr.infercidium.PayMyBuddy.model.BankAccount;
import fr.infercidium.PayMyBuddy.model.User;
import fr.infercidium.PayMyBuddy.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = {RegistrationController.class})
class RegistrationControllerTest {

    @MockBean
    private UserService userS;

    @MockBean
    private UserMapper userM;

    @Autowired
    private RegistrationController registrationController;

    BankAccount bankAccount = new BankAccount("test1", "Jean Jean", LocalDate.now().plusMonths(2L), "123456789", "123", "FR123", "C123", "89");
    User user = new User("Jean@email.com", "123", "JeanJ", bankAccount);
    UserRegistrationDto userRegistrationDto = new UserRegistrationDto();

    @BeforeEach
    private void setUpPerTest() {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        userRegistrationDto.setPassword("123");
        userRegistrationDto.setUserName("test");
        userRegistrationDto.setOldPassword("456");
        userRegistrationDto.setEmail(user.getEmail());
        }

    @Test
    void userRegistrationDto() {
        UserRegistrationDto userRegistrationDto = registrationController.userRegistrationDto();
        assertEquals(new UserRegistrationDto().getUserName(), userRegistrationDto.getUserName());
    }

    @Test
    void userRegistration() {
        String result = registrationController.userRegistration(userRegistrationDto);
        assertEquals("redirect:/login?errorPassword", result);

        userRegistrationDto.setPassword2("123");
        String result2 = registrationController.userRegistration(userRegistrationDto);
        assertEquals("redirect:/home?registration", result2);

        when(userS.getUsers()).thenReturn(Collections.singletonList(user));
        String result3 = registrationController.userRegistration(userRegistrationDto);
        assertEquals("redirect:/login?emailUsed", result3);
    }
}