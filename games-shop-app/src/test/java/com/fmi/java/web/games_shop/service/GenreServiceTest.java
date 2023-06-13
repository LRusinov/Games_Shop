package com.fmi.java.web.games_shop.service;

import com.fmi.java.web.games_shop.exception.EntityExistsException;
import com.fmi.java.web.games_shop.exception.EntityNotFoundException;
import com.fmi.java.web.games_shop.model.Genre;
import com.fmi.java.web.games_shop.repository.GenreRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public final class GenreServiceTest {
    @Mock
    private static GenreRepository genreRepository;

    private static GenreService genreService;

    private static Map<String, Genre> genres;

    @BeforeAll
    public static void setUp() {
        Genre action = new Genre("ACTION");
        Genre racing = new Genre("RACING");
        Genre adventure = new Genre("ADVENTURE");
        Genre sport = new Genre("SPORT");
        genres = Map.of("ACTION", action, "RACING", racing, "ADVENTURE", adventure, "SPORT", sport);

        genreRepository = mock(GenreRepository.class);
        genreService = new GenreService(genreRepository);
    }

    @Test
    public void shouldGetAllGenres() {
        when(genreRepository.findAll()).thenReturn(List.of(genres.get("ACTION"), genres.get("RACING"), genres.get(
                "ADVENTURE")));
        List<Genre> genresList = genreService.getAllGenres();

        assertThat(genresList).extracting(Genre::getName).contains("ACTION", "RACING", "ADVENTURE");
    }

    @Test
    public void shouldAddGenre() {
        Genre newGenre = new Genre("HORROR");

        when(genreRepository.existsById(newGenre.getName())).thenReturn(false);
        when(genreRepository.save(newGenre)).thenReturn(newGenre);
        Genre result = genreService.addGenre(newGenre);

        verify(genreRepository).save(newGenre);
        assertEquals(result.getName(), newGenre.getName());
    }

    @Test
    public void addGenreShouldThrowException() {
        Genre newGenre = new Genre("OPEN-WORLD");
        when(genreRepository.existsById(newGenre.getName())).thenReturn(true);

        assertThrows(EntityExistsException.class, () -> genreService.addGenre(newGenre));
    }

    @Test
    public void shouldDeleteGenre() {
        Genre genreToDelete = genres.get("SPORT");
        when(genreRepository.findById(genreToDelete.getName())).thenReturn(Optional.of(genreToDelete));
        genreService.deleteGenre(genreToDelete.getName());

        verify(genreRepository, times(1)).delete(genreToDelete);
    }

    @Test
    public void deleteGenreShouldThrowException() {
        Genre genreToDelete = genres.get("SPORT");
        when(genreRepository.findById(genreToDelete.getName())).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> genreService.deleteGenre(genreToDelete.getName()));
    }
}