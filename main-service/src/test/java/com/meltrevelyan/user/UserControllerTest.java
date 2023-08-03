package com.meltrevelyan.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.meltrevelyan.user.dto.UserInDto;
import com.meltrevelyan.user.dto.UserOutDto;
import com.meltrevelyan.user.dto.UserUpdateDto;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private UserService userService;

    private final UserInDto inDto = new UserInDto("test@ya.ru", "Melissa", "Trevelyan",
            "+79999999", LocalDateTime.now(), LocalDateTime.now().plusMonths(6));

    private final UserOutDto outDto = new UserOutDto(1L, "Melissa", "Trevelyan", true);


    @Test
    @SneakyThrows
    void addUserTest() {
        when(userService.addUser(any(UserInDto.class))).thenReturn(outDto);

        String result = mockMvc.perform(post("/users")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(inDto)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString();

        assertEquals(objectMapper.writeValueAsString(outDto), result);
    }

    @Test
    @SneakyThrows
    void updateUserTest() {
        long userId = 1L;
        UserUpdateDto updateDto = new UserUpdateDto();
        updateDto.setFirstName("Eve");
        UserOutDto expected = outDto;
        expected.setFirstName("Eve");

        when(userService.updateUser(updateDto, 1L)).thenReturn(outDto);

        String result = mockMvc.perform(patch("/users/{userId}", userId)
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(updateDto)))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        assertEquals(objectMapper.writeValueAsString(expected), result);
    }

    @Test
    @SneakyThrows
    void deleteUserTest() {
        long userId = 1L;

        mockMvc.perform(delete("/users/{userId}", userId))
                .andExpect(status().isNoContent());

        verify(userService).deleteUser(userId);
    }

    @Test
    @SneakyThrows
    void findUserByIdTest() {
        long userId = 1L;

        when(userService.findUserById(userId)).thenReturn(outDto);

        String result = mockMvc.perform((get("/users/{userId}", userId)))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        assertEquals(objectMapper.writeValueAsString(outDto), result);
    }
}
