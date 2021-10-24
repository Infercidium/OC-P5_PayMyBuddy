package fr.infercidium.PayMyBuddy.controller;

import fr.infercidium.PayMyBuddy.configuration.UserComponent;
import fr.infercidium.PayMyBuddy.model.BankAccount;
import fr.infercidium.PayMyBuddy.model.TransferAdd;
import fr.infercidium.PayMyBuddy.model.TransferRemov;
import fr.infercidium.PayMyBuddy.model.TransferUser;
import fr.infercidium.PayMyBuddy.model.User;
import fr.infercidium.PayMyBuddy.service.TransferService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = {MoneyController.class})
class MoneyControllerTest {

    @MockBean
    private UserComponent userComponent;

    @MockBean
    private TransferService transferS;

    @Autowired
    MoneyController moneyController;

    BankAccount bankAccount = new BankAccount("test1", "Jean Jean", LocalDate.now().plusMonths(2L), "123456789", "123", "FR123", "C123", "89");
    User user = new User("Jean@email.com", "123", "JeanJ", bankAccount);
    TransferAdd transferAdd = new TransferAdd(user, BigDecimal.valueOf(100), "test", bankAccount);
    TransferRemov transferRem = new TransferRemov(BigDecimal.valueOf(100), "test", user, bankAccount);
    TransferUser transferUser = new TransferUser(user, BigDecimal.valueOf(100), "test", user);

    @BeforeEach
    private void setUpPerTest() {
        user.setPay(BigDecimal.valueOf(1000.00));

        when(userComponent.saveUser()).thenReturn(user);
    }

    @Test
    void transferAdd() {
        TransferAdd transferAdd = moneyController.transferAdd();
        assertEquals(new TransferAdd().getId(), transferAdd.getId());
    }

    @Test
    void transferRemov() {
        TransferRemov transferRemov = moneyController.transferRemov();
        assertEquals(new TransferRemov().getId(), transferRemov.getId());
    }

    @Test
    void transferUser() {
        TransferUser transferUser = moneyController.transferUser();
        assertEquals(new TransferUser().getId(), transferUser.getId());
    }

    @Test
    void addMoney() {
        String result = moneyController.addMoney(transferAdd);
        assertEquals("redirect:/home?success", result);

        transferAdd.setAmount(BigDecimal.ZERO);
        String result2 = moneyController.addMoney(transferAdd);
        assertEquals("redirect:/home?noAmount", result2);
    }

    @Test
    void removMoney() {
        String result = moneyController.removMoney(transferRem);
        assertEquals("redirect:/home?success", result);

        transferRem.setAmount(BigDecimal.ZERO);
        String result2 = moneyController.removMoney(transferRem);
        assertEquals("redirect:/home?noAmount", result2);

        transferRem.setAmount(BigDecimal.TEN);
        user.setPay(BigDecimal.ZERO);
        String result3 = moneyController.removMoney(transferRem);
        assertEquals("redirect:/home?error", result3);
    }

    @Test
    void pay() {
        String result = moneyController.pay(transferUser);
        assertEquals("redirect:/transfer?successPay", result);

        transferUser.setAmount(BigDecimal.ZERO);
        String result2 = moneyController.pay(transferUser);
        assertEquals("redirect:/transfer?noAmount", result2);

        transferUser.setAmount(BigDecimal.TEN);
        user.setPay(BigDecimal.ZERO);
        String result3 = moneyController.pay(transferUser);
        assertEquals("redirect:/transfer?errorPay", result3);
    }
}