package com.example.pruebasaval.pruebaversion2.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.example.pruebasaval.pruebaversion2.services.EpisodeService;
@Component
public class EpisodeCleanupTask {
    
    private final EpisodeService episodeService;

    @Autowired
    public EpisodeCleanupTask(EpisodeService episodeService) {
        this.episodeService = episodeService;
    }

    @Scheduled(fixedRate = 300000) // Ejecutar cada 5 minutos
    public void cleanupEpisodes() {
        episodeService.cleanupEpisodes();
        System.out.println("Tarea programada completada");
    }
    
}
