package fr.infercidium.PayMyBuddy.service;

import fr.infercidium.PayMyBuddy.model.BankAccount;
import fr.infercidium.PayMyBuddy.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BankAccountI {
    //Service Page BankAccount
    /**
     * create a BankAccount in DataBase.
     * @param bankAccount to save.
     * @param user linked.
     */
    void creatBankAccount(BankAccount bankAccount, User user);

    /**
     * delete a BankAccount in dataBase. (just delink user)
     * @param id of BankAccount.
     * @param user linked.
     */
    void removeBankAccount(Long id, User user);

    //Service
    /**
     * Save a bankAccount.
     * @param bankAccount to save.
     */
    void postBankAccount(BankAccount bankAccount);

    /**
     * Find a bankAccount using its id.
     * @param id used.
     * @return bankAccount found.
     */
    BankAccount getBankAccount(Long id);

    //Pagination
    /**
     * Use the user's email to find the BankAccount list.
     * @param email used.
     * @return a list of bankAccount.
     */
    List<BankAccount> getUserBankAccount(String email);

    /**
     * Use the user's email to find the BankAccount
     * list and put them into pages.
     * @param email used by Repository.
     * @param pageable used by Repository.
     * @return multi-page BankAccount list found.
     */
    Page<BankAccount> getBankAccountPageUser(String email, Pageable pageable);
}
