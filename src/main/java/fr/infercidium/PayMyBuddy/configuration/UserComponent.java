package fr.infercidium.PayMyBuddy.configuration;

import fr.infercidium.PayMyBuddy.model.User;
import fr.infercidium.PayMyBuddy.service.UserI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class UserComponent {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserComponent.class);

    @Autowired
    private UserI userS;

    private User user = null;

    public User saveUser() {
        if (user == null) {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String currentPrincipalName = authentication.getName();
            user = userS.getUser(currentPrincipalName);
            LOGGER.info("Current User loaded");
        }
        return user;
    }

    public void cleanUser() {
        LOGGER.info("Emptying User caches");
        user = null;
    }
}
