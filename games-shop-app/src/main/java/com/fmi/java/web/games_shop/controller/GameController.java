package com.fmi.java.web.games_shop.controller;

import com.fmi.java.web.games_shop.dto.GameDto;
import com.fmi.java.web.games_shop.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/games-shop/games")
@CrossOrigin(origins = "http://localhost:4200")
public class GameController {
    private final GameService gameService;

    @Autowired
    public GameController(final GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping
    List<GameDto> getAllGames() {
        return gameService.getAllGames();
    }

    @GetMapping("/{name}")
    public ResponseEntity<GameDto> getGameById(@PathVariable("name") final String name) {
        return new ResponseEntity<>(gameService.getGameById(name), HttpStatus.OK);
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<GameDto> createGame(@RequestBody final GameDto gameDto) {
        return new ResponseEntity<>(gameService.addGame(gameDto), HttpStatus.CREATED);
    }

    @DeleteMapping("/{name}")
    @ResponseBody
    public ResponseEntity<Boolean> deleteGame(@PathVariable final String name) {
        gameService.deleteGame(name);
        return new ResponseEntity<>(Boolean.TRUE, HttpStatus.ACCEPTED);
    }

    @PutMapping("/{name}")
    @ResponseBody
    public ResponseEntity<GameDto> updateGame(@PathVariable final String name, @RequestBody final GameDto gameDto) {
        return new ResponseEntity<>(gameService.updateGame(name, gameDto), HttpStatus.OK);
    }
}
