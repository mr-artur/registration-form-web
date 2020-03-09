package ua.kpi.arturo.registrationform.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.kpi.arturo.registrationform.dto.NativeUserDto;
import ua.kpi.arturo.registrationform.entity.User;
import ua.kpi.arturo.registrationform.service.UserService;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    private NativeUserBuilder nativeUserBuilder = new NativeUserBuilder();

    @GetMapping(value = "/all")
    public String getAll(Model model) {
        List<User> users = userService.getAllUsers();
        List<NativeUserDto> nativeUsers = nativeUserBuilder.localizeUsers(users);
        model.addAttribute("users", nativeUsers);
        return "allUsers.html";
    }
}
