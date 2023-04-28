package com.fmi.java.web.games_shop.service;

import com.fmi.java.web.games_shop.exception.EntityNotFoundException;
import com.fmi.java.web.games_shop.model.Game;
import com.fmi.java.web.games_shop.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameService {
    private final GameRepository gameRepository;

    @Autowired
    public GameService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    public List<Game> getAllGames() {
        return gameRepository.findAll();
    }

    public Game getGameById(String name) {
        return gameRepository.findById(name).orElseThrow(() -> new EntityNotFoundException(String.format("Game with name \"%s\" does not exist.", name)));
    }

    public Game addGame(Game newGame) {
        return gameRepository.save(newGame);
    }

    public Game deleteGame(String name) {
        Game gameToDelete = gameRepository.findById(name).orElseThrow(() -> new EntityNotFoundException(String.format("Game with name \"%s\" does not exist.", name)));
        gameRepository.delete(gameToDelete);
        return gameToDelete;
    }


}
