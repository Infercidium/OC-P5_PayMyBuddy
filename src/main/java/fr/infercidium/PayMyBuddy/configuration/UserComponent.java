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

    /**
     * Instantiation of LOGGER in order to inform in console.
     */
    private static final Logger LOGGER
            = LoggerFactory.getLogger(UserComponent.class);

    /**
     * Instantiation of userInterface.
     */
    @Autowired
    private UserI userS;

    /**
     * User attribute stored in order not to reload it every time.
     */
    private User user = null;

    /**
     * Used to find the user and save it in the attribute of the class.
     * @return the logged in user.
     */
    public User saveUser() {
        if (user == null) {
            Authentication authentication
                    = SecurityContextHolder.getContext().getAuthentication();
            String currentPrincipalName = authentication.getName();
            user = userS.getUser(currentPrincipalName);
            LOGGER.info("Current User loaded");
        }
        return user;
    }

    /**
     * Used to reset the user attribute to null in order to reload it,
     * update it.
     */
    public void cleanUser() {
        LOGGER.info("Emptying User caches");
        user = null;
    }

    /**
     * Used for test user attribute.
     * @return user attribute.
     */
    public User getUser() {
        return user;
    }
}
