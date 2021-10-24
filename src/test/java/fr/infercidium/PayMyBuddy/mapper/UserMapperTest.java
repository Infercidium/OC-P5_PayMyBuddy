package fr.infercidium.PayMyBuddy.mapper;

import fr.infercidium.PayMyBuddy.dto.UserRegistrationDto;
import fr.infercidium.PayMyBuddy.model.BankAccount;
import fr.infercidium.PayMyBuddy.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = {UserMapperImpl.class})
class UserMapperTest {

    @Autowired
    private UserMapper userMapperImpl;

    BankAccount bankAccount = new BankAccount("test1", "Jean Jean", LocalDate.now().plusMonths(2L), "123456789", "123", "FR123", "C123", "89");
    User user = new User("Jean@email.com", "123", "JeanJ", bankAccount);
    UserRegistrationDto userRegistrationDto = new UserRegistrationDto();

    @BeforeEach
    private void setUpPerTest() {
        userRegistrationDto.setPassword(user.getPassword());
        userRegistrationDto.setUserName(user.getUserName());
        userRegistrationDto.setEmail(user.getEmail());
    }

    @Test
    void dtoToModel() {
        User userResult = userMapperImpl.dtoToModel(userRegistrationDto);
        assertEquals(user.getEmail(), userResult.getEmail());
        assertEquals(user.getUserName(), userResult.getUserName());
        assertEquals(user.getPassword(), userResult.getPassword());

        User userResult2 = userMapperImpl.dtoToModel(null);
        assertNull(userResult2);
    }
}