package com.meltrevelyan.user;

import com.meltrevelyan.workout.Workout;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "phone_number")
    private String phoneNumber;
    @Column(name = "membership_start")
    private LocalDateTime membershipStart;
    @Column(name = "membership_expiration")
    private LocalDateTime membershipExpiration;
    @Column(name = "is_active")
    private Boolean isActive;
    @ToString.Exclude
    @ManyToMany(mappedBy = "visitors")
    private List<Workout> workouts;
}
