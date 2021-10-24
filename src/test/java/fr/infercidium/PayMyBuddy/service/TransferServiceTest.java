package fr.infercidium.PayMyBuddy.service;

import fr.infercidium.PayMyBuddy.model.BankAccount;
import fr.infercidium.PayMyBuddy.model.Transfer;
import fr.infercidium.PayMyBuddy.model.TransferAdd;
import fr.infercidium.PayMyBuddy.model.TransferRemov;
import fr.infercidium.PayMyBuddy.model.TransferUser;
import fr.infercidium.PayMyBuddy.model.User;
import fr.infercidium.PayMyBuddy.repository.TransferRepository;
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
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = {TransferService.class})
class TransferServiceTest {

    @MockBean
    private TransferRepository transferR;

    @MockBean
    private UserService userS;

    @Autowired
    private TransferService transferS;

    BankAccount bankAccount = new BankAccount("test1", "Jean Jean", LocalDate.now().plusMonths(2L), "123456789", "123", "FR123", "C123", "89");
    User user = new User("Jean@email.com", "123", "JeanJ", bankAccount);
    TransferAdd transferAdd = new TransferAdd(user, BigDecimal.valueOf(100), "test", bankAccount);
    TransferRemov transferRem = new TransferRemov(BigDecimal.valueOf(100), "test", user, bankAccount);
    TransferUser transferUser = new TransferUser(user, BigDecimal.valueOf(100), "test", user);

    @Test
    void addCardMoney() {
        transferS.addCardMoney(transferAdd, user);
        verify(userS, times(1)).updateUser(isA(User.class));
        verify(transferR, times(1)).save(isA(Transfer.class));
    }

    @Test
    void removCardMoney() {
        transferS.removCardMoney(transferRem, user);
        verify(userS, times(1)).updateUser(isA(User.class));
        verify(transferR, times(1)).save(isA(Transfer.class));
    }

    @Test
    void transactMoney() {
        transferS.transactMoney(transferUser, user);
        verify(userS, times(2)).updateUser(isA(User.class));
        verify(transferR, times(1)).save(isA(Transfer.class));
    }

    @Test
    void postTransfer() {
        transferS.postTransfer(transferAdd);
        verify(transferR, times(1)).save(isA(Transfer.class));
    }

    @Test
    void getTransferPageCredited() {
        when(transferR.findByCreditedEmailIgnoreCase(isA(String.class), isA(Pageable.class))).thenReturn(new PageImpl<>(Collections.singletonList(transferAdd)));
        Page<Transfer> transferPage = transferS.getTransferPageCredited(user.getEmail(), Pageable.unpaged());
        assertEquals(new PageImpl<>(Collections.singletonList(transferAdd)), transferPage);
    }

    @Test
    void getTransferPageDebited() {
        when(transferR.findByDebitedEmailIgnoreCase(isA(String.class), isA(Pageable.class))).thenReturn(new PageImpl<>(Collections.singletonList(transferRem)));
        Page<Transfer> transferPage = transferS.getTransferPageDebited(user.getEmail(), Pageable.unpaged());
        assertEquals(new PageImpl<>(Collections.singletonList(transferRem)), transferPage);
    }

    @Test
    void getTransferCredited() {
        when(transferR.findByCreditedEmailIgnoreCase(isA(String.class))).thenReturn(Collections.singletonList(transferAdd));
        List<Transfer> transferPage = transferS.getTransferCredited(user.getEmail());
        assertEquals(Collections.singletonList(transferAdd), transferPage);
    }

    @Test
    void getTransferDebited() {
        when(transferR.findByDebitedEmailIgnoreCase(isA(String.class))).thenReturn(Collections.singletonList(transferRem));
        List<Transfer> transferPage = transferS.getTransferDebited(user.getEmail());
        assertEquals(Collections.singletonList(transferRem), transferPage);
    }
}