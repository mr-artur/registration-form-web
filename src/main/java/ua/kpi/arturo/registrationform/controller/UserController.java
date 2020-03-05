package ua.kpi.arturo.registrationform.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import ua.kpi.arturo.registrationform.dto.LoginDto;
import ua.kpi.arturo.registrationform.dto.NativeUserDto;
import ua.kpi.arturo.registrationform.entity.User;
import ua.kpi.arturo.registrationform.service.UserService;

import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

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
        Locale locale = LocaleContextHolder.getLocale();
        List<User> users = userService.getAllUsers();
        List<NativeUserDto> nativeUsers = users.stream()
            .map(user -> createNativeUser(user, locale))
            .collect(Collectors.toList());
        model.addAttribute("users", nativeUsers);
        return "allUsers.html";
    }

    private NativeUserDto createNativeUser(User user, Locale locale) {
        NativeUserDto nativeUser = buildStableFields(user);
        setLocalizedFields(nativeUser, user, locale);
        return nativeUser;
    }

    private void setLocalizedFields(NativeUserDto nativeUser, User user, Locale locale) {
        if(locale.equals(Locale.ENGLISH)) {
            nativeUser.setFirstName(user.getFirstName());
            nativeUser.setLastName(user.getLastName());
        } else {
            nativeUser.setFirstName(user.getFirstNameNative());
            nativeUser.setLastName(user.getLastNameNative());
        }
    }

    private NativeUserDto buildStableFields(User user) {
        return NativeUserDto.builder()
            .id(user.getId())
            .email(user.getEmail())
            .role(user.getRole())
            .build();
    }
}
