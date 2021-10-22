package fr.infercidium.PayMyBuddy.service;

import fr.infercidium.PayMyBuddy.model.Authority;
import fr.infercidium.PayMyBuddy.repository.AuthorityRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class AuthorityServiceTest {

    @MockBean
    private AuthorityRepository authorityR;

    @Autowired
    private AuthorityService authorityS;

    Authority authority = new Authority("user", new ArrayList<>());

    @Test
    void getUser() {
        when(authorityR.findByNameIgnoreCase("user")).thenReturn(authority);
        Authority authority2 = authorityS.getUser();
        assertEquals(authority2, authority);
    }
}