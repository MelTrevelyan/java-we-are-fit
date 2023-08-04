package com.meltrevelyan.trainer;

import com.meltrevelyan.trainer.dto.TrainerInDto;
import com.meltrevelyan.trainer.dto.TrainerOutDto;
import com.meltrevelyan.trainer.dto.TrainerUpdateDto;

import java.util.List;

public interface TrainerService {

    TrainerOutDto addTrainer(TrainerInDto inDto);

    TrainerOutDto findTrainerById(Long trainerId);

    List<TrainerOutDto> findAllTrainers(Integer from, Integer size);

    TrainerOutDto updateTrainer(Long trainerId, TrainerUpdateDto updateDto);

    void deleteTrainer(Long trainerId);

    List<TrainerOutDto> findTrainersBySpecification(String specification);

    List<TrainerOutDto> findTrainersByEducation(String education);
}
