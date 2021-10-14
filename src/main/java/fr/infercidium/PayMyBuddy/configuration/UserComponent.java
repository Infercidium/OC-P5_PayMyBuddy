package fr.infercidium.PayMyBuddy.configuration;

import fr.infercidium.PayMyBuddy.model.User;
import fr.infercidium.PayMyBuddy.service.UserI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class UserComponent {

    @Autowired
    private UserI userS;

    private User user;

    public User loadUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        user = userS.getUser(currentPrincipalName);
        return user;
    }

    public User saveUser() {
        if (user == null) {
            user = loadUser();
            System.out.println("verification perso");
        }
        return user;
    }
}
