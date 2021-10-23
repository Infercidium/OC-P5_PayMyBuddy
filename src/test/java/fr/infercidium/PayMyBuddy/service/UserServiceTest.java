package fr.infercidium.PayMyBuddy.service;

import fr.infercidium.PayMyBuddy.dto.UserRegistrationDto;
import fr.infercidium.PayMyBuddy.model.BankAccount;
import fr.infercidium.PayMyBuddy.model.User;
import fr.infercidium.PayMyBuddy.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest(classes = {UserService.class})
class UserServiceTest {

    @MockBean
    private UserRepository userR;

    @MockBean
    private BCryptPasswordEncoder passwordEncoder;

    @MockBean
    private AuthorityService authorityS;

    @Autowired
    private UserService userS;

    BankAccount bankAccount = new BankAccount("test1", "Jean Jean", LocalDate.now().plusMonths(2L), "123456789", "123", "FR123", "C123", "89");
    User user = new User("Jean@email.com", "123", "JeanJ", bankAccount);
    UserRegistrationDto userRegistrationDto = new UserRegistrationDto();

    @BeforeEach
    private void setUpPerTest() {
        userRegistrationDto.setPassword("123");
        userRegistrationDto.setUserName("test");

        when(userR.findById(isA(Long.class))).thenReturn(java.util.Optional.of(user));
        when(userR.findByEmailIgnoreCase(isA(String.class))).thenReturn(user);
    }

    @Test
    void removeConnection() {
        userS.removeConnection(1L, user);
        verify(userR, times(2)).save(isA(User.class));
    }

    @Test
    void addConnection() {
        userS.addConnection(user.getEmail(), user);
        verify(userR, times(2)).save(isA(User.class));
    }

    @Test
    void editUser() {
        userS.editUser(userRegistrationDto, user);
        verify(userR, times(1)).save(isA(User.class));
    }

    @Test
    void postUser() {
        userS.postUser(user);
        verify(userR, times(1)).save(isA(User.class));
    }

    @Test
    void updateUser() {
        userS.updateUser(user);
        verify(userR, times(1)).save(isA(User.class));
    }

    @Test
    void getUser() {
        User userResult = userS.getUser(user.getEmail());
        assertEquals(user, userResult);
    }

    @Test
    void testGetUser() {
        User userResult = userS.getUser(1L);
        assertEquals(user, userResult);
    }

    @Test
    void getKnowUser() {
        when(userR.findByKnowUserEmailIgnoreCase(isA(String.class), isA(Pageable.class))).thenReturn(new PageImpl<>(Collections.singletonList(user)));
        Page<User> userPage = userS.getKnowUser(user.getEmail(), Pageable.unpaged());
        assertEquals(new PageImpl<>(Collections.singletonList(user)), userPage);
    }

    @Test
    void testGetKnowUser() {
        when(userR.findByKnowUserEmailIgnoreCase(isA(String.class))).thenReturn(Collections.singletonList(user));
        List<User> userPage = userS.getKnowUser(user.getEmail());
        assertEquals(Collections.singletonList(user), userPage);
    }

    @Test
    void getUsers() {
        when(userR.findAll()).thenReturn(Collections.singletonList(user));
        List<User> userList = userS.getUsers();
        assertEquals(Collections.singletonList(user), userList);
    }
}