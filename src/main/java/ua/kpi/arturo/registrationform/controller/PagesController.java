package ua.kpi.arturo.registrationform.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PagesController {

    @RequestMapping("/api")
    public String mainPage() {
        return "index.html";
    }

    @RequestMapping("/registration")
    public String registrationForm() {
        return "registrationForm";
    }
}
