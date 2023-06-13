package com.fmi.java.web.games_shop.service;

import com.fmi.java.web.games_shop.exception.EntityExistsException;
import com.fmi.java.web.games_shop.exception.EntityNotFoundException;
import com.fmi.java.web.games_shop.model.Game;
import com.fmi.java.web.games_shop.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GameService {
    private final GameRepository gameRepository;

    @Autowired
    public GameService(final GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    public List<Game> getAllGames() {
        return gameRepository.findAll();
    }

    public Game getGameById(final String name) {
        return gameRepository.findById(name).orElseThrow(() -> new EntityNotFoundException(String.format("Game with " +
                "name \"%s\" does not exist.", name)));
    }

    public Game addGame(final Game newGame) {
        String gameName = newGame.getName();
        Optional<Game> result = gameRepository.findById(gameName);
        if (result.isPresent() && result.get().getPlatformName().equals(newGame.getPlatformName())) {
            throw new EntityExistsException(String.format("Game: \"%s\" for console \"%s\" already exists.", gameName
                    , newGame.getPlatformName()));
        }
        return gameRepository.save(newGame);
    }

    public void deleteGame(final String name) {
        final Game gameToDelete =
                gameRepository.findById(name).orElseThrow(() -> new EntityNotFoundException(String.format("Game with " +
                        "name \"%s\" does not exist.", name)));
        gameRepository.delete(gameToDelete);
    }

    public Game updateGame(final Game updatedGame) {
        return gameRepository.findById(updatedGame.getName()).map(game -> {
            game.setDescription(updatedGame.getDescription());
            game.setGenres(updatedGame.getGenres());
            game.setPlatform(updatedGame.getPlatform());
            game.setPublisher(updatedGame.getPublisher());
            game.setPictureUrl(updatedGame.getPictureUrl());
            game.setReleaseDate(updatedGame.getReleaseDate());
            game.setPrice(updatedGame.getPrice());
            return gameRepository.save(game);
        }).orElseGet(() -> gameRepository.save(updatedGame));
    }


}
