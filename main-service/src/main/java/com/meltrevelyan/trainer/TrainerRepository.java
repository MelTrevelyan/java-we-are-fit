package com.meltrevelyan.trainer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrainerRepository extends JpaRepository<Trainer, Long> {

    List<Trainer> findBySpecificationsContainingIgnoreCase(String text);

    List<Trainer> findByEducationContainingIgnoreCase(String text);
}
