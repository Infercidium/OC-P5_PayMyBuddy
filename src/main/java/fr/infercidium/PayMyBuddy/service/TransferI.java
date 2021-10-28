package fr.infercidium.PayMyBuddy.service;

import fr.infercidium.PayMyBuddy.model.Transfer;
import fr.infercidium.PayMyBuddy.model.TransferAdd;
import fr.infercidium.PayMyBuddy.model.TransferRemov;
import fr.infercidium.PayMyBuddy.model.TransferUser;
import fr.infercidium.PayMyBuddy.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TransferI {
    //Service MoneyController
    /**
     * Adds money from bankAccount to the User.
     * @param transferAdd : transfer with bankAccount.
     * @param user : Affected user.
     */
    void addCardMoney(TransferAdd transferAdd, User user);

    /**
     * Adds money from User to the bankAccount.
     * @param transferRemov : transfer with bankAccount.
     * @param user : Affected user.
     */
    void removCardMoney(TransferRemov transferRemov, User user);

    /**
     * Transaction between user.
     * @param transferUser transfer with credited User.
     * @param user Affected debited User.
     */
    void transactMoney(TransferUser transferUser, User user);

    //Service
    /**
     * Save a transfer.
     * @param transfer to save.
     */
    void postTransfer(Transfer transfer);

    //Pagination
    /**
     * Use the user's email to find the Transfer Credited
     * list and put them into pages.
     * @param email used by Repository.
     * @param pageable used by Repository.
     * @return multi-page Transfer list found.
     */

    Page<Transfer> getTransferPageCredited(String email, Pageable pageable);

    /**
     * Use the user's email to find the Transfer Debited
     * list and put them into pages.
     * @param email used by Repository.
     * @param pageable used by Repository.
     * @return multi-page Transfer list found.
     */
    Page<Transfer> getTransferPageDebited(String email, Pageable pageable);

    /**
     * Use the user's email to find the Transfer Credited list.
     * @param email used.
     * @return a list of Transfer.
     */
    List<Transfer> getTransferCredited(String email);

    /**
     * Use the user's email to find the Transfer Debited list.
     * @param email used.
     * @return a list of Transfer.
     */
    List<Transfer> getTransferDebited(String email);
}
