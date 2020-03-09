package ua.kpi.arturo.registrationform.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import ua.kpi.arturo.registrationform.entity.User;

import java.util.List;

public interface UserService extends UserDetailsService {

    void save(User user);

    List<User> getAllUsers();
}
