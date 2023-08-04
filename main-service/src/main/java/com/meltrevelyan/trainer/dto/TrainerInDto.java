package com.meltrevelyan.trainer.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.time.LocalDateTime;

import static com.meltrevelyan.constant.Constant.TIME_FORMAT;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TrainerInDto {

    @Email
    @NotNull
    private String email;
    @NotEmpty
    private String phoneNumber;
    @NotEmpty
    @Size(min = 3, max = 50, message = "Length must be between 3 and 50 symbols")
    private String firstName;
    @NotEmpty
    @Size(min = 3, max = 50, message = "Length must be between 3 and 50 symbols")
    private String lastName;
    @NotNull
    @PastOrPresent
    @JsonFormat(pattern = TIME_FORMAT)
    private LocalDateTime employmentDate;
    @NotEmpty
    private String education;
    @NotEmpty
    private String specifications;
}
