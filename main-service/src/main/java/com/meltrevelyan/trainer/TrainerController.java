package com.meltrevelyan.trainer;

import com.meltrevelyan.trainer.dto.TrainerInDto;
import com.meltrevelyan.trainer.dto.TrainerOutDto;
import com.meltrevelyan.trainer.dto.TrainerUpdateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Validated
@RestController
@RequestMapping("/trainers")
@RequiredArgsConstructor
public class TrainerController {

    private final TrainerService trainerService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TrainerOutDto addTrainer(@RequestBody @Valid TrainerInDto inDto) {
        return trainerService.addTrainer(inDto);
    }

    @GetMapping("/{trainerId}")
    public TrainerOutDto findTrainerById(@PathVariable Long trainerId) {
        return trainerService.findTrainerById(trainerId);
    }

    @GetMapping
    public List<TrainerOutDto> findAllTrainers(Integer from, Integer size) {
        return trainerService.findAllTrainers(from, size);
    }

    @PatchMapping
    public TrainerOutDto updateTrainer(Long trainerId, TrainerUpdateDto updateDto) {
        return trainerService.updateTrainer(trainerId, updateDto);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTrainer(Long trainerId) {
        trainerService.deleteTrainer(trainerId);
    }

    @GetMapping("/search/specification")
    public List<TrainerOutDto> findTrainersBySpecification(@NotEmpty @RequestParam String specification) {
        return trainerService.findTrainersBySpecification(specification);
    }

    @GetMapping("/search/education")
    public List<TrainerOutDto> findTrainersByEducation(@NotEmpty @RequestParam String education) {
        return trainerService.findTrainersByEducation(education);
    }
}
