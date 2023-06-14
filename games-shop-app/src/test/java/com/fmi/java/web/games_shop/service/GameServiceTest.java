package com.fmi.java.web.games_shop.service;

import com.fmi.java.web.games_shop.exception.EntityExistsException;
import com.fmi.java.web.games_shop.exception.EntityNotFoundException;
import com.fmi.java.web.games_shop.model.Game;
import com.fmi.java.web.games_shop.model.Genre;
import com.fmi.java.web.games_shop.model.Platform;
import com.fmi.java.web.games_shop.model.Publisher;
import com.fmi.java.web.games_shop.repository.GameRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.time.Instant;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.tuple;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public final class GameServiceTest {

    @Mock
    private static GameRepository gameRepository;

    private static GameService gameService;

    private static Map<String, Game> games;

    @BeforeAll
    public static void setUp() {
        Platform pc = new Platform("PC");
        Publisher valve = new Publisher(1L, "Valve", "LogoPictureUrl", 2000, "Publisher description");
        Publisher electronicArts = new Publisher(2L, "Electronic Arts", "LogoPictureUrl", 2000, "Publisher " +
                "description");
        Genre action = new Genre("ACTION");
        Genre racing = new Genre("RACING");
        Game counterStrike = new Game("Counter Strike 2.0", Instant.parse("2023-04-10T00:00:00Z"), 100.0, Set.of(pc),
                "Description", "picUrl", valve, Set.of(action));
        Game needForSpeedMW = new Game("Need For Speed: Most Wanted", Instant.parse("2012-10-30T00:00:00Z"), 80.0, Set.of(pc)
                , "Descript", "picUrl", electronicArts, Set.of(racing));
        Game needForSpeedU = new Game("Need For Speed: Underground", Instant.parse("2012-11-17T00:00:00Z"), 50.0, Set.of(pc),
                "Descript", "picUrl", electronicArts, Set.of(racing));
        Game needForSpeedC = new Game("Need For Speed: Carbon", Instant.parse("2012-10-27T00:00:00Z"), 70.0, Set.of(pc),
                "Descript", "picUrl", electronicArts, Set.of(racing));

        games = Map.of("Counter Strike", counterStrike, "Need For Speed MW", needForSpeedMW, "Need For Speed U",
                needForSpeedU, "Need For Speed C", needForSpeedC);

        gameRepository = mock(GameRepository.class);
        gameService = new GameService(gameRepository);
    }

    @Test
    public void shouldGetAllGames() {
        when(gameRepository.findAll()).thenReturn(List.of(games.get("Counter Strike"), games.get("Need For Speed MW")));

        List<Game> gamesList = gameService.getAllGames();

        assertThat(gamesList).hasSize(2).extracting(Game::getName, game -> game.getReleaseDate().toString(),
                Game::getPrice, Game::getDescription, Game::getPictureUrl,
                Game::getPublisherName, game -> {
                    ArrayList<Genre> genres = new ArrayList<>(game.getGenres());
                    if (!genres.isEmpty()) {
                        return genres.get(0).getName();
                    }
                    return "";
                }).contains(tuple("Counter Strike 2.0", "2023-04-10T00:00:00Z", 100.0, "Description", "picUrl",
                "Valve"
                , "ACTION"), tuple("Need For Speed: Most Wanted", "2012-10-30T00:00:00Z", 80.0, "Descript",
                "picUrl", "Electronic Arts", "RACING"));
    }

    @Test
    public void shouldGetGameById() {
        Game cs = games.get("Counter Strike");

        when(gameRepository.findById("Counter Strike")).thenReturn(Optional.of(games.get("Counter Strike")));

        Game foundGame = gameService.getGameById("Counter Strike");
        assertThat(foundGame).extracting(Game::getName, game -> game.getReleaseDate().toString(), Game::getPrice,
                Game::getPlatforms, Game::getDescription, Game::getPictureUrl,
                Game::getPublisherName).containsExactly(cs.getName(), cs.getReleaseDate().toString(),
                cs.getPrice(), cs.getPlatforms(), cs.getDescription(), cs.getPictureUrl(),
                cs.getPublisherName());
    }

    @Test
    public void shouldAddGame() {
        Platform ps5 = new Platform("PS5");
        Publisher sonyInteractiveEntertainment = new Publisher(1L, "Sony Interactive Entertainment", "LogoPictureUrl"
                , 1993, "Publisher description");
        Genre action = new Genre("ACTION");
        Genre adventure = new Genre("ADVENTURE");
        Game godOfWar = new Game("God Of War", Instant.parse("2022-11-09T00:00:00Z"), 70.0, Set.of(ps5), "Description",
                "picUrl", sonyInteractiveEntertainment, Set.of(action, adventure));

        when(gameRepository.findById(godOfWar.getName())).thenReturn(Optional.empty());
        when(gameRepository.save(godOfWar)).thenReturn(godOfWar);

        Game newGame = gameService.addGame(godOfWar);
        verify(gameRepository).save(godOfWar);
        assertThat(newGame).extracting(Game::getName, game -> game.getReleaseDate().toString(), Game::getPrice,
                Game::getPlatforms, Game::getDescription, Game::getPictureUrl,
                Game::getPublisherName).containsExactly(godOfWar.getName(),
                godOfWar.getReleaseDate().toString(), godOfWar.getPrice(), godOfWar.getPlatforms(),
                godOfWar.getDescription(), godOfWar.getPictureUrl(), godOfWar.getPublisherName());
    }

    @Test
    public void addGameShouldThrowException() {
        Platform pc = new Platform("PC");
        Publisher sonyInteractiveEntertainment = new Publisher(1L, "Sony Interactive Entertainment", "LogoPictureUrl"
                , 1993, "Publisher description");
        Genre adventure = new Genre("ADVENTURE");
        Game counterStrike = new Game("Counter Strike 2.0", Instant.parse("2023-04-10T00:00:00Z"), 100.0, Set.of(pc),
                "Description", "picUrl", sonyInteractiveEntertainment, Set.of(adventure));

        when(gameRepository.existsById(counterStrike.getName())).thenReturn(true);

        EntityExistsException exception = assertThrows(EntityExistsException.class, () -> gameService.addGame(counterStrike));

        assertEquals(exception.getMessage(), String.format("Game with name \"%s\" already exists.", counterStrike.getName()));
    }

    @Test
    public void shouldDeleteGame() {
        Game gameToDelete = games.get("Need For Speed C");

        when(gameRepository.findById(gameToDelete.getName())).thenReturn(Optional.of(gameToDelete));
        gameService.deleteGame(gameToDelete.getName());

        verify(gameRepository, times(1)).delete(gameToDelete);
    }

    @Test
    public void deleteGameShouldThrowException() {
        Game gameToDelete = games.get("Need For Speed C");

        when(gameRepository.findById(gameToDelete.getName())).thenReturn(Optional.empty());

        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> gameService.deleteGame(gameToDelete.getName()));
        assertEquals(exception.getMessage(), String.format("Game with name \"%s\" does not exist.", gameToDelete.getName()));
    }


    @Test
    public void shouldUpdateGame() {
        Platform ps3 = new Platform("PS3");
        Publisher electronicArts = new Publisher(1L, "Electronic Arts", "LogoPictureUrl", 2000, "Publisher " +
                "description");
        Genre racing = new Genre("RACING");
        Game updatedGame = new Game("Need For Speed: Underground", Instant.parse("2012-10-30T00:00:00Z"), 70.0, Set.of(ps3),
                "Updated description", "picUrl", electronicArts, Set.of(racing));

        Game gameToUpdate = games.get("Need For Speed U");
        when(gameRepository.findById(gameToUpdate.getName())).thenReturn(Optional.of(gameToUpdate));
        when(gameRepository.save(updatedGame)).thenReturn(updatedGame);
        Game result = gameService.updateGame(updatedGame.getName(), updatedGame);

        assertThat(result).extracting(Game::getName, game -> game.getReleaseDate().toString(), Game::getPrice,
                Game::getPlatforms, Game::getDescription, Game::getPictureUrl,
                Game::getPublisherName).containsExactly(updatedGame.getName(),
                updatedGame.getReleaseDate().toString(), updatedGame.getPrice(), updatedGame.getPlatforms(),
                updatedGame.getDescription(), updatedGame.getPictureUrl(), updatedGame.getPublisherName());
    }
}