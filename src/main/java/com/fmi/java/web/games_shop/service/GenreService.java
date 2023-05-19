package com.fmi.java.web.games_shop.service;

import com.fmi.java.web.games_shop.exception.EntityNotFoundException;
import com.fmi.java.web.games_shop.model.Genre;
import com.fmi.java.web.games_shop.repository.GenresRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenreService {
    private final GenresRepository genresRepository;

    public GenreService(final GenresRepository genresRepository) {
        this.genresRepository = genresRepository;
    }

    public List<Genre> getAllGenres() {
        return genresRepository.findAll();
    }

    public Genre addGenre(final Genre genre) {
        return genresRepository.save(genre);
    }

    public void deleteGenre(final String name) {
        final Genre genreToDelete = genresRepository.findById(name).orElseThrow(() -> new EntityNotFoundException(String.format("Genre with name \"%s\" does not exist.", name)));
        genresRepository.delete(genreToDelete);
    }
}
