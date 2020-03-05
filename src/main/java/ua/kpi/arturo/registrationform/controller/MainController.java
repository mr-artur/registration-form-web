package ua.kpi.arturo.registrationform.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class MainController {

    @GetMapping("/")
    public RedirectView toLogin() {
        return new RedirectView("users/login");
    }
}
