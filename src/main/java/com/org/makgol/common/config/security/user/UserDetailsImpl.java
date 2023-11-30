package com.org.makgol.common.config.security.user;

import com.org.makgol.users.vo.Users;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class UserDetailsImpl implements UserDetails {

    private Users users;

    public Users getAccount() {
        return this.users;
    }

    public void setUsers(Users users) {
            this.users = users;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        Map<String, GrantedAuthority> authorities = new HashMap<>();
        authorities.put("ROLE_USER", new SimpleGrantedAuthority("ROLE_USER"));
        return authorities.values();
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return users.getEmail();
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
}
