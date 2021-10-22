package fr.infercidium.PayMyBuddy.service;

import fr.infercidium.PayMyBuddy.dto.UserRegistrationDto;
import fr.infercidium.PayMyBuddy.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserI extends UserDetailsService {
    //Service Page Connexion
    void removeConnection(Long id, User user);

    void addConnection(String email, User user);

    //Service ProfileController
    void editUser(UserRegistrationDto registrationDto, User user);

    //Service
    void postUser(User user);

    void updateUser(User user);

    User getUser(String email);

    User getUser(Long id);

    //Pagination
    Page<User> getKnowUser(String email, Pageable pageable);

    List<User> getKnowUser(String email);
}
