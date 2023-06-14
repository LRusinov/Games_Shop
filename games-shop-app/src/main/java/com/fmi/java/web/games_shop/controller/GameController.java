package com.fmi.java.web.games_shop.controller;

import com.fmi.java.web.games_shop.dto.GameDto;
import com.fmi.java.web.games_shop.model.Game;
import com.fmi.java.web.games_shop.model.Genre;
import com.fmi.java.web.games_shop.model.Platform;
import com.fmi.java.web.games_shop.model.Publisher;
import com.fmi.java.web.games_shop.service.GameService;
import com.fmi.java.web.games_shop.service.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/games-shop/games")
@CrossOrigin(origins = "http://localhost:4200")
public class GameController {
    private final GameService gameService;

    private final PublisherService publisherService;

    @Autowired
    public GameController(final GameService gameService, final PublisherService publisherService) {
        this.publisherService = publisherService;
        this.gameService = gameService;
    }

    @GetMapping
    List<GameDto> getAllGames() {
        return gameService.getAllGames().stream().map(this::entityToDto).toList();
    }

    @GetMapping("/{name}")
    public GameDto getGameById(@PathVariable("name") final String name) {
        return entityToDto(gameService.getGameById(name));
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<Game> createGame(@RequestBody final GameDto gameDto) {
        final Game newGame = gameService.addGame(dtoToEntity(gameDto));
        return new ResponseEntity<>(newGame, HttpStatus.CREATED);
    }

    @DeleteMapping("/{name}")
    @ResponseBody
    public ResponseEntity<Boolean> deleteGame(@PathVariable final String name) {
        gameService.deleteGame(name);
        return new ResponseEntity<>(Boolean.TRUE, HttpStatus.ACCEPTED);
    }

    @PutMapping("/{name}")
    @ResponseBody
    public Game updateGame(@PathVariable final String name, @RequestBody final GameDto gameDto) {
        return gameService.updateGame(name, dtoToEntity(gameDto));
    }

    private GameDto entityToDto(final Game game) {
        final Set<String> platforms = game.getPlatforms().stream().map(Platform::getName).collect(Collectors.toSet());
        final Set<String> genres = game.getGenres().stream().map(Genre::getName).collect(Collectors.toSet());
        return new GameDto(game.getName(), game.getPrice(), platforms, genres, game.getDescription(),
                game.getReleaseDate(), game.getPublisherName(), game.getPictureUrl());
    }

    private Game dtoToEntity(final GameDto gameDto) {
        final Publisher publisher = publisherService.getPublisherByName(gameDto.publisher());
        final Set<Genre> genres = gameDto.genres().stream().map(Genre::new).collect(Collectors.toSet());
        final Set<Platform> platforms = gameDto.platforms().stream().map(Platform::new).collect(Collectors.toSet());
        return new Game(gameDto.name(), gameDto.releaseDate(), gameDto.price(), platforms, gameDto.description(),
                gameDto.picture(), publisher, genres);
    }
}
