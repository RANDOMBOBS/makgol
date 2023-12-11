package com.org.makgol.common.oauth2.security;

import com.org.makgol.common.oauth2.infra.UsersRepoUtil;
import com.org.makgol.users.repository.UsersRepository;
import com.org.makgol.users.vo.Users;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UsersRepoUtil usersRepoUtil;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email)
        throws UsernameNotFoundException {

        Users user = usersRepoUtil.findFirstByEmailOrderByIdAsc(email)
            .orElseThrow(() -> new UsernameNotFoundException("User not found with email : " + email));
        return UserPrincipal.create(user);
    }

    @Transactional
    public UserDetails loadUserByEmail(String email) {


        Users user = usersRepoUtil.findFirstByEmailOrderByIdAsc(email)
            .orElseThrow(() -> new UsernameNotFoundException("User not found with id : " + email));
        return UserPrincipal.create(user);
    }

}