package com.example.backend_gamematch.repository;

import com.example.backend_gamematch.model.Message;
import com.example.backend_gamematch.model.Match;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findByMatchOrderBySentAtAsc(Match match);
}
