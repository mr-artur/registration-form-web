package ua.kpi.arturo.registrationform.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ua.kpi.arturo.registrationform.dto.LoginDto;
import ua.kpi.arturo.registrationform.service.LoginService;

@Slf4j
@Service
public class LoginServiceImpl implements LoginService {

    @Override
    public void login(LoginDto dto) {
        log.info("Received in service : {}", dto);
    }
}
