package fr.infercidium.PayMyBuddy.repository;

import fr.infercidium.PayMyBuddy.model.Authority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorityRepository extends JpaRepository<Authority, Long> {
    Authority findByNameIgnoreCase(String user);
}
