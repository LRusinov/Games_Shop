package com.fmi.java.web.games_shop.controller;

import com.fmi.java.web.games_shop.dto.GenreDto;
import com.fmi.java.web.games_shop.model.Genre;
import com.fmi.java.web.games_shop.service.GenreService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/games-shop/genres")
@CrossOrigin(origins = "http://localhost:4200")
public class GenreController {
    private final GenreService genreService;

    public GenreController(final GenreService genreService) {
        this.genreService = genreService;
    }

    @GetMapping
    List<GenreDto> getAllGenres() {
        return genreService.getAllGenres().stream().map(genre -> new GenreDto(genre.getName())).toList();
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<Genre> createGenre(@RequestBody final GenreDto genreDto) {
        final Genre newGenre = genreService.addGenre(new Genre(genreDto.name()));
        return new ResponseEntity<>(newGenre, HttpStatus.CREATED);
    }

    @DeleteMapping("/{name}")
    @ResponseBody
    public ResponseEntity<Boolean> deleteGenre(@PathVariable final String name) {
        genreService.deleteGenre(name);
        return new ResponseEntity<>(Boolean.TRUE, HttpStatus.ACCEPTED);
    }
}
