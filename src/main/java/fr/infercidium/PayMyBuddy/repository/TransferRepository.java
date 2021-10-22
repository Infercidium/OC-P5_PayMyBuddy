package fr.infercidium.PayMyBuddy.repository;

import fr.infercidium.PayMyBuddy.model.Transfer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransferRepository extends JpaRepository<Transfer, Long> {

    //Pagination
    /**
     * Find a list of Transfer linked
     * to the creditor's email address in several pages.
     * @param email used.
     * @param pageable to make the pages.
     * @return a multi-page Transfer list.
     */
    Page<Transfer> findByCreditedEmailIgnoreCase(String email,
                                                 Pageable pageable);

    /**
     * Find a list of Transfer linked
     * to the debtor's email address in several pages.
     * @param email used.
     * @param pageable to make the pages.
     * @return a multi-page Transfer list.
     */
    Page<Transfer> findByDebitedEmailIgnoreCase(String email,
                                                Pageable pageable);

    /**
     * Find a Transfer list with his email.
     * @param email used.
     * @return a Transfer list.
     */
    List<Transfer> findByCreditedEmailIgnoreCase(String email);

    /**
     * Find a Transfer list with his email.
     * @param email used.
     * @return a Transfer list.
     */
    List<Transfer> findByDebitedEmailIgnoreCase(String email);
}
