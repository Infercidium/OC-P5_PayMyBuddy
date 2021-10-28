package fr.infercidium.PayMyBuddy.model;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = {BankAccount.class})
class BankAccountTest {

    BankAccount bankAccount = new BankAccount("test1", "Jean Jean", LocalDate.now().plusMonths(2L), "123456789", "123", "FR123", "C123", "89");
    User user = new User("Jean@email.com", "123", "JeanJ", bankAccount);

    @Test
    void testToString() {
        bankAccount.setUser(user);
        assertEquals("BankAccount{id = null, name = 'test1', holder = 'Jean Jean', user = JeanJ', expirationDate = " + bankAccount.getExpirationDate() + ", cardNumber = '123456789', cryptogram = '123', iban = 'FR123', bic = 'C123', deer = '89'}", bankAccount.toString());
    }
}