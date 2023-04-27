package com.fmi.java.web.games_shop.controller;

import com.fmi.java.web.games_shop.dto.GameDto;
import com.fmi.java.web.games_shop.model.Game;
import com.fmi.java.web.games_shop.model.Genre;
import com.fmi.java.web.games_shop.model.Platform;
import com.fmi.java.web.games_shop.model.Publisher;
import com.fmi.java.web.games_shop.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/games-shop/games")
public class GameController {
    private final GameService gameService;

    @Autowired
    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping
    List<GameDto> getAllGames() {
        return gameService.getAllGames().stream().map(this::entityToDto).toList();
    }

    @GetMapping("/{name}")
    public GameDto getGameById(@PathVariable("name") String name) {
        return entityToDto(gameService.getGameById(name));
    }

    @PostMapping
    @ResponseBody
    public GameDto createCar(@RequestBody GameDto gameDto) {
        gameService.addGame(dtoToEntity(gameDto));
        return gameDto;
    }
    private Game dtoToEntity(GameDto gameDto) {
        Platform platform = new Platform(gameDto.platform());
        Publisher publisher = gameService.getPublisherByName(gameDto.publisher());
        Set<Genre> genres = gameDto.genres().stream().map(Genre::new).collect(Collectors.toSet());
        return new Game(gameDto.name(), gameDto.releaseDate(), gameDto.price(), platform, gameDto.description(), gameDto.picture(), publisher, genres);
    }

    @DeleteMapping("/{name}")
    @ResponseBody
    public GameDto deleteCar(@PathVariable String name) {
        Game game = gameService.deleteGame(name);
        return entityToDto(game);
    }

    private GameDto entityToDto(Game game) {
        return new GameDto(game.getName(),  game.getPrice(), game.getPlatform().getName(), game.getGenres().stream().map(Genre::getName).collect(Collectors.toSet()), game.getDescription(),game.getReleaseDate(), game.getPublisher().getName(), game.getPicture());
    }
}
