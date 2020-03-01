package ua.kpi.arturo.registrationform.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ua.kpi.arturo.registrationform.dto.RegistrationDto;

@Slf4j
@RestController
@RequestMapping(value = "/api")
public class RegistrationFormController {

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value="/registration")
    public void register(RegistrationDto dto){
        log.info("{}", dto);
    }
}
