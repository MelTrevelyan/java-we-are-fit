package com.meltrevelyan.user.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.time.LocalDateTime;

import static com.meltrevelyan.constant.Constant.TIME_FORMAT;

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
    @JsonFormat(pattern = TIME_FORMAT)
    private LocalDateTime membershipStart;
    @NotNull
    @FutureOrPresent
    @JsonFormat(pattern = TIME_FORMAT)
    private LocalDateTime membershipExpiration;
}
