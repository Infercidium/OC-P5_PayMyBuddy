package fr.infercidium.PayMyBuddy.service;

import fr.infercidium.PayMyBuddy.model.Transfer;
import fr.infercidium.PayMyBuddy.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TransferI {
    //Service MoneyController
    void addCardMoney(Transfer transferAdd, User user);

    void removCardMoney(Transfer transferRemov, User user);

    void transactMoney(Transfer transferUser, User user);

    //Service
    void postTransfer(Transfer transfer);

    //Pagination
    Page<Transfer> getTransferPageCredited(String email, Pageable pageable);

    Page<Transfer> getTransferPageDebited(String email, Pageable pageable);

    List<Transfer> getTransferCredited(String email);

    List<Transfer> getTransferDebited(String email);
}
