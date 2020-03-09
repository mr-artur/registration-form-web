package ua.kpi.arturo.registrationform.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;
import ua.kpi.arturo.registrationform.dto.UserDto;
import ua.kpi.arturo.registrationform.entity.Role;
import ua.kpi.arturo.registrationform.entity.User;
import ua.kpi.arturo.registrationform.service.UserService;

@Slf4j
@Controller
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @RequestMapping("/login")
    public String login(@RequestParam(value = "error", required = false) String error,
                        @RequestParam(value = "logout", required = false) String logout,
                        @RequestParam(value = "signedup", required = false) String signedUp,
                        Model model) {
        if (!SecurityContextHolder.getContext().getAuthentication().getName().equals("anonymousUser")) {
            return "account.html";
        }
        model.addAttribute("error", error != null);
        model.addAttribute("logout", logout != null);
        model.addAttribute("signedUp", signedUp != null);
        return "logIn.html";
    }

    @RequestMapping("/signup")
    public String signUp(@ModelAttribute User user,
                         @RequestParam(value = "duplicate", required = false) String duplicate,
                         Model model) {
        model.addAttribute("roles", Role.values());
        model.addAttribute("duplicate", duplicate != null);
        model.addAttribute("user", user == null ? new User() : user);
        return "signUp.html";
    }

    @RequestMapping("/newuser")
    public RedirectView newUser(@ModelAttribute User user) {
        RedirectView redirectView = new RedirectView();

        userService.save(getUserWithDetails(user));
        redirectView.setUrl("/login?signedup");
        return redirectView;
    }

    private User getUserWithDetails(User user) {
        return User.builder()
            .firstName(user.getFirstName())
            .firstNameNative(user.getFirstNameNative())
            .lastName(user.getLastName())
            .lastNameNative(user.getLastNameNative())
            .email(user.getEmail())
            .role(user.getRole())
            .username(user.getUsername())
            .password(passwordEncoder.encode(user.getPassword()))
            .accountNonExpired(true)
            .accountNonLocked(true)
            .credentialsNonExpired(true)
            .enabled(true)
            .build();
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public RedirectView handleDuplicateEmails(DataIntegrityViolationException e) {
        log.info("Request to save user with already existing email or username : ", e);
        RedirectView redirectView = new RedirectView();

        redirectView.setUrl("/signup?duplicate");
        return redirectView;
    }

    @RequestMapping("/success")
    public RedirectView successRedirect() {
        RedirectView redirectView = new RedirectView();

        if (isAdmin()) {
            redirectView.setUrl("/users/all");
        } else {
            redirectView.setUrl("/account");
        }
        return redirectView;
    }

    private boolean isAdmin() {
        Role role = getCurrentUser().getRole();
        return role.equals(Role.ROLE_ADMIN) || role.equals(Role.ROLE_SUPERADMIN);
    }

    private User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDto dto = (UserDto) authentication.getPrincipal();
        return dto.getUser();
    }
}
