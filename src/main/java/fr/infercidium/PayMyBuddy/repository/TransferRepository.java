package fr.infercidium.PayMyBuddy.repository;

import fr.infercidium.PayMyBuddy.model.Transfer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransferRepository extends JpaRepository<Transfer, Long> {
    Page<Transfer> findByCreditedEmail(String email, Pageable pageable);
    Page<Transfer> findByDebitedEmail(String email, Pageable pageable);
}
