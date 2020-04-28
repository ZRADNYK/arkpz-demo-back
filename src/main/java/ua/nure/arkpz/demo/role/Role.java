package ua.nure.arkpz.demo.role;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    SUPERADMIN, ADMIN, USER;

    @Override
    public String getAuthority() {
        return name();
    }
}
