package fr.infercidium.PayMyBuddy.controller;

import fr.infercidium.PayMyBuddy.configuration.UserComponent;
import fr.infercidium.PayMyBuddy.dto.BankAccountDto;
import fr.infercidium.PayMyBuddy.mapper.BankAccountMapper;
import fr.infercidium.PayMyBuddy.model.BankAccount;
import fr.infercidium.PayMyBuddy.model.User;
import fr.infercidium.PayMyBuddy.service.BankAccountService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = {BankAccountController.class})
class BankAccountControllerTest {

    @MockBean
    private UserComponent userComponent;

    @MockBean
    private BankAccountMapper bankAccountM;

    @MockBean
    private BankAccountService bankAccountS;

    @Autowired
    private BankAccountController bankAccountController;

    BankAccount bankAccount = new BankAccount("test1", "Jean Jean", LocalDate.now().plusMonths(2L), "123456789", "123", "FR123", "C123", "89");
    BankAccountDto bankAccountDto = new BankAccountDto();
    User user = new User("Jean@email.com", "123", "JeanJ", bankAccount);

    @BeforeEach
    private void setUpPerTest() {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        bankAccountDto.setName(bankAccount.getName());
        bankAccountDto.setHolder(bankAccount.getHolder());
        bankAccountDto.setExpirationDate(String.valueOf(bankAccount.getExpirationDate()));
        bankAccountDto.setCardNumber(bankAccount.getCardNumber());
        bankAccountDto.setCryptogram(bankAccount.getCryptogram());
        bankAccountDto.setIban(bankAccount.getIban());
        bankAccountDto.setBic(bankAccount.getBic());
        bankAccountDto.setPassword("123");

        when(userComponent.saveUser()).thenReturn(user);
    }

    @Test
    void bankAccountDto() {
        BankAccountDto bankAccountDto = bankAccountController.bankAccountDto();
        assertEquals(new BankAccountDto().getName(), bankAccountDto.getName());
    }

    @Test
    void newBankAccount() {
        String result = bankAccountController.newBankAccount(bankAccountDto);
        assertEquals("redirect:/profile?successBank", result);

        bankAccountDto.setPassword("456");
        String result2 = bankAccountController.newBankAccount(bankAccountDto);
        assertEquals("redirect:/profile?errorOldPassword", result2);
    }

    @Test
    void removebank() {
        String result = bankAccountController.removebank(user.getId());
        assertEquals("redirect:/profile?successRemBank", result);
    }
}