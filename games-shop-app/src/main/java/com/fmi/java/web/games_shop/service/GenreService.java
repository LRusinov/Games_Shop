package com.fmi.java.web.games_shop.service;

import com.fmi.java.web.games_shop.exception.EntityExistsException;
import com.fmi.java.web.games_shop.exception.EntityNotFoundException;
import com.fmi.java.web.games_shop.model.Genre;
import com.fmi.java.web.games_shop.repository.GenreRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenreService {
    private final GenreRepository genreRepository;

    public GenreService(final GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    public List<Genre> getAllGenres() {
        return genreRepository.findAll();
    }

    public Genre addGenre(final Genre newGenre) {
        String genreName = newGenre.getName();
        if (genreRepository.existsById(genreName)) {
            throw new EntityExistsException(String.format("Genre \"%s\" already exists.", genreName));
        } else {
            return genreRepository.save(newGenre);
        }
    }

    public void deleteGenre(final String name) {
        final Genre genreToDelete =
                genreRepository.findById(name).orElseThrow(() -> new EntityNotFoundException(String.format("Genre " +
                        "with name \"%s\" does not exist.", name)));
        genreRepository.delete(genreToDelete);
    }
}
