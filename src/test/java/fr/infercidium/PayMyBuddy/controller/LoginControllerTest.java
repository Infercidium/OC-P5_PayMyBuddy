package fr.infercidium.PayMyBuddy.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = {LoginController.class})
class LoginControllerTest {

    @MockBean
    private HttpServletRequest request;

    @MockBean
    private HttpServletResponse response;

    @Autowired
    private LoginController loginController;

    @Test
    void login() {
        String result = loginController.login();
        assertEquals("login", result);
    }

    @Test
    void logout() {
        String result = loginController.logout(request,response);
        assertEquals("redirect:/login?logout", result);
    }
}