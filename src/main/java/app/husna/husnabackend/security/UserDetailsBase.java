package app.husna.husnabackend.security;

import org.springframework.security.core.userdetails.UserDetails;

public interface UserDetailsBase extends UserDetails {
    Long getId();
    String getEmail();

    @Override
    default boolean isAccountNonExpired() {
        return true;
    }

    @Override
    default boolean isAccountNonLocked() {
        return true;
    }

    @Override
    default boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    default boolean isEnabled() {
        return true;
    }
}

