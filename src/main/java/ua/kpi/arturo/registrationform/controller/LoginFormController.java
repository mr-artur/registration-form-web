package ua.kpi.arturo.registrationform.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ua.kpi.arturo.registrationform.dto.UserDTO;

@Slf4j
@RestController
@RequestMapping(value = "/")
public class LoginFormController {

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public void login(UserDTO user){
        log.info("{}", user);
    }
}
