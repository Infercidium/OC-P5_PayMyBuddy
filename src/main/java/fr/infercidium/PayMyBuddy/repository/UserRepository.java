package fr.infercidium.PayMyBuddy.repository;

import fr.infercidium.PayMyBuddy.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmailIgnoreCase(String email);

    User findByEmailIgnoreCaseAndPassword(String email, String password);

    Page<User> findByKnowUserEmailIgnoreCase(String email, Pageable pageable);
    List<User> findByKnowUserEmailIgnoreCase(String email);
}
