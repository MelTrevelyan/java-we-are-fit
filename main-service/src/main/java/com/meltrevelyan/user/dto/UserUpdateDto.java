package com.meltrevelyan.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserUpdateDto {

    @Email
    private String email;
    @Size(min = 3, max = 50, message = "Length must be between 3 and 50 symbols")
    private String firstName;
    @Size(min = 3, max = 100, message = "Length must be between 3 and 100 symbols")
    private String lastName;
    private String phoneNumber;
    @PastOrPresent
    private LocalDateTime membershipStart;
    @FutureOrPresent
    private LocalDateTime membershipExpiration;
}
