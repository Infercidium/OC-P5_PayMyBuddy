package fr.infercidium.PayMyBuddy.repository;

import fr.infercidium.PayMyBuddy.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    //Service
    /**
     * Find a User with his email.
     * @param email used.
     * @return User found.
     */
    User findByEmailIgnoreCase(String email);

    //Pagination

    /**
     * Find a friend user list with the user owning the multi-page email.
     * @param email used.
     * @param pageable to make the pages.
     * @return multi-page list of user friends.
     */
    Page<User> findByKnowUserEmailIgnoreCase(String email, Pageable pageable);

    /**
     * Find a friend user list with the user owning the email.
     * @param email used.
     * @return a list of user friends.
     */
    List<User> findByKnowUserEmailIgnoreCase(String email);
}
