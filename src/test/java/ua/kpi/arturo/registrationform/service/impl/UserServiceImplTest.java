package ua.kpi.arturo.registrationform.service.impl;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ua.kpi.arturo.registrationform.dto.LoginDto;
import ua.kpi.arturo.registrationform.entity.User;
import ua.kpi.arturo.registrationform.repository.UserRepository;
import ua.kpi.arturo.registrationform.service.UserService;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class UserServiceImplTest {

    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    private static User user;
    private static LoginDto loginDto;
    private static LoginDto notExistingLoginDto;

    @BeforeAll
    public static void setUp() {
        String email = "aaa@bb.cc";
        user = User.builder()
            .email(email)
            .build();
        loginDto = new LoginDto(email, "1234");
        notExistingLoginDto = new LoginDto("bbb@c.c", "1234");
    }

    @Test
    public void getAllUsers_shouldReturnUsersList() {
        Mockito.when(userRepository.findAll()).thenReturn(List.of(user));

        List<User> users = userService.getAllUsers();

        assertTrue(users.contains(user));
    }

    @Test
    public void findbyEmail_shouldReturnOptionalUser_whenUserWithSuchEmailExistsInDB() {
        Mockito.when(userRepository.findByEmail(user.getEmail())).thenReturn(Optional.of(user));

        Optional<User> found = userService.findByEmail(loginDto);

        assertTrue(found.isPresent());
        assertEquals(user, found.get());
    }

    @Test
    public void findbyEmail_shouldReturnEmptyOptional_whenUserWithSuchEmailDoesNotExistInDB() {
        Mockito.when(userRepository.findByEmail(notExistingLoginDto.getEmail())).thenReturn(Optional.empty());

        Optional<User> found = userService.findByEmail(notExistingLoginDto);

        assertTrue(found.isEmpty());
    }

    @Test
    public void save_shouldCallSaveMethodOfRepository() {
        userService.save(user);

        Mockito.verify(userRepository).save(user);
    }
}
