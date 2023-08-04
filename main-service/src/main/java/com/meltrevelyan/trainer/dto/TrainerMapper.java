package com.meltrevelyan.trainer.dto;

import com.meltrevelyan.trainer.Trainer;
import lombok.experimental.UtilityClass;

@UtilityClass
public class TrainerMapper {

    public static Trainer toTrainer(TrainerInDto inDto) {
        return Trainer.builder()
                .email(inDto.getEmail())
                .phoneNumber(inDto.getPhoneNumber())
                .education(inDto.getEducation())
                .specifications(inDto.getSpecifications())
                .firstName(inDto.getFirstName())
                .lastName(inDto.getLastName())
                .employmentDate(inDto.getEmploymentDate())
                .build();
    }

    public static TrainerOutDto toOutDto(Trainer trainer) {
        return TrainerOutDto.builder()
                .id(trainer.getId())
                .firstName(trainer.getFirstName())
                .lastName(trainer.getLastName())
                .education(trainer.getEducation())
                .specifications(trainer.getSpecifications())
                .build();
    }
}
