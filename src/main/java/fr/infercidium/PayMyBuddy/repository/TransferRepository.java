package fr.infercidium.PayMyBuddy.repository;

import fr.infercidium.PayMyBuddy.model.Transfer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransferRepository extends JpaRepository<Transfer, Long> {
    Page<Transfer> findByCreditedEmailIgnoreCase(String email, Pageable pageable);

    Page<Transfer> findByDebitedEmailIgnoreCase(String email, Pageable pageable);

    List<Transfer> findByCreditedEmailIgnoreCase(String email);

    List<Transfer> findByDebitedEmailIgnoreCase(String email);
}
