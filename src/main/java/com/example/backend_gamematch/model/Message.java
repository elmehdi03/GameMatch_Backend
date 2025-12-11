package com.example.backend_gamematch.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "messages")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private Match match;

    @ManyToOne(optional = false)
    private User sender;

    @Column(nullable = false, length = 1000)
    private String content;

    @Builder.Default
    private LocalDateTime sentAt = LocalDateTime.now();
}
