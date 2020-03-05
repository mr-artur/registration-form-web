package ua.kpi.arturo.registrationform.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import ua.kpi.arturo.registrationform.entity.User;
import ua.kpi.arturo.registrationform.service.UserService;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/new")
    public String signUpPage() {
        return "signUp.html";
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/save")
    public void save(User user) {
        userService.save(user);
    }

    @GetMapping(value = "/all")
    public String getAll(Model model) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        model.addAttribute("title", "All Users");
        return "allUsers.html";
    }
}
