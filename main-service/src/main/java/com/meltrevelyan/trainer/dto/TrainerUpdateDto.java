package com.meltrevelyan.trainer.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

import static com.meltrevelyan.constant.Constant.TIME_FORMAT;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TrainerUpdateDto {

    private Long id;
    private String email;
    private String phoneNumber;
    @Size(min = 3, max = 50, message = "Length must be between 3 and 50 symbols")
    private String firstName;
    @Size(min = 3, max = 50, message = "Length must be between 3 and 50 symbols")
    private String lastName;
    @PastOrPresent
    @JsonFormat(pattern = TIME_FORMAT)
    private LocalDateTime employmentDate;
    private String education;
    private String specifications;
}
