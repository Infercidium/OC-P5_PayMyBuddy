package fr.infercidium.PayMyBuddy.repository;

import fr.infercidium.PayMyBuddy.model.BankAccount;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BankAccountRepository extends JpaRepository<BankAccount, Long> {
    //Service
    BankAccount findByCardNumber(String cardNumber);

    //Pagination
    List<BankAccount> findByUserEmailIgnoreCase(String email);

    Page<BankAccount> findByUserEmailIgnoreCase(String email, Pageable pageable);
}
