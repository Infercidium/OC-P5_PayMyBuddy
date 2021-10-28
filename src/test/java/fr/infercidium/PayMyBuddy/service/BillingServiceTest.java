package fr.infercidium.PayMyBuddy.service;

import fr.infercidium.PayMyBuddy.model.BankAccount;
import fr.infercidium.PayMyBuddy.model.Billing;
import fr.infercidium.PayMyBuddy.model.TransferUser;
import fr.infercidium.PayMyBuddy.model.User;
import fr.infercidium.PayMyBuddy.repository.BillingRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.isA;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = {BillingService.class})
class BillingServiceTest {

    @MockBean
    private BillingRepository billingR;

    @Autowired
    private BillingService billingS;

    BankAccount bankAccount = new BankAccount("test1", "Jean Jean", LocalDate.now().plusMonths(2L), "123456789", "123", "FR123", "C123", "89");
    User user = new User("Jean@email.com", "123", "JeanJ", bankAccount);
    TransferUser transferUser = new TransferUser(user, BigDecimal.valueOf(100), "test", user);
    Billing billing = new Billing(user, transferUser, BigDecimal.valueOf(0.50));

    @Test
    void getBillingPage() {
        when(billingR.findAll(isA(Pageable.class))).thenReturn(new PageImpl<>(Collections.singletonList(billing)));
        Page<Billing> billingPage = billingS.getBillingPage(Pageable.unpaged());
        assertEquals(new PageImpl<>(Collections.singletonList(billing)), billingPage);
    }

    @Test
    void postBilling() {
        billingS.postBilling(billing);
        verify(billingR, times(1)).save(isA(Billing.class));
    }
}