package fr.infercidium.PayMyBuddy.service;

import fr.infercidium.PayMyBuddy.model.Authority;
import fr.infercidium.PayMyBuddy.model.User;
import fr.infercidium.PayMyBuddy.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class UserService implements UserI {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

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
        return this.userR.save(user);
    }

    @Override
    public void editUser(final String email, final User user) {
        User verifiedUser = userVerification(email, user);
        postUser(verifiedUser);
    }

    @Override
    public void removeUser(final String email, final String password) {
        User user = this.userR.findByEmailIgnoreCaseAndPassword(email, password);
        if (user != null) {
            this.userR.delete(user);
        } else {
            throw new NullPointerException();
        }
    }

    @Override
    public User getUser(final String email) {
        User user = this.userR.findByEmailIgnoreCase(email);
        if (user != null) {
            return user;
        } else {
            throw  new NullPointerException();
        }
    }

    @Override
    public List<User> getUsers() {
        List<User> userList = this.userR.findAll();
        if (!userList.isEmpty()) {
            return userList;
        } else {
            throw new NullPointerException();
        }
    }
    
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
        if (user.getKnowUser() == null) {
            user.setKnowUser(origineUser.getKnowUser());
        }
        if (user.getPay() == null) {
            user.setPay(origineUser.getPay());
        }
        if (user.getHistoryCredited() == null) {
            user.setHistoryCredited(origineUser.getHistoryCredited());
        }
        if (user.getHistoryDebited() == null) {
            user.setHistoryDebited(origineUser.getHistoryDebited());
        }
        if (user.getAuthorities() == null) {
            user.setAuthorities(origineUser.getAuthorities());
        }
        LOGGER.debug("Verification of User fields");
        return user;
    }
}
