package ua.kpi.arturo.registrationform.service.impl;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ua.kpi.arturo.registrationform.entity.User;
import ua.kpi.arturo.registrationform.repository.UserRepository;
import ua.kpi.arturo.registrationform.service.UserService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class UserServiceImplTest {

    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    private static User user;

    @BeforeAll
    public static void setUp() {
        String email = "aaa@bb.cc";
        user = User.builder()
            .email(email)
            .build();
    }

    @Test
    public void getAllUsers_shouldReturnUsersList() {
        Mockito.when(userRepository.findAll()).thenReturn(List.of(user));

        List<User> users = userService.getAllUsers();

        assertTrue(users.contains(user));
    }

    @Test
    public void save_shouldCallSaveMethodOfRepository() {
        userService.save(user);

        Mockito.verify(userRepository).save(user);
    }
}
