package fr.infercidium.PayMyBuddy.repository;

import fr.infercidium.PayMyBuddy.model.Billing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BillingRepository extends JpaRepository<Billing, Long> {
}
