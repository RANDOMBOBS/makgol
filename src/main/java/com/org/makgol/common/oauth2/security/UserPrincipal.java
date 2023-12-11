package com.org.makgol.common.oauth2.security;

import com.org.makgol.common.RoleType;
import com.org.makgol.users.vo.Users;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Slf4j
@Getter
@AllArgsConstructor
public class UserPrincipal implements OAuth2User, UserDetails {


    private String email;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;
    @Setter
    private Map<String, Object> attributes;

    public static UserPrincipal create(Users user) {
        List<GrantedAuthority> authorities =
                Collections.singletonList(new SimpleGrantedAuthority("" + RoleType.USER));
        return new UserPrincipal(user.getEmail(), user.getPassword(), authorities, null);
    }

    public static UserPrincipal create(Users user, Map<String, Object> attributes) {
        log.info("public static UserPrincipal create(Users user, Map<String, Object> attributes) {");
        UserPrincipal userPrincipal = UserPrincipal.create(user);
        userPrincipal.setAttributes(attributes);
        return userPrincipal;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String getName() {
        return email;
    }

    @Override
    public String getUsername() {
        return email;
    }

}