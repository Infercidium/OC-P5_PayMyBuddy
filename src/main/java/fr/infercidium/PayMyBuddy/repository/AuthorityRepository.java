package fr.infercidium.PayMyBuddy.repository;

import fr.infercidium.PayMyBuddy.model.Authority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorityRepository extends JpaRepository<Authority, Long> {
    /**
     * Find Authority with name role.
     * @param role research.
     * @return Authority.
     */
    Authority findByNameIgnoreCase(String role);
}
