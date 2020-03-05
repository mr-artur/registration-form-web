package ua.kpi.arturo.registrationform.service;

import ua.kpi.arturo.registrationform.dto.LoginDto;
import ua.kpi.arturo.registrationform.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    void save(User user);

    List<User> getAllUsers();

    Optional<User> findByEmail(LoginDto loginDto);
}
