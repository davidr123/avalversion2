package com.example.pruebasaval.pruebaversion2.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import org.springframework.web.client.RestTemplate;

import com.example.pruebasaval.pruebaversion2.model.Episode;
import com.example.pruebasaval.pruebaversion2.repository.EpisodeRepository;

import jakarta.transaction.Transactional;

@Service
public class EpisodeService {
    private final EpisodeRepository episodeRepository;
    private final RestTemplate restTemplate;

    @Autowired
    public EpisodeService(EpisodeRepository episodeRepository, RestTemplate restTemplate) {
        this.episodeRepository = episodeRepository;
        this.restTemplate = restTemplate;
    }

    public Episode getEpisodeById(Long id) {
        Episode episode = episodeRepository.findById(id).orElse(null);
        if (episode != null) {
            episode.setConsultedFromDatabase(true);
            episode.incrementConsultationCount();
            episodeRepository.save(episode);
            return episode;
        } else {
            // Lógica para consultar el episodio desde el servicio externo y guardarlo en la base de datos
            String externalApiUrl = "https://rickandmortyapi.com/api/episode/" + id;
            ResponseEntity<Episode> response = restTemplate.getForEntity(externalApiUrl, Episode.class);
            Episode newEpisode = response.getBody();

            if (newEpisode != null) {
                newEpisode.setConsultedFromDatabase(false);
                newEpisode.setConsultationCount(1);
                episodeRepository.save(newEpisode);
                return newEpisode;
            } else {
                return null;
            }
        }
    }

    @Transactional
    public void cleanupEpisodes() {
        LocalDateTime threshold = LocalDateTime.now().minusMinutes(5);
        List<Episode> episodesToDelete = episodeRepository.findAll()
            .stream()
            .filter(episode -> episode.getAirDate().isBefore(threshold))
            .collect(Collectors.toList());
        episodeRepository.deleteAll(episodesToDelete);
    }
}
