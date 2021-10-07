package fr.infercidium.PayMyBuddy.repository;

import fr.infercidium.PayMyBuddy.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmailIgnoreCase(String email);

    User findByEmailIgnoreCaseAndPassword(String email, String password);
}
