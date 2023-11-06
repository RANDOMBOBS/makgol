package com.org.makgol.user.Dao;

import com.org.makgol.user.repository.UserRepository;
import com.org.makgol.user.vo.UserVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Objects;

@Repository
@RequiredArgsConstructor
public class UserDao {

    private final UserRepository userRepository;
    public UserVo findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}