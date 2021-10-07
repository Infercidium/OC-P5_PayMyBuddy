package fr.infercidium.PayMyBuddy.repository;

import fr.infercidium.PayMyBuddy.model.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityRepository extends JpaRepository<Authority, Long> {
    Authority findByNameIgnoreCase(String user);
}