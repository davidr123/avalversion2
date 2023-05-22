package com.example.pruebasaval.pruebaversion2.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.pruebasaval.pruebaversion2.model.Episode;

@Repository
public interface EpisodeRepository extends JpaRepository<Episode, Long> {
    List<Episode> findEpisodeById(Long id);
}