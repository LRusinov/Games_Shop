package com.fmi.java.web.games_shop.service;

import com.fmi.java.web.games_shop.dto.GameDto;
import com.fmi.java.web.games_shop.exception.EntityExistsException;
import com.fmi.java.web.games_shop.exception.EntityNotFoundException;
import com.fmi.java.web.games_shop.model.Game;
import com.fmi.java.web.games_shop.model.Genre;
import com.fmi.java.web.games_shop.model.Platform;
import com.fmi.java.web.games_shop.model.Publisher;
import com.fmi.java.web.games_shop.repository.GameRepository;
import com.fmi.java.web.games_shop.repository.GenreRepository;
import com.fmi.java.web.games_shop.repository.PlatformRepository;
import com.fmi.java.web.games_shop.repository.PublisherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class GameService {
    private final GameRepository gameRepository;

    private final PlatformRepository platformRepository;

    private final GenreRepository genreRepository;

    private final PublisherRepository publisherRepository;

    private static final String EXCEPTION_MESSAGE = "Game with name \"%s\" does not exist.";

    public List<GameDto> getAllGames() {
        return gameRepository.findAll().stream().map(this::entityToDto).toList();
    }

    public GameDto getGameById(final String name) {
        Game foundGame = gameRepository.findById(name).orElseThrow(() -> new EntityNotFoundException(String.format(EXCEPTION_MESSAGE, name)));
        return entityToDto(foundGame);
    }

    public GameDto addGame(final GameDto newGame) {
        String gameName = newGame.name();
        if (gameRepository.existsById(gameName)) {
            throw new EntityExistsException(String.format("Game with name \"%s\" already exists.", gameName));
        }
        return entityToDto(gameRepository.save(dtoToEntity(newGame)));
    }

    public void deleteGame(final String name) {
        final Game gameToDelete =
                gameRepository.findById(name).orElseThrow(() -> new EntityNotFoundException(String.format(EXCEPTION_MESSAGE, name)));
        gameRepository.delete(gameToDelete);
    }

    public GameDto updateGame(final String gameName, final GameDto updatedGame) {
        return gameRepository.findById(gameName).map(game -> {
            game.setDescription(updatedGame.description());
            game.setGenres(updatedGame.genres().stream().map(this::findGenreByName).collect(Collectors.toSet()));
            game.setPlatforms(updatedGame.platforms().stream().map(this::findPlatformByName).collect(Collectors.toSet()));
            game.setPublisher(findPublisherByName(updatedGame.publisher()));
            game.setPictureUrl(updatedGame.pictureUrl());
            game.setReleaseDate(updatedGame.releaseDate());
            game.setPrice(updatedGame.price());
            return entityToDto(gameRepository.save(game));
        }).orElseGet(() -> {
            Game savedGame = gameRepository.save(dtoToEntity(updatedGame));
            return entityToDto(savedGame);
        });

    }

    public GameDto entityToDto(final Game game) {
        final Set<String> platforms = game.getPlatforms().stream().map(Platform::getName).collect(Collectors.toSet());
        final Set<String> genres = game.getGenres().stream().map(Genre::getName).collect(Collectors.toSet());

        return new GameDto(game.getName(), game.getPrice(), genres, platforms, game.getDescription(),
                game.getReleaseDate(), game.getPublisherName(), game.getPictureUrl());
    }

    public Game dtoToEntity(final GameDto gameDto) {
        final Publisher publisher = findPublisherByName(gameDto.publisher());
        final Set<Genre> genres = gameDto.genres().stream().map(this::findGenreByName).collect(Collectors.toSet());
        final Set<Platform> platforms = gameDto.platforms().stream().map(this::findPlatformByName).collect(Collectors.toSet());

        return new Game(gameDto.name(), gameDto.releaseDate(), gameDto.price(), platforms, gameDto.description(),
                gameDto.pictureUrl(), publisher, genres);
    }

    private Genre findGenreByName(String genreName) {
        return genreRepository.findById(genreName).orElseThrow(() -> new EntityNotFoundException(String.format("Genre " +
                "with name \"%s\" does not exist.", genreName)));
    }

    private Platform findPlatformByName(String platformName) {
        return platformRepository.findById(platformName)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Platform with name \"%s\" " +
                        "does not exist.", platformName)));
    }

    private Publisher findPublisherByName(String publisherName) {
        return publisherRepository.findByname(publisherName)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Publisher with name \"%s\" " + "does not exist.", publisherName)));
    }
}
