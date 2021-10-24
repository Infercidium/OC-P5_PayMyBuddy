package fr.infercidium.PayMyBuddy.configuration;

import fr.infercidium.PayMyBuddy.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest(classes = {UserComponent.class})
class UserComponentTest {

    @MockBean
    private UserService userS;

    @Autowired
    private UserComponent userComponent;

    @Test
    void cleanUser() {
        userComponent.cleanUser();
        assertNull(userComponent.getUser());
    }
}