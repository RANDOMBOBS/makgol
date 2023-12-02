package com.org.makgol.common.config.security.user;

import com.org.makgol.users.repository.UsersRepository;
import com.org.makgol.users.vo.Users;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
// userDetailsImple에 account를 넣어주는 서비스입니다.
public class UserDetailsServiceImpl implements UserDetailsService {

	private final UsersRepository usersRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

		try {
			Users user = usersRepository.findByEmail(email);
			log.info(user.toString());
			log.info("account.toString() -- > : {}", user.toString());
			UserDetailsImpl userDetails = new UserDetailsImpl();
			userDetails.setUsers(user);
			return userDetails;

		} catch(Exception e) {
			new RuntimeException("Not Found user");
		}

		return null;
	}
}