package fr.infercidium.PayMyBuddy.service;

import fr.infercidium.PayMyBuddy.model.User;

import java.util.List;

public interface UserI {
    User postUser(User user);

    void editUser(String email, User user);

    void removeUser(String email, String password);

    User getUser(String email);

    List<User> getUsers();
}
