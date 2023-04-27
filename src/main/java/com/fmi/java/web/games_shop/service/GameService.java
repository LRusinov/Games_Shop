package com.fmi.java.web.games_shop.service;

import com.fmi.java.web.games_shop.model.Game;
import com.fmi.java.web.games_shop.model.Publisher;
import com.fmi.java.web.games_shop.repository.GameRepository;
import com.fmi.java.web.games_shop.repository.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameService {
    private final GameRepository gameRepository;
    private final PublisherRepository publisherRepository;

    @Autowired
    public GameService(GameRepository gameRepository, PublisherRepository publisherRepository) {
        this.gameRepository = gameRepository;
        this.publisherRepository = publisherRepository;
    }

    public List<Game> getAllGames(){
        return  gameRepository.findAll();
    }

    public Game getGameById(String name){
        return  gameRepository.findById(name).orElseThrow(()-> new IllegalArgumentException("No game with this name found."));
    }

    public Publisher getPublisherByName(String name){
        return publisherRepository.findByname(name);
    }

    public Game addGame(Game newGame){
       return gameRepository.save(newGame);
    }

    public Game deleteGame(String name){
        Game gameToDelete = gameRepository.findById(name).orElseThrow(()-> new IllegalArgumentException("No game with this name found."));
        gameRepository.delete(gameToDelete);
        return gameToDelete;
    }
}
