package com.fmi.java.web.games_shop.controller;

import com.fmi.java.web.games_shop.model.Genre;
import com.fmi.java.web.games_shop.service.GenreService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/games-shop/genres")
@CrossOrigin(origins = "http://localhost:4200")
public class GenreController {
    private final GenreService genreService;

    public GenreController(GenreService genreService) {
        this.genreService = genreService;
    }

    @GetMapping
    List<String> getAllGenres() {
        return genreService.getAllGenres().stream().map(Genre::getName).toList();
    }
}
