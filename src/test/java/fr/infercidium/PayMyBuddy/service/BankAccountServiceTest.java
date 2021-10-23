package fr.infercidium.PayMyBuddy.service;

import fr.infercidium.PayMyBuddy.model.BankAccount;
import fr.infercidium.PayMyBuddy.model.User;
import fr.infercidium.PayMyBuddy.repository.BankAccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = {BankAccountService.class})
class BankAccountServiceTest {

    @MockBean
    private BankAccountRepository bankAccountR;

    @MockBean
    private UserService userS;

    @Autowired
    private BankAccountService bankAccountS;

    BankAccount bankAccount = new BankAccount("test1", "Jean Jean", LocalDate.now().plusMonths(2L), "123456789", "123", "FR123", "C123", "89");
    BankAccount bankAccount2 = new BankAccount("test2", "Jean Jean", LocalDate.now().plusMonths(3L), "987654321", "123", "FR123", "C123", "21");
    User user = new User("Jean@email.com", "123", "JeanJ", bankAccount);

    @BeforeEach
    private void setUpPerTest() {
        user.setId(1L);
        bankAccount.setId(1L);
        bankAccount2.setId(2L);
        bankAccount.setUser(user);
    }

    @Test
    void creatBankAccount() {
        bankAccountS.creatBankAccount(bankAccount2, user);
    }

    @Test
    void removeBankAccount() {
        User userMock = mock(User.class);
        BankAccount bankAccountMock = mock(BankAccount.class);
        when(bankAccountMock.getId()).thenReturn(1L);
        doNothing().when(userMock).removeBankAccount(isA(BankAccount.class));
        doNothing().when(bankAccountMock).setUser(isNull());
        doNothing().when(userS).updateUser(isA(User.class));
        bankAccountS.removeBankAccount(bankAccountMock.getId(), userMock);

    }

    @Test
    void postBankAccount() {
    }

    @Test
    void getBankAccount() {
    }

    @Test
    void getUserBankAccount() {
    }

    @Test
    void getBankAccountPageUser() {
    }
}