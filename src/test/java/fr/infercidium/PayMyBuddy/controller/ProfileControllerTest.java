package fr.infercidium.PayMyBuddy.controller;

import fr.infercidium.PayMyBuddy.configuration.UserComponent;
import fr.infercidium.PayMyBuddy.dto.UserRegistrationDto;
import fr.infercidium.PayMyBuddy.model.BankAccount;
import fr.infercidium.PayMyBuddy.model.User;
import fr.infercidium.PayMyBuddy.service.BankAccountService;
import fr.infercidium.PayMyBuddy.service.TransferService;
import fr.infercidium.PayMyBuddy.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;

import java.time.LocalDate;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = {ProfileController.class})
class ProfileControllerTest {

    @MockBean
    private UserComponent userComponent;

    @MockBean
    private TransferService transferS;

    @MockBean
    private UserService userS;

    @MockBean
    private BankAccountService bankAccountS;

    @MockBean
    private Model model;

    @Autowired
    private ProfileController profileController;

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

        when(userComponent.saveUser()).thenReturn(user);
        when(bankAccountS.getBankAccountPageUser(isA(String.class), isA(Pageable.class))).thenReturn(new PageImpl<>(Collections.singletonList(bankAccount)));
    }

    @Test
    void userRegistrationDto() {
        UserRegistrationDto userRegistrationDto = profileController.userRegistrationDto();
        assertEquals(new UserRegistrationDto().getUserName(), userRegistrationDto.getUserName());
    }

    @Test
    void profile() {
        String result = profileController.profile(model, 1);
        assertEquals("profile", result);
    }

    @Test
    void editProfile() {
        String result = profileController.editProfile(userRegistrationDto);
        assertEquals("redirect:/profile?errorPassword", result);

        userRegistrationDto.setPassword2("123");
        String result2 = profileController.editProfile(userRegistrationDto);
        assertEquals("redirect:/profile?errorOldPassword", result2);

        userRegistrationDto.setOldPassword("123");
        String result3 = profileController.editProfile(userRegistrationDto);
        assertEquals("redirect:/profile?success", result3);
    }
}