package ua.kpi.arturo.registrationform.dto;

import lombok.AllArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@ToString
public class LoginDto {

    private String email;
    private String password;
}
