package ua.kpi.arturo.registrationform.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.kpi.arturo.registrationform.dto.LoginDto;
import ua.kpi.arturo.registrationform.dto.NativeUserDto;
import ua.kpi.arturo.registrationform.entity.RoleType;
import ua.kpi.arturo.registrationform.entity.User;
import ua.kpi.arturo.registrationform.service.UserService;

import java.util.List;
import java.util.Optional;

@Slf4j
@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    private NativeUserBuilder nativeUserBuilder = new NativeUserBuilder();

    @GetMapping(value = "/login")
    public String signInPage() {
        return "signIn.html";
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping(value = "/login")
    public void login(LoginDto dto) {
        Optional<User> user = userService.findByEmail(dto);
        if (user.isPresent()) {
            log.info("Found user : {}", user.get());
        } else {
            log.info("User with such email : {} does not exist", dto.getEmail());
        }
    }

    @GetMapping(value = "/new")
    public String signUpPage(Model model) {
        model.addAttribute("roles", RoleType.values());
        return "signUp.html";
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/save")
    public void save(User user) {
        userService.save(user);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity handleDuplicateEmails(DataIntegrityViolationException e) {
        log.error("Request to save user with already existing email : ", e.getCause().getCause());
        return new ResponseEntity("User with such email already exists", HttpStatus.CONFLICT);
    }

    @GetMapping(value = "/all")
    public String getAll(Model model) {
        List<User> users = userService.getAllUsers();
        List<NativeUserDto> nativeUsers = nativeUserBuilder.localizeUsers(users);
        model.addAttribute("users", nativeUsers);
        return "allUsers.html";
    }
}
