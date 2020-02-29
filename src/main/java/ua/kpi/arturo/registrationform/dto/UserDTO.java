package ua.kpi.arturo.registrationform.dto;

import lombok.AllArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@ToString
public class UserDTO {

    private String email;
    private String password;
}
