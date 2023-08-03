package com.meltrevelyan.user;

import com.meltrevelyan.exception.EntityNotFoundException;
import com.meltrevelyan.user.dto.UserInDto;
import com.meltrevelyan.user.dto.UserMapper;
import com.meltrevelyan.user.dto.UserOutDto;
import com.meltrevelyan.user.dto.UserUpdateDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    private final User user = new User(1L, "test@ya.ru", "Melissa", "Trevelyan",
            "+79999999", LocalDateTime.now(), LocalDateTime.now().plusMonths(6), true);

    @Test
    void findUserByIdWhenUserFoundThenReturnUserDto() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        UserOutDto result = userService.findUserById(1L);
        UserOutDto expectedResult = UserMapper.toOutDto(user);

        assertEquals(expectedResult, result);
    }

    @Test
    void findUserByIdWhenUserNotFoundThenUserNotFoundExceptionThrown() {
        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> userService.findUserById(1L));
    }

    @Test
    void createNewUserSuccessfulThenReturnUserDto() {
        UserOutDto expected = UserMapper.toOutDto(user);
        UserInDto inDto = new UserInDto("test@ya.ru", "Melissa", "Trevelyan",
                "+79999999", user.getMembershipStart(), user.getMembershipExpiration());

        when(userRepository.save(any(User.class))).thenReturn(user);

        UserOutDto result = userService.addUser(inDto);

        assertEquals(expected, result);
    }

    @Test
    void deleteUserSuccessful() {
        when(userRepository.existsById(anyLong())).thenReturn(true);

        userService.deleteUser(1L);

        verify(userRepository).deleteById(1L);
    }

    @Test
    void deleteUserFailUserNotFound() {
        when(userRepository.existsById(anyLong())).thenReturn(false);

        assertThrows(EntityNotFoundException.class, () -> userService.deleteUser(1L));
    }

    @Test
    void updateUserSuccessful() {
        String newName = "Carolina";
        UserUpdateDto updateDto = new UserUpdateDto();
        User updated = user;
        updated.setFirstName(newName);
        updateDto.setFirstName(newName);
        UserOutDto expected = new UserOutDto(user.getId(), newName, "Trevelyan", true);

        when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));
        when(userRepository.save(any(User.class))).thenReturn(updated);

        UserOutDto result = userService.updateUser(updateDto, 1L);

        assertEquals(expected, result);
    }

    @Test
    void updateUserFailUserNotFound() {
        UserUpdateDto updateDto = new UserUpdateDto();
        when(userRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> userService.updateUser(updateDto, 1L));
    }

    @Test
    void findUserByIdSuccessful() {
        UserOutDto expected = new UserOutDto(user.getId(), "Melissa", "Trevelyan", true);
        when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));

        UserOutDto result = userService.findUserById(1L);

        assertEquals(expected, result);
    }

    @Test
    void findUserByIdFailUserNotFound() {
        when(userRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> userService.findUserById(1L));
    }
}
