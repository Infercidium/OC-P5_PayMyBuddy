package fr.infercidium.PayMyBuddy.controller;

import fr.infercidium.PayMyBuddy.model.BankAccount;
import fr.infercidium.PayMyBuddy.model.Billing;
import fr.infercidium.PayMyBuddy.model.TransferUser;
import fr.infercidium.PayMyBuddy.model.User;
import fr.infercidium.PayMyBuddy.service.BillingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.ui.Model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = {BillingController.class})
class BillingControllerTest {

    @MockBean
    private BillingService billingS;

    @MockBean
    private Model model;

    @Autowired
    private BillingController billingController;

    BankAccount bankAccount = new BankAccount("test1", "Jean Jean", LocalDate.now().plusMonths(2L), "123456789", "123", "FR123", "C123", "89");
    User user = new User("Jean@email.com", "123", "JeanJ", bankAccount);
    TransferUser transferUser = new TransferUser(user, BigDecimal.valueOf(100), "test", user);
    Billing billing = new Billing(user, transferUser, BigDecimal.valueOf(0.50));

    @BeforeEach
    private void setUpPerTest() {
        when(billingS.getBillingPage(isA(Pageable.class))).thenReturn(new PageImpl<>(Collections.singletonList(billing)));
    }

    @Test
    void billing() {
        String result = billingController.billing(model, 1);
        assertEquals("billing", result);
    }
}