package ua.kpi.arturo.registrationform.service;

import ua.kpi.arturo.registrationform.dto.LoginDto;

@FunctionalInterface
public interface LoginService {

    void login(LoginDto dto);
}
