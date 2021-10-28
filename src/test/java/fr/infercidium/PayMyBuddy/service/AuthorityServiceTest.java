package fr.infercidium.PayMyBuddy.service;

import fr.infercidium.PayMyBuddy.model.Authority;
import fr.infercidium.PayMyBuddy.repository.AuthorityRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = {AuthorityService.class})
class AuthorityServiceTest {

    @MockBean
    private AuthorityRepository authorityR;

    @Autowired
    private AuthorityService authorityS;

    Authority authority = new Authority("user", new ArrayList<>());

    @BeforeEach
    private void setUpPerTest() {
    authority.setId(1L);
    }

    @Test
    void getUser() {
        when(authorityR.findByNameIgnoreCase("user")).thenReturn(authority);
        Authority authority2 = authorityS.getRole("user");
        assertEquals(authority, authority);
    }
}