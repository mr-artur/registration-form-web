package ua.kpi.arturo.registrationform.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping(value = "/account")
    public String getAccountPage() {
        return "account.html";
    }
}
