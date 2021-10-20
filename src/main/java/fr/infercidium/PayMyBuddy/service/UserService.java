package fr.infercidium.PayMyBuddy.service;

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

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService implements UserI {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    private final UserRepository userR;
    private final AuthorityService authorityS;

    public UserService(final UserRepository userRe, final AuthorityService authoritySe) {
        this.userR = userRe;
        this.authorityS = authoritySe;
    }

    @Override
    public User postUser(final User user) {
        List<Authority> authorities = Collections.singletonList(authorityS.getUser());
        user.setAuthorities(authorities);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return this.userR.save(user);
    }

    @Override
    public void updateUser(final User user){
        userR.save(user);
    }

    @Override
    public User getUser(final String email) {
        User user = this.userR.findByEmailIgnoreCase(email);
        return user;
    }

    @Override
    public User getUser(final Long id) {
        User user = this.userR.findById(id).get();
        return user;
    }

    @Override
    public Page<User> getKnowUser(final String email, final Pageable pageable) {
        return userR.findByKnowUserEmailIgnoreCase(email, pageable);
    }

    @Override
    public List<User> getKnowUser(final String email) {
        return userR.findByKnowUserEmailIgnoreCase(email);
    }

    //TODO à voir
    private User userVerification(final String email, final User user) {
        User origineUser = getUser(email);
        user.setId(origineUser.getId());
        if (user.getUserName() == null) {
            user.setUserName(origineUser.getUserName());
        }
        if (user.getEmail() == null) {
            user.setEmail(origineUser.getEmail());
        }
        if (user.getPassword() == null) {
            user.setPassword(origineUser.getPassword());
        }
        if (user.getKnowUser().isEmpty()) {
            user.setKnowUser(origineUser.getKnowUser());
        }
        if (user.getPay() == null) {
            user.setPay(origineUser.getPay());
        }
        if (user.getHistoryCredited().isEmpty()) {
            user.setHistoryCredited(origineUser.getHistoryCredited());
        }
        if (user.getHistoryDebited().isEmpty()) {
            user.setHistoryDebited(origineUser.getHistoryDebited());
        }
        if (user.getAuthorities().isEmpty()) {
            user.setAuthorities(origineUser.getAuthorities());
        }
        LOGGER.debug("Verification of User fields");
        return user;
    }

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
