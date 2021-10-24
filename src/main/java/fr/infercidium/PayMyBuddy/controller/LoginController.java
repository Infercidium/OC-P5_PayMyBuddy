package fr.infercidium.PayMyBuddy.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class LoginController {

    /**
     * Used to connect to the application.
     * @return the home page.
     */
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    /**
     * Allows you to log out of the application.
     * @param request of disconnection.
     * @param response of disconnection.
     * @return the login page.
     */
    @GetMapping("/logout")
    public String logout(final HttpServletRequest request,
                         final HttpServletResponse response) {
        Authentication auth
                = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login?logout";
    }
}
