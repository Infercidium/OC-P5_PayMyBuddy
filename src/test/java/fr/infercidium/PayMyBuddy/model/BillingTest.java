package fr.infercidium.PayMyBuddy.model;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = {Billing.class})
class BillingTest {

    BankAccount bankAccount = new BankAccount("test1", "Jean Jean", LocalDate.now().plusMonths(2L), "123456789", "123", "FR123", "C123", "89");
    User user = new User("Jean@email.com", "123", "JeanJ", bankAccount);
    TransferUser transferUser = new TransferUser(user, BigDecimal.valueOf(100), "test", user);
    Billing billing = new Billing(user, transferUser, BigDecimal.valueOf(0.50));
    @Test
    void testToString() {
        assertEquals("Billing{id = null, user = User{id = null, email = 'Jean@email.com', password = '123', userName = 'JeanJ', pay = 0, historyCredited = [], historyDebited = [], knowUser = User : {}, authorities = [], bankAccount = BankAccount : {test1, }}, transfer = Transfert{id = null, credited = User{id = null, email = 'Jean@email.com', password = '123', userName = 'JeanJ', pay = 0, historyCredited = [], historyDebited = [], knowUser = User : {}, authorities = [], bankAccount = BankAccount : {test1, }}, amount = 100, description = 'test', dateTime = " + transferUser.getDateTime() + ", debited = User{id = null, email = 'Jean@email.com', password = '123', userName = 'JeanJ', pay = 0, historyCredited = [], historyDebited = [], knowUser = User : {}, authorities = [], bankAccount = BankAccount : {test1, }}}, amount = 0.5}", billing.toString());
    }
}