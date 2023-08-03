package com.meltrevelyan.user;

import com.meltrevelyan.exception.EntityNotFoundException;
import com.meltrevelyan.user.dto.UserInDto;
import com.meltrevelyan.user.dto.UserMapper;
import com.meltrevelyan.user.dto.UserOutDto;
import com.meltrevelyan.user.dto.UserUpdateDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Transactional
    @Override
    public UserOutDto addUser(UserInDto userInDto) {
        User user = UserMapper.toUser(userInDto);
        user = userRepository.save(user);
        log.info("User with id {} was created", user.getId());
        return UserMapper.toOutDto(user);
    }

    @Transactional
    @Override
    public UserOutDto updateUser(UserUpdateDto userUpdateDto, Long userId) {
        User user = getById(userId);
        if (userUpdateDto.getFirstName() != null) {
            user.setFirstName(userUpdateDto.getFirstName());
        }
        if (userUpdateDto.getLastName() != null) {
            user.setLastName(userUpdateDto.getLastName());
        }
        if (userUpdateDto.getEmail() != null) {
            user.setEmail(userUpdateDto.getEmail());
        }
        if (userUpdateDto.getPhoneNumber() != null) {
            user.setPhoneNumber(userUpdateDto.getPhoneNumber());
        }
        if (userUpdateDto.getMembershipStart() != null) {
            user.setMembershipStart(userUpdateDto.getMembershipStart());
        }
        if (userUpdateDto.getMembershipExpiration() != null) {
            user.setMembershipExpiration(userUpdateDto.getMembershipExpiration());
        }

        user = userRepository.save(user);
        log.info("User with id {} was updated", userId);
        return UserMapper.toOutDto(user);
    }

    @Transactional
    @Override
    public void deleteUser(Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new EntityNotFoundException("User with id " + userId + " was not found");
        }
        userRepository.deleteById(userId);
        log.info("User with id {} was deleted", userId);
    }

    @Override
    public UserOutDto findUserById(Long userId) {
        log.info("Finding user by id {}", userId);
        return UserMapper.toOutDto(getById(userId));
    }

    @Override
    public User getById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User with id " + userId + " was not found"));
    }
}
