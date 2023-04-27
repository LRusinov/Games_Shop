package com.fmi.java.web.games_shop.controller;

import com.fmi.java.web.games_shop.dto.GameDto;
import com.fmi.java.web.games_shop.model.Game;
import com.fmi.java.web.games_shop.model.Genre;
import com.fmi.java.web.games_shop.model.Platform;
import com.fmi.java.web.games_shop.model.Publisher;
import com.fmi.java.web.games_shop.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/games-shop")
@CrossOrigin(origins = "http://localhost:4200")
public class GameController {
    private final GameService gameService;

    @Autowired
    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping("/games")
    List<GameDto> getAllGames() {
        return gameService.getAllGames().stream().map(this::entityToDto).toList();
    }

    @GetMapping("/publishers")
    List<String> getAllPublishers() {
        return gameService.getAllPublishers().stream().map(Publisher::getName).toList();
    }

    @GetMapping("/platforms")
    List<String> getAllPlatforms() {
        return gameService.getAllPlatforms().stream().map(Platform::getName).toList();
    }

    @GetMapping("/genres")
    List<String> getAllGenres() {
        return gameService.getAllGenres().stream().map(Genre::getName).toList();
    }

    @GetMapping("/{name}")
    public GameDto getGameById(@PathVariable("name") String name) {
        return entityToDto(gameService.getGameById(name));
    }

    @PostMapping("/games")
    @ResponseBody
    public ResponseEntity<Game> createCar(@RequestBody GameDto gameDto) {
        Game newGame = gameService.addGame(dtoToEntity(gameDto));
        return new ResponseEntity<>(newGame, HttpStatus.CREATED);
    }
    private Game dtoToEntity(GameDto gameDto) {
        Platform platform = new Platform(gameDto.platform());
        Publisher publisher = gameService.getPublisherByName(gameDto.publisher());
        Set<Genre> genres = gameDto.genres().stream().map(Genre::new).collect(Collectors.toSet());
        return new Game(gameDto.name(), gameDto.releaseDate(), gameDto.price(), platform, gameDto.description(), gameDto.picture(), publisher, genres);
    }

    @DeleteMapping("/games/{name}")
    @ResponseBody
    public GameDto deleteCar(@PathVariable String name) {
        Game game = gameService.deleteGame(name);
        return entityToDto(game);
    }

    private GameDto entityToDto(Game game) {
        return new GameDto(game.getName(),  game.getPrice(), game.getPlatform().getName(), game.getGenres().stream().map(Genre::getName).collect(Collectors.toSet()), game.getDescription(),game.getReleaseDate(), game.getPublisher().getName(), game.getPictureUrl());
    }
}
