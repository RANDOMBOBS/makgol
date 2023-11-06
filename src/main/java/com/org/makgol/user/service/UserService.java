package com.org.makgol.user.service;

import com.org.makgol.user.Dao.UserDao;
import com.org.makgol.user.exception.EmailExistException;
import com.org.makgol.user.vo.UserVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserDao userDao;

    public void checkExistEmail(String email) throws EmailExistException {
        UserVo user = userDao.findByEmail(email);

        if (user != null) {
            throw new EmailExistException();
        }
    }

}
