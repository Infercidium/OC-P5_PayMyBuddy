package fr.infercidium.PayMyBuddy.controller;

import fr.infercidium.PayMyBuddy.dto.UserRegistrationDto;
import fr.infercidium.PayMyBuddy.model.User;
import fr.infercidium.PayMyBuddy.service.UserI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RolesAllowed({"USER"})
@Controller
public class UserRest {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserRest.class);

    private final UserI userS;

    public UserRest(final UserI userSe) {
        this.userS = userSe;
    }

    @PostMapping(value = "/User")
    public ResponseEntity<Void> createUser(@Valid @RequestBody final User user) {
        User postUser = userS.postUser(user);

        URI locate = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{email}")
                .buildAndExpand(postUser.getEmail(), postUser.getPassword())
                .toUri();

        LOGGER.info("Saving " + user.getUserName() + " in the Users table");
        return ResponseEntity.created(locate).build();
    }

    @PutMapping(value = "/User/{email}")
    public ResponseEntity<Void> editUser(@PathVariable final String email, @RequestBody final User user) {
        userS.editUser(email, user);
        LOGGER.info("User " + email + " modification");
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(value = "/User/{email}&{password}")
    public ResponseEntity<Void> removeUser(@PathVariable final String email, @PathVariable final String password) {
        userS.removeUser(email, password);
        LOGGER.info("User " + email + " deletion");
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/User/{email}")
    public User getUser(@PathVariable final String email) {
        User user = userS.getUser(email);
        LOGGER.info("User found");
        return user;
    }

    @GetMapping(value = "/Users")
    public List<User> getUsers() {
        List<User> userList = userS.getUsers();
        LOGGER.info("List of Users displayed");
        return userList;
    }
}
