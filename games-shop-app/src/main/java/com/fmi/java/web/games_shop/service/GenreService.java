package com.fmi.java.web.games_shop.service;

import com.fmi.java.web.games_shop.dto.GenreDto;
import com.fmi.java.web.games_shop.exception.EntityExistsException;
import com.fmi.java.web.games_shop.exception.EntityNotFoundException;
import com.fmi.java.web.games_shop.model.Genre;
import com.fmi.java.web.games_shop.repository.GenreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class GenreService {
    private final GenreRepository genreRepository;

    public List<GenreDto> getAllGenres() {
        return genreRepository.findAll().stream().map(this::entityToDto).toList();
    }

    public GenreDto addGenre(final GenreDto newGenre) {
        String genreName = newGenre.name();
        if (genreRepository.existsById(genreName)) {
            throw new EntityExistsException(String.format("Genre \"%s\" already exists.", genreName));
        } else {
            return entityToDto(genreRepository.save(dtoToEntity(newGenre)));
        }
    }

    public void deleteGenre(final String name) {
        final Genre genreToDelete =
                genreRepository.findById(name).orElseThrow(() -> new EntityNotFoundException(String.format("Genre " +
                        "with name \"%s\" does not exist.", name)));
        genreRepository.delete(genreToDelete);
    }

    private Genre dtoToEntity(GenreDto genreDto) {
        return new Genre(genreDto.name());
    }

    private GenreDto entityToDto(Genre genre) {
        return new GenreDto(genre.getName());
    }
}
