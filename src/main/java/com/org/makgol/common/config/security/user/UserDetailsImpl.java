package com.org.makgol.common.config.security.user;

import com.org.makgol.common.RoleType;
import com.org.makgol.users.vo.Users;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class UserDetailsImpl implements UserDetails {

    private Users user;

    public Users getUser() {
        return this.user;
    }

    public void setUsers(Users user) {
            this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        return Collections.singleton(new SimpleGrantedAuthority(RoleType.USER.getCode()));
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // 수정: 계정 만료 여부
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // 수정: 계정 잠김 여부
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // 수정: 자격 증명 만료 여부
    }

    @Override
    public boolean isEnabled() {
        return true; // 수정: 사용자 활성화 여부
    }
}
