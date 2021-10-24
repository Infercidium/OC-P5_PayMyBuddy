package fr.infercidium.PayMyBuddy.service;

import fr.infercidium.PayMyBuddy.dto.UserRegistrationDto;
import fr.infercidium.PayMyBuddy.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserI extends UserDetailsService {
    //Service Page Connexion
    /**
     * Removes a Connection.
     * @param id of the connection to delete.
     * @param user : Affected user.
     */
    void removeConnection(Long id, User user);

    /**
     * Add a connection.
     * @param email of connection to add.
     * @param user : Affected user.
     */
    void addConnection(String email, User user);

    //Service ProfileController
    /**
     * Modifies user data.
     * @param registrationDto new informations.
     * @param user to update.
     */
    void editUser(UserRegistrationDto registrationDto, User user);

    //Service
    /**
     * Save a transfer.
     * @param user to save.
     */
    void postUser(User user);

    /**
     * Update a transfer.
     * @param user to update.
     */
    void updateUser(User user);

    /**
     * Find a User by their email.
     * @param email used.
     * @return User found.
     */
    User getUser(String email);

    /**
     * Find a User by their id.
     * @param id used.
     * @return User found.
     */
    User getUser(Long id);

    //Pagination
    /**
     * Use the user's email to find the Knowuser
     * list and put them into pages.
     * @param email used by Repository.
     * @param pageable used by Repository.
     * @return multi-page Knowuser list found.
     */
    Page<User> getKnowUser(String email, Pageable pageable);

    /**
     * Use the user's email to find the Knowuser list.
     * @param email used.
     * @return a list of Knowuser.
     */
    List<User> getKnowUser(String email);

    /**
     * Allows you to have the complete list of users.
     * @return full user in List.
     */
    List<User> getUsers();
}
