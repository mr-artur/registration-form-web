package ua.kpi.arturo.registrationform.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public class LoginDto {

    private String email;
    private String password;
}
