package com.example.pruebasaval.pruebaversion2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.pruebasaval.pruebaversion2.model.Episode;
import com.example.pruebasaval.pruebaversion2.services.EpisodeService;

@RestController
@RequestMapping("/episodes")
public class EpisodeController {
    private final EpisodeService episodeService;

    @Autowired
    public EpisodeController(EpisodeService episodeService) {
        this.episodeService = episodeService;
    }

    @GetMapping("/{id}")
    public Episode getEpisodeById(@PathVariable("id") Long id) {
        return episodeService.getEpisodeById(id);
    }
}
