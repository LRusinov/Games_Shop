package com.fmi.java.web.games_shop.service;

import com.fmi.java.web.games_shop.exception.EntityNotFoundException;
import com.fmi.java.web.games_shop.model.Game;
import com.fmi.java.web.games_shop.model.Genre;
import com.fmi.java.web.games_shop.model.Platform;
import com.fmi.java.web.games_shop.model.Publisher;
import com.fmi.java.web.games_shop.repository.GameRepository;
import com.fmi.java.web.games_shop.repository.GenresRepository;
import com.fmi.java.web.games_shop.repository.PlatformRepository;
import com.fmi.java.web.games_shop.repository.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameService {
    private final GameRepository gameRepository;
    private final PublisherRepository publisherRepository;

    private final PlatformRepository platformRepository;

    private final GenresRepository genresRepository;
    @Autowired
    public GameService(GameRepository gameRepository, PublisherRepository publisherRepository, PlatformRepository platformRepository,GenresRepository genresRepository) {
        this.gameRepository = gameRepository;
        this.publisherRepository = publisherRepository;
        this.platformRepository=platformRepository;
        this.genresRepository = genresRepository;
    }

    public List<Game> getAllGames(){
        return  gameRepository.findAll();
    }

    public List<Publisher> getAllPublishers(){return publisherRepository.findAll();}

    public List<Platform> getAllPlatforms() { return platformRepository.findAll();}

    public List<Genre> getAllGenres() { return genresRepository.findAll();}
    public Game getGameById(String name){
        return  gameRepository.findById(name).orElseThrow(()-> new EntityNotFoundException(String.format("Game with name \"%s\" does not exist.",name)));
    }

    public Publisher getPublisherByName(String name){
        return publisherRepository.findByname(name);
    }

    public Game addGame(Game newGame){
       return gameRepository.save(newGame);
    }

    public Game deleteGame(String name){
        Game gameToDelete = gameRepository.findById(name).orElseThrow(()-> new EntityNotFoundException(String.format("Game with name \"%s\" does not exist.",name)));
        gameRepository.delete(gameToDelete);
        return gameToDelete;
    }


}
