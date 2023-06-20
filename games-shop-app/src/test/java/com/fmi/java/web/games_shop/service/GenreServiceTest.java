package com.fmi.java.web.games_shop.service;

import com.fmi.java.web.games_shop.dto.GenreDto;
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

class GenreServiceTest {
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
        List<GenreDto> genresList = genreService.getAllGenres();

        assertThat(genresList).extracting(GenreDto::name).contains("ACTION", "RACING", "ADVENTURE");
    }

    @Test
    public void shouldAddGenre() {
        Genre entityGenre = new Genre("HORROR");
        GenreDto dtoGenre = new GenreDto("HORROR");

        when(genreRepository.existsById(dtoGenre.name())).thenReturn(false);
        when(genreRepository.save(entityGenre)).thenReturn(entityGenre);
        GenreDto result = genreService.addGenre(dtoGenre);

        verify(genreRepository).save(entityGenre);
        assertEquals(entityGenre.getName(), result.name());
    }

    @Test
    public void addGenreShouldThrowException() {
        GenreDto dtoGenre = new GenreDto("OPEN-WORLD");

        when(genreRepository.existsById(dtoGenre.name())).thenReturn(true);

        EntityExistsException exception = assertThrows(EntityExistsException.class, () -> genreService.addGenre(dtoGenre));
        assertEquals(exception.getMessage(), String.format("Genre \"%s\" already exists.", dtoGenre.name()));
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

        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> genreService.deleteGenre(genreToDelete.getName()));
        assertEquals(exception.getMessage(), String.format("Genre " +
                "with name \"%s\" does not exist.", genreToDelete.getName()));
    }
}