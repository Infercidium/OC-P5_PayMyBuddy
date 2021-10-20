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

    @Autowired
    private UserRepository userR;

    @Autowired
    private AuthorityService authorityS;

    @Override
    public void postUser(final User user) {
        List<Authority> authorities = Collections.singletonList(authorityS.getUser());
        user.setAuthorities(authorities);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userR.save(user);
    }

    @Override
    public void updateUser(final User user){
        userR.save(user);
    }

    @Override
    public User getUser(final String email) {
        return userR.findByEmailIgnoreCase(email);
    }

    @Override
    public User getUser(final Long id) {
        return userR.findById(id).get();
    }

    @Override
    public Page<User> getKnowUser(final String email, final Pageable pageable) {
        return userR.findByKnowUserEmailIgnoreCase(email, pageable);
    }

    @Override
    public List<User> getKnowUser(final String email) {
        return userR.findByKnowUserEmailIgnoreCase(email);
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
