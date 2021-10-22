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

    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userR;

    @Autowired
    private AuthorityService authorityS;

    //Service ConnexionController
    @Override
    public void removeConnection(final Long id, final User user) {
        User removed = getUser(id);

        user.removeKnowUser(removed);
        removed.removeKnowUser(user);

        updateUser(user);
        updateUser(removed);
        LOGGER.info("Connection deleted");
    }

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
    @Override
    public void editUser(final UserRegistrationDto registrationDto, final User user) {
        if (!registrationDto.getPassword().isEmpty()) {
            user.setPassword(passwordEncoder.encode(registrationDto.getPassword()));
        }
        if (!registrationDto.getUserName().isEmpty()) {
            user.setUserName(registrationDto.getUserName());
        }

        updateUser(user);
        LOGGER.info("Updated User Information");
    }

    //Service
    @Override
    public void postUser(final User user) {
        Authority authority = authorityS.getUser();
        user.setAuthorities(Collections.singletonList(authority));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userR.save(user);
        LOGGER.debug("User creation");
    }

    @Override
    public void updateUser(final User user){
        userR.save(user);
        LOGGER.debug("User updated");
    }

    @Override
    public User getUser(final String email) {
        LOGGER.debug("User found");
        return userR.findByEmailIgnoreCase(email);
    }

    @Override
    public User getUser(final Long id) {
        LOGGER.debug("User found");
        return userR.findById(id).get();
    }

    //Pagination
    @Override
    public Page<User> getKnowUser(final String email, final Pageable pageable) {
        LOGGER.debug("User connections page");
        return userR.findByKnowUserEmailIgnoreCase(email, pageable);
    }

    @Override
    public List<User> getKnowUser(final String email) {
        LOGGER.debug("List of User Connections");
        return userR.findByKnowUserEmailIgnoreCase(email);
    }

    // UserDetailsService
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userR.findByEmailIgnoreCase(username);
        if (user == null) {
            throw  new UsernameNotFoundException("Could not find user with that email");
        }
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), mapRolesToAuthorities(user.getAuthorities()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Authority> authorities) {
        return authorities.stream().map(authority -> new SimpleGrantedAuthority(authority.getName())).collect(Collectors.toList());
    }
}
