package com.org.makgol.common.oauth2.infra;

import com.org.makgol.common.oauth2.entity.SocialAuth;
import com.org.makgol.users.repository.UsersRepository;
import com.org.makgol.users.vo.Users;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UsersRepoUtil {
    private final UsersRepository usersRepository;

    public Optional<Users> findFirstByEmailOrderByIdAsc(String email){
        Users user = usersRepository.findByEmail(email);

        return Optional.ofNullable(user);
    }

    public Users save(Users user){
        if(usersRepository.save(user))
            return usersRepository.findByEmail(user.getEmail());

        return null;
    }

    public Optional<Users> findById(long id) {

        if (id >= Integer.MIN_VALUE && id <= Integer.MAX_VALUE) {
            Users user = usersRepository.findById(Math.toIntExact(id));
            return Optional.ofNullable(user);
        }
        return null;
    }

    public void saveSocial(SocialAuth social) {
        usersRepository.saveSocial(social);
    }
}
