package fr.infercidium.PayMyBuddy.model;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = {TransferRemov.class})
class TransferRemovTest {

    BankAccount bankAccount = new BankAccount("test1", "Jean Jean", LocalDate.now().plusMonths(2L), "123456789", "123", "FR123", "C123", "89");
    User user = new User("Jean@email.com", "123", "JeanJ", bankAccount);
    TransferRemov transferRem = new TransferRemov(BigDecimal.valueOf(100), "test", user, bankAccount);

    @Test
    void testToString() {
        bankAccount.setUser(user);
        assertEquals("Transfert{id = null, amount = 100, description = 'test', dateTime = " + transferRem.getDateTime() + ", debited = User{id = null, email = 'Jean@email.com', password = '123', userName = 'JeanJ', pay = 0, historyCredited = [], historyDebited = [], knowUser = User : {}, authorities = [], bankAccount = BankAccount : {test1, }}, bankAccount = BankAccount{id = null, name = 'test1', holder = 'Jean Jean', user = JeanJ', expirationDate = 2021-12-24, cardNumber = '123456789', cryptogram = '123', iban = 'FR123', bic = 'C123', deer = '89'}}", transferRem.toString());
    }
}