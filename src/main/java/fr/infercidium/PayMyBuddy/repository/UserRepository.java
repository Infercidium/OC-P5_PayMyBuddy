package fr.infercidium.PayMyBuddy.repository;

import fr.infercidium.PayMyBuddy.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmailIgnoreCase(final String email);

    User findByEmailIgnoreCaseAndPassword(final String email, final String password);
}
