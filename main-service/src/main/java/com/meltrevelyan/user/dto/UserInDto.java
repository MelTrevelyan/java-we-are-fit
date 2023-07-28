package com.meltrevelyan.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserInDto {

    @NotEmpty
    @Email
    private String email;
    @NotEmpty
    @Size(min = 3, max = 50, message = "Length must be between 3 and 50 symbols")
    private String firstName;
    @NotEmpty
    @Size(min = 3, max = 100, message = "Length must be between 3 and 100 symbols")
    private String lastName;
    @NotEmpty
    private String phoneNumber;
    @NotNull
    @PastOrPresent
    private LocalDateTime membershipStart;
    @NotNull
    @FutureOrPresent
    private LocalDateTime membershipExpiration;
}
