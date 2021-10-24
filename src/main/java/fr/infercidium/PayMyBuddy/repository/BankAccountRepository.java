package fr.infercidium.PayMyBuddy.repository;

import fr.infercidium.PayMyBuddy.model.BankAccount;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BankAccountRepository
        extends JpaRepository<BankAccount, Long> {

    //Pagination
    /**
     * Find a BankAccount list with his email.
     * @param email used.
     * @return a BankAccount list.
     */
    List<BankAccount> findByUserEmailIgnoreCase(String email);

    /**
     * Find a list of BankAccount linked
     * to the User email address in several pages.
     * @param email
     * @param pageable
     * @return a list of BankAccount.
     */
    Page<BankAccount> findByUserEmailIgnoreCase(String email,
                                                Pageable pageable);
}
