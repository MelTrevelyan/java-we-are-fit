package com.meltrevelyan.user;

import com.meltrevelyan.user.dto.UserInDto;
import com.meltrevelyan.user.dto.UserOutDto;
import com.meltrevelyan.user.dto.UserUpdateDto;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@AutoConfigureTestDatabase
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class UserServiceIntegrationTest {

    private final UserRepository userRepository;

    private final UserService userService;

    @Test
    void createUserThenReturnUserDto() {
        UserInDto inDto = new UserInDto("test@ya.ru", "Melissa", "Trevelyan",
                "+79999999", LocalDateTime.now(), LocalDateTime.now().plusMonths(6));

        UserOutDto result = userService.addUser(inDto);

        assertEquals(1L, result.getId());
        assertEquals(inDto.getFirstName(), result.getFirstName());
        assertEquals(inDto.getLastName(), result.getLastName());
    }

    @Test
    void updateUserThenReturnUserDto() {
        User user = new User(1L, "tulip@ya.ru", "Melissa", "Trevelyan",
                "+78999999", LocalDateTime.now(), LocalDateTime.now().plusMonths(6), true);
        UserUpdateDto updateDto = new UserUpdateDto();
        updateDto.setFirstName("Carolina");
        UserOutDto expected = new UserOutDto(user.getId(), "Carolina", "Trevelyan", true);

        userRepository.save(user);
        UserOutDto result = userService.updateUser(updateDto, 1L);

        assertEquals(expected, result);
    }

    @Test
    void deleteUserTest() {
        User user = new User(1L, "tulip@ya.ru", "Melissa", "Trevelyan",
                "+78999999", LocalDateTime.now(), LocalDateTime.now().plusMonths(6), true);

        userRepository.save(user);

        Assertions.assertDoesNotThrow(() -> userService.deleteUser(1L));
    }

    @Test
    void findUserByIdTest() {
        User user = new User(1L, "tulip@ya.ru", "Melissa", "Trevelyan",
                "+78999999", LocalDateTime.now(), LocalDateTime.now().plusMonths(6), true);
        UserOutDto expected = new UserOutDto(user.getId(), "Melissa", "Trevelyan", true);

        userRepository.save(user);
        UserOutDto result = userService.findUserById(1L);

        assertEquals(expected, result);
    }
}
