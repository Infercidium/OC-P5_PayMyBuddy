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

    private User user = null;

    public User saveUser() {
        if (user == null) {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String currentPrincipalName = authentication.getName();
            user = userS.getUser(currentPrincipalName);
            System.out.println("Sauver");
        }
        return user;
    }

    public void cleanUser() {
        System.out.println("Vider");
        user = null;
    }
}
