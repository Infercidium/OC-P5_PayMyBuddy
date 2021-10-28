package fr.infercidium.PayMyBuddy.service;

import fr.infercidium.PayMyBuddy.model.BankAccount;
import fr.infercidium.PayMyBuddy.model.User;
import fr.infercidium.PayMyBuddy.repository.BankAccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.isA;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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

        when(bankAccountR.getById(isA(Long.class))).thenReturn(bankAccount);
    }

    @Test
    void postBankAccount() {
        bankAccountS.postBankAccount(bankAccount);
        verify(bankAccountR, times(1)).save(isA(BankAccount.class));
    }

    @Test
    void getBankAccount() {
        BankAccount bankAccountTest = bankAccountS.getBankAccount(bankAccount.getId());
        assertEquals(bankAccount, bankAccountTest);
    }

    @Test
    void getUserBankAccount() {
        when(bankAccountR.findByUserEmailIgnoreCase(isA(String.class))).thenReturn(Collections.singletonList(bankAccount));
        List<BankAccount> bankAccountList = bankAccountS.getUserBankAccount(user.getEmail());
        assertEquals(Collections.singletonList(bankAccount), bankAccountList);
    }

    @Test
    void getBankAccountPageUser() {
        when(bankAccountR.findByUserEmailIgnoreCase(isA(String.class), isA(Pageable.class))).thenReturn(new PageImpl<>(Collections.singletonList(bankAccount)));
        Page<BankAccount> bankAccountPage = bankAccountS.getBankAccountPageUser(user.getEmail(), Pageable.unpaged());
        assertEquals(new PageImpl<>(Collections.singletonList(bankAccount)), bankAccountPage);
    }
}