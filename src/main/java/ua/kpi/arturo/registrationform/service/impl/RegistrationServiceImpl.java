package ua.kpi.arturo.registrationform.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ua.kpi.arturo.registrationform.dto.RegistrationDto;
import ua.kpi.arturo.registrationform.service.RegistrationService;

@Slf4j
@Service
public class RegistrationServiceImpl implements RegistrationService {

    @Override
    public void register(RegistrationDto dto) {
        log.info("Received in service : {}", dto);
    }
}
