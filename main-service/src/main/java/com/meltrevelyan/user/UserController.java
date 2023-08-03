package com.meltrevelyan.user;

import com.meltrevelyan.user.dto.UserInDto;
import com.meltrevelyan.user.dto.UserOutDto;
import com.meltrevelyan.user.dto.UserUpdateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserOutDto addUser(@RequestBody @Valid UserInDto userInDto) {
        return userService.addUser(userInDto);
    }

    @PatchMapping(value = "/{userId}")
    public UserOutDto updateUser(@RequestBody @Valid UserUpdateDto updateDto, @PathVariable Long userId) {
        return userService.updateUser(updateDto, userId);
    }

    @DeleteMapping(value = "/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
    }

    @GetMapping(value = "/{userId}")
    public UserOutDto findUserById(@PathVariable Long userId) {
        return userService.findUserById(userId);
    }
}
