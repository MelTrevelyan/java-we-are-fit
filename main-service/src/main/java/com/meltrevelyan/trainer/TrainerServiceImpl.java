package com.meltrevelyan.trainer;

import com.meltrevelyan.exception.EntityNotFoundException;
import com.meltrevelyan.trainer.dto.TrainerInDto;
import com.meltrevelyan.trainer.dto.TrainerMapper;
import com.meltrevelyan.trainer.dto.TrainerOutDto;
import com.meltrevelyan.trainer.dto.TrainerUpdateDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class TrainerServiceImpl implements TrainerService {

    private final TrainerRepository trainerRepository;

    @Override
    @Transactional
    public TrainerOutDto addTrainer(TrainerInDto inDto) {
        Trainer trainer = TrainerMapper.toTrainer(inDto);
        trainer = trainerRepository.save(trainer);
        log.info("Trainer with id {} was created", trainer.getId());
        return null;
    }

    @Override
    public TrainerOutDto findTrainerById(Long trainerId) {
        Trainer trainer = findTrainer(trainerId);
        log.info("Trainer with id {} was found", trainerId);
        return TrainerMapper.toOutDto(trainer);
    }

    @Override
    public List<TrainerOutDto> findAllTrainers(Integer from, Integer size) {
        Pageable pageable = PageRequest.of(from / size, size);
        List<Trainer> trainers = trainerRepository.findAll(pageable).getContent();
        return trainers.stream()
                .map(TrainerMapper::toOutDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public TrainerOutDto updateTrainer(Long trainerId, TrainerUpdateDto updateDto) {
        Trainer trainer = findTrainer(trainerId);

        if (updateDto.getEmail() != null) {
            trainer.setEmail(updateDto.getEmail());
        }
        if (updateDto.getEducation() != null) {
            trainer.setEducation(updateDto.getEducation());
        }
        if (updateDto.getPhoneNumber() != null) {
            trainer.setPhoneNumber(updateDto.getPhoneNumber());
        }
        if (updateDto.getFirstName() != null) {
            trainer.setFirstName(updateDto.getFirstName());
        }
        if (updateDto.getLastName() != null) {
            trainer.setLastName(updateDto.getLastName());
        }
        if (updateDto.getEmploymentDate() != null) {
            trainer.setEmploymentDate(updateDto.getEmploymentDate());
        }
        if (updateDto.getSpecifications() != null) {
            trainer.setSpecifications(updateDto.getSpecifications());
        }

        trainerRepository.save(trainer);
        log.info("Trainer with 1d {} was updated", trainerId);
        return TrainerMapper.toOutDto(trainer);
    }

    @Override
    @Transactional
    public void deleteTrainer(Long trainerId) {
        if (!trainerRepository.existsById(trainerId)) {
            throw new EntityNotFoundException("Trainer with id " + trainerId + " does not exist");
        }
        trainerRepository.deleteById(trainerId);
        log.info("Trainer with id {} was deleted", trainerId);
    }

    @Override
    public List<TrainerOutDto> findTrainersBySpecification(String specification) {
        List<Trainer> trainers = trainerRepository.findBySpecificationsContainingIgnoreCase(specification);
        log.info("Found trainers by specification: {}", specification);
        return trainers.stream()
                .map(TrainerMapper::toOutDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<TrainerOutDto> findTrainersByEducation(String education) {
        List<Trainer> trainers = trainerRepository.findByEducationContainingIgnoreCase(education);
        log.info("Found trainers by education: {}", education);
        return trainers.stream()
                .map(TrainerMapper::toOutDto)
                .collect(Collectors.toList());
    }

    private Trainer findTrainer(Long trainerId) {
        return trainerRepository.findById(trainerId)
                .orElseThrow(() -> new EntityNotFoundException("Trainer with id " + trainerId + " was not found"));
    }
}
