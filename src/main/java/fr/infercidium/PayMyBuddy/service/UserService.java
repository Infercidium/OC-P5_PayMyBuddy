package fr.infercidium.PayMyBuddy.service;

import fr.infercidium.PayMyBuddy.dto.UserRegistrationDto;
import fr.infercidium.PayMyBuddy.model.Authority;
import fr.infercidium.PayMyBuddy.model.User;
import fr.infercidium.PayMyBuddy.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserService implements UserI {

    /**
     * Instantiation of LOGGER in order to inform in console.
     */
    private static final Logger LOGGER
            = LoggerFactory.getLogger(UserService.class);

    /**
     * Instantiation of bCryptPasswordEncoder.
     */
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    /**
     * Instantiation of userRepository.
     */
    @Autowired
    private UserRepository userR;

    /**
     * Instantiation of authorityInterface.
     */
    @Autowired
    private AuthorityI authorityS;

    //Service ConnexionController
    /**
     * Removes a Connection.
     * @param id of the connection to delete.
     * @param user : Affected user.
     */
    @Override
    public void removeConnection(final Long id, final User user) {
        User removed = getUser(id);

        user.removeKnowUser(removed);
        removed.removeKnowUser(user);

        updateUser(user);
        updateUser(removed);
        LOGGER.info("Connection deleted");
    }

    /**
     * Add a connection.
     * @param email of connection to add.
     * @param user : Affected user.
     */
    @Override
    public void addConnection(final String email, final User user) {
        User added = getUser(email);

        user.addKnowUser(added);
        added.addKnowUser(user);

        updateUser(user);
        updateUser(added);
        LOGGER.info("Connection added");
    }

    //Service ProfileController

    /**
     * Modifies user data.
     * @param registrationDto new information.
     * @param user to update.
     */
    @Override
    public void editUser(final UserRegistrationDto registrationDto,
                         final User user) {
        if (!registrationDto.getPassword().isEmpty()) {
            user.setPassword(passwordEncoder
                    .encode(registrationDto.getPassword()));
        }
        if (!registrationDto.getUserName().isEmpty()) {
            user.setUserName(registrationDto.getUserName());
        }

        updateUser(user);
        LOGGER.info("Updated User Information");
    }

    //Service
    /**
     * Save a transfer.
     * @param user to save.
     */
    @Override
    public void postUser(final User user) {
        Authority authority = authorityS.getUser();
        user.setAuthorities(Collections.singletonList(authority));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userR.save(user);
        LOGGER.debug("User creation");
    }

    /**
     * Update a transfer.
     * @param user to update.
     */
    @Override
    public void updateUser(final User user) {
        userR.save(user);
        LOGGER.debug("User updated");
    }

    /**
     * Find a User by their email.
     * @param email used.
     * @return User found.
     */
    @Override
    public User getUser(final String email) {
        LOGGER.debug("User found");
        return userR.findByEmailIgnoreCase(email);
    }

    /**
     * Find a User by their id.
     * @param id used.
     * @return User found.
     */
    @Override
    public User getUser(final Long id) {
        LOGGER.debug("User found");
        return userR.findById(id).get();
    }

    //Pagination
    /**
     * Use the user's email to find the Knowuser
     * list and put them into pages.
     * @param email used by Repository.
     * @param pageable used by Repository.
     * @return multi-page Knowuser list found.
     */
    @Override
    public Page<User> getKnowUser(final String email, final Pageable pageable) {
        LOGGER.debug("User connections page");
        return userR.findByKnowUserEmailIgnoreCase(email, pageable);
    }

    /**
     * Use the user's email to find the Knowuser list.
     * @param email used.
     * @return a list of Knowuser.
     */
    @Override
    public List<User> getKnowUser(final String email) {
        LOGGER.debug("List of User Connections");
        return userR.findByKnowUserEmailIgnoreCase(email);
    }

    /**
     * Allows you to have the complete list of users.
     * @return full user in List.
     */
    @Override
    public List<User> getUsers() {
        return userR.findAll();
    }

    // UserDetailsService

    /**
     * Load User security from the Username of a User model.
     * @param username research.
     * @return User of Security Springframework.
     * @throws UsernameNotFoundException if Username is not found.
     */
    @Override
    public UserDetails loadUserByUsername(final String username)
            throws UsernameNotFoundException {
        User user = userR.findByEmailIgnoreCase(username);
        if (user == null) {
            throw  new UsernameNotFoundException("Could not find user "
                    + "with that email");
        }
        return new org.springframework.security.core.userdetails.User(
                user.getEmail(), user.getPassword(),
                mapRolesToAuthorities(user.getAuthorities()));
    }

    /**
     * Used by loadUserByUsername.
     * @param authorities : role like User or Admin.
     * @return Authorities.
     */
    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(
            final Collection<Authority> authorities) {
        return authorities.stream().map(authority
                -> new SimpleGrantedAuthority(authority.getName()))
                .collect(Collectors.toList());
    }
}
