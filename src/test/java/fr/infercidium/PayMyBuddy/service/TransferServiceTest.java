package fr.infercidium.PayMyBuddy.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest(classes = {TransferService.class})
class TransferServiceTest {

    @Autowired
    private TransferService transferS;

    @BeforeEach
    private void setUpPerTest() {

    }

    @Test
    void addCardMoney() {
    }

    @Test
    void removCardMoney() {
    }

    @Test
    void transactMoney() {
    }

    @Test
    void postTransfer() {
    }

    @Test
    void getTransferPageCredited() {
    }

    @Test
    void getTransferPageDebited() {
    }

    @Test
    void getTransferCredited() {
    }

    @Test
    void getTransferDebited() {
    }
}