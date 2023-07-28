package com.meltrevelyan.workout;

import com.meltrevelyan.trainer.Trainer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "workouts")
public class Workout {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    @Column(name = "place_info")
    private String placeInfo;
    private LocalDateTime date;
    @Column(name = "is_paid")
    private Boolean isPaid;
    @Enumerated(value = EnumType.STRING)
    private WorkoutStatus status;
    @ManyToOne
    @JoinColumn(name = "trainer_id", nullable = false)
    private Trainer trainer;
//    private List<User> visitors;
}
