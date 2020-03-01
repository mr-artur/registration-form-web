package ua.kpi.arturo.registrationform.service;

import ua.kpi.arturo.registrationform.dto.RegistrationDto;

@FunctionalInterface
public interface RegistrationService {

    void register(RegistrationDto dto);
}
