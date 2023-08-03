package com.meltrevelyan.user;

import com.meltrevelyan.user.dto.UserInDto;
import com.meltrevelyan.user.dto.UserOutDto;
import com.meltrevelyan.user.dto.UserUpdateDto;

public interface UserService {

    UserOutDto addUser(UserInDto userInDto);

    UserOutDto updateUser(UserUpdateDto userUpdateDto, Long userId);

    void deleteUser(Long userId);

    UserOutDto findUserById(Long userId);

    User getById(Long userId);
}
