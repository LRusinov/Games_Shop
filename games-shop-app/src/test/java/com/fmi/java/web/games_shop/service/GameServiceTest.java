package com.fmi.java.web.games_shop.service;

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
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class GameServiceTest {

    @Mock
    private static GameRepository gameRepository;

    private static GameService gameService;

    private static Map<String, Game> games;

    @BeforeAll
    static void setUp() {
        Platform pc = new Platform("PC");
        Publisher valve = new Publisher(1L, "Valve", "LogoPictureUrl", 2000, "Publisher description");
        Publisher electronicArts = new Publisher(1L, "Electronic Arts", "LogoPictureUrl", 2000, "Publisher description");
        Genre action = new Genre("ACTION");
        Genre racing = new Genre("RACING");
        Game counterStrike = new Game("Counter Strike 2.0", Instant.parse("2023-04-10T00:00:00Z"), 100.0, pc, "Description", "picUrl", valve, Set.of(action));
        Game needForSpeed = new Game("Need For Speed: Most Wanted", Instant.parse("2012-10-30T00:00:00Z"), 80.0, pc, "Descript", "picUrl", electronicArts, Set.of(racing));
        games = Map.of("Counter Strike", counterStrike, "Need For Speed", needForSpeed);
        gameRepository = mock(GameRepository.class);
        gameService = new GameService(gameRepository);
    }

    @Test
    void shouldGetAllGames() {
        given(gameRepository.findAll()).willReturn(List.of(games.get("Counter Strike"), games.get("Need For Speed")));
        List<Game> gamesList = gameService.getAllGames();

        assertThat(gamesList).hasSize(2).extracting(Game::getName, game -> game.getReleaseDate().toString(), Game::getPrice, game -> game.getPlatform().getName(), Game::getDescription, Game::getPictureUrl, game -> game.getPublisher().getName(), game -> {
            ArrayList<Genre> genres = new ArrayList<>(game.getGenres());
            if (!genres.isEmpty()) {
                return genres.get(0).getName();
            }
            return "";
        }).containsExactly(tuple("Counter Strike 2.0", "2023-04-10T00:00:00Z", 100.0, "PC", "Description", "picUrl", "Valve", "ACTION"), tuple("Need For Speed: Most Wanted", "2012-10-30T00:00:00Z", 80.0, "PC", "Descript", "picUrl", "Electronic Arts", "RACING"));
    }

    @Test
    void getGameById() {
        given(gameRepository.findById("Counter Strike")).willReturn(Optional.of(games.get("Counter Strike")));
        Game foundGame = gameService.getGameById("Counter Strike");

        assertEquals(games.get("Counter Strike"), foundGame);
    }

    @Test
    void addGame() {
    }

    @Test
    void deleteGame() {
    }

    @Test
    void updateGame() {
    }
}