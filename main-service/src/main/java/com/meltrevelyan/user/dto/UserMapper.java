package com.meltrevelyan.user.dto;

import com.meltrevelyan.user.User;
import lombok.experimental.UtilityClass;

@UtilityClass
public class UserMapper {

    public static User toUser(UserInDto userInDto) {
        return User.builder()
                .email(userInDto.getEmail())
                .firstName(userInDto.getFirstName())
                .lastName(userInDto.getLastName())
                .phoneNumber(userInDto.getPhoneNumber())
                .membershipStart(userInDto.getMembershipStart())
                .membershipExpiration(userInDto.getMembershipExpiration())
                .isActive(true)
                .build();
    }

    public static UserOutDto toOutDto(User user) {
        return UserOutDto.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .isActive(user.getIsActive())
                .build();
    }
}
