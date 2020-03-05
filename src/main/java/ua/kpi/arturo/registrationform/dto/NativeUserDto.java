package ua.kpi.arturo.registrationform.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import ua.kpi.arturo.registrationform.entity.RoleType;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
@ToString
public class NativeUserDto {

    private Long id;
    /**
     * Native first name
     */
    private String firstName;
    /**
     * Native last name
     */
    private String lastName;
    private String email;
    private RoleType role;
}
