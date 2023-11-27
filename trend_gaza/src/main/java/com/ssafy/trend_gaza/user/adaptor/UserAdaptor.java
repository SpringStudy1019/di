package com.ssafy.trend_gaza.user.adaptor;


import com.ssafy.trend_gaza.user.entity.User;
import com.ssafy.trend_gaza.user.exception.UserNotFoundException;
import com.ssafy.trend_gaza.user.repository.UserMapper;
import com.ssafy.trend_gaza.user.service.UserService;
import java.sql.SQLException;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class UserAdaptor {

    private final UserMapper userMapper;
    private final UserService userService;

    public UserAdaptor(UserMapper userMapper, UserService userService) {
        this.userMapper = userMapper;
        this.userService = userService;
    }

    public User authUser(String nickname, String email, String socialId, String platform) {
        Optional<User> findUser = null;
        try {
            findUser = userMapper.findByEmail(email);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (findUser.isPresent()) {
            return userService.login(platform, findUser.get());
        } else {
            return userService.register(nickname, email, socialId, platform);
        }
    }

    public User findUser(String id) throws Exception {
        return userMapper.findById(id).orElseThrow(() -> UserNotFoundException.EXCEPTION);
    }

    public void isExistUser(String socialId) {
        boolean isExist = false;
        try {
            isExist = userMapper.existsBySocialId(socialId);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (!isExist) {
            throw UserNotFoundException.EXCEPTION;
        }
    }
}
