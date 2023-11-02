package org.library.user_client.core.dto;

import org.library.user_client.core.enums.UserRole;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

public class UserDetailsDTO implements UserDetails {
    private final UUID uuid;
    private final String username;
    private final String password;
    private final List<UserRole> authorities;

    public UserDetailsDTO(UUID uuid, String username, String password, List<UserRole> authorities) {
        this.uuid = uuid;
        this.username = username;
        this.password = password;
        this.authorities = authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

    public UUID getUuid() {
        return uuid;
    }


}
