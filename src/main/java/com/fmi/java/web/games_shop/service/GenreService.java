package com.fmi.java.web.games_shop.service;

import com.fmi.java.web.games_shop.model.Genre;
import com.fmi.java.web.games_shop.repository.GenresRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenreService {
private final GenresRepository genresRepository;

    public GenreService(GenresRepository genresRepository) {
        this.genresRepository = genresRepository;
    }

    public List<Genre> getAllGenres() { return genresRepository.findAll();}
}
