package ua.kpi.arturo.registrationform.entity;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    ROLE_SUPERADMIN("SUPERADMIN"),
    ROLE_ADMIN("ADMIN"),
    ROLE_USER("USER");

    private String roleName;

    Role(String roleName) {
        this.roleName = roleName;
    }

    @Override
    public String getAuthority() {
        return name();
    }

    public String getRoleName() {
        return roleName;
    }
}
