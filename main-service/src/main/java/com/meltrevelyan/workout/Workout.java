package com.meltrevelyan.workout;

import com.meltrevelyan.trainer.Trainer;
import com.meltrevelyan.user.User;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

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
    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "trainer_id", nullable = false)
    private Trainer trainer;
    @ToString.Exclude
    @ManyToMany
    @JoinTable(name = "workout_visitors",
    joinColumns = @JoinColumn(name = "visitor_id"),
    inverseJoinColumns = @JoinColumn(name = "workout_id"))
    private List<User> visitors;
}
