package fr.infercidium.PayMyBuddy.model;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = {Authority.class})
class AuthorityTest {

    BankAccount bankAccount = new BankAccount("test1", "Jean Jean", LocalDate.now().plusMonths(2L), "123456789", "123", "FR123", "C123", "89");
    User user = new User("Jean@email.com", "123", "JeanJ", bankAccount);
    Authority authority = new Authority("test", Collections.singletonList(user));

    @Test
    void testToString() {
        assertEquals("Authority{id = null, name = 'test', users = [User{id = null, email = 'Jean@email.com', password = '123', userName = 'JeanJ', pay = 0, historyCredited = [], historyDebited = [], knowUser = User : {}, authorities = [], bankAccount = BankAccount : {test1, }}]}", authority.toString());
    }
}