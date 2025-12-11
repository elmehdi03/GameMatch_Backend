package com.example.backend_gamematch.model;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(
        name = "matches",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"user1_id", "user2_id"})
        }
)
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user1_id")
    private User user1;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user2_id")
    private User user2;

    @Builder.Default
    private LocalDateTime matchedAt = LocalDateTime.now();
}
