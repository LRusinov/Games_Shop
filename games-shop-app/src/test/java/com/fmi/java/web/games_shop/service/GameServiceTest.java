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

class GameServiceTest {

    @Mock
    private static GameRepository gameRepository;
    @Mock
    private static PlatformRepository platformRepository;
    @Mock
    private static GenreRepository genreRepository;

    @Mock
    private static PublisherRepository publisherRepository;

    private static GameService gameService;

    private static Map<String, Game> games;

    private static final Publisher electronicArts = new Publisher(2L, "Electronic Arts", "LogoPictureUrl", 2000, "Publisher " +
            "description");

    private static final Publisher sonyInteractiveEntertainment = new Publisher(1L, "Sony Interactive Entertainment", "LogoPictureUrl"
            , 1993, "Publisher description");
    private static final Genre action = new Genre("ACTION");
    private static final Genre adventure = new Genre("ADVENTURE");

    private static final Genre racing = new Genre("RACING");

    private static final Platform pc = new Platform("PC");

    private static final Platform ps5 = new Platform("PS5");

    @BeforeAll
    public static void setUp() {

        Publisher valve = new Publisher(1L, "Valve", "LogoPictureUrl", 2000, "Publisher description");
        Game counterStrike = new Game("Counter Strike 2.0", 100.0, Set.of(action), Set.of(pc),
                "Description", Instant.parse("2023-04-10T00:00:00Z"), valve, "picUrl");
        Game needForSpeedMW = new Game("Need For Speed: Most Wanted", 80.0, Set.of(racing), Set.of(pc),
                "Descript", Instant.parse("2012-10-30T00:00:00Z"), electronicArts, "picUrl");
        Game needForSpeedU = new Game("Need For Speed: Underground", 50.0, Set.of(racing), Set.of(pc),
                "Descript", Instant.parse("2012-11-17T00:00:00Z"), electronicArts, "picUrl");
        Game needForSpeedC = new Game("Need For Speed: Carbon", 70.0, Set.of(racing), Set.of(pc),
                "Descript", Instant.parse("2012-10-27T00:00:00Z"), electronicArts, "picUrl");
        Game godOfWar = new Game("God Of War", 70.0, Set.of(action, adventure), Set.of(ps5),
                "Description", Instant.parse("2022-11-09T00:00:00Z"), sonyInteractiveEntertainment,
                "picUrl");

        games = Map.of("Counter Strike", counterStrike, "Need For Speed MW", needForSpeedMW, "Need For Speed U",
                needForSpeedU, "Need For Speed C", needForSpeedC, "God Of War", godOfWar);

        gameRepository = mock(GameRepository.class);
        genreRepository = mock(GenreRepository.class);
        publisherRepository = mock(PublisherRepository.class);
        platformRepository = mock(PlatformRepository.class);
        gameService = new GameService(gameRepository, platformRepository, genreRepository, publisherRepository);
    }

    @Test
    public void shouldGetAllGames() {
        when(gameRepository.findAll()).thenReturn(List.of(games.get("Counter Strike"), games.get("Need For Speed MW")));

        List<GameDto> gamesList = gameService.getAllGames();

        assertThat(gamesList)
                .hasSize(2)
                .extracting(GameDto::name, game -> game.releaseDate().toString(),
                        GameDto::price, GameDto::description, GameDto::pictureUrl,
                        GameDto::publisher, game -> {
                            ArrayList<String> genres = new ArrayList<>(game.genres());
                            if (!genres.isEmpty()) {
                                return genres.get(0);
                            }
                            return "";
                        })
                .contains(tuple("Counter Strike 2.0", "2023-04-10T00:00:00Z", 100.0, "Description", "picUrl",
                                "Valve", "ACTION"),
                        tuple("Need For Speed: Most Wanted", "2012-10-30T00:00:00Z", 80.0, "Descript",
                                "picUrl", "Electronic Arts", "RACING"));
    }

    @Test
    public void shouldGetGameById() {
        Game cs = games.get("Counter Strike");

        when(gameRepository.findById("Counter Strike")).thenReturn(Optional.of(games.get("Counter Strike")));

        GameDto foundGame = gameService.getGameById("Counter Strike");
        assertThat(foundGame).extracting(GameDto::name, GameDto::price, gameDto -> gameDto.genres().toArray().length,
                        gameDto -> gameDto.platforms().toArray().length, GameDto::description, game -> game.releaseDate().toString(),
                        GameDto::publisher, GameDto::pictureUrl)
                .containsExactly(cs.getName(), cs.getGenres().toArray().length, cs.getPlatforms().toArray().length,
                        cs.getPrice(), cs.getDescription(), cs.getReleaseDate().toString(),
                        cs.getPublisherName(), cs.getPictureUrl());
    }

    @Test
    public void shouldAddGame() {
        GameDto dtoGodOfWar = new GameDto("God Of War", 70.0, Set.of("ADVENTURE"), Set.of("PS5"), "Description", Instant.parse("2022-11-09T00:00:00Z"),
                "Sony Interactive Entertainment", "picUrl");
        Game entityGodOfWar = games.get("God Of War");

        when(gameRepository.findById(dtoGodOfWar.name())).thenReturn(Optional.empty());
        when(publisherRepository.findByname(sonyInteractiveEntertainment.getName())).thenReturn(Optional.of(sonyInteractiveEntertainment));
        when(platformRepository.findById(ps5.getName())).thenReturn(Optional.of(ps5));
        when(genreRepository.findById(adventure.getName())).thenReturn(Optional.of(adventure));
        when(gameRepository.save(any(Game.class))).thenReturn(entityGodOfWar);

        GameDto newGame = gameService.addGame(dtoGodOfWar);
        assertThat(newGame).extracting(GameDto::name, game -> game.releaseDate().toString(), GameDto::price,
                        GameDto::platforms, GameDto::description, GameDto::pictureUrl, GameDto::publisher)
                .containsExactly(dtoGodOfWar.name(), dtoGodOfWar.releaseDate().toString(),
                        dtoGodOfWar.price(), dtoGodOfWar.platforms(), dtoGodOfWar.description(),
                        dtoGodOfWar.pictureUrl(), dtoGodOfWar.publisher());
    }

    @Test
    public void addGameShouldThrowException() {
        GameDto counterStrike = new GameDto("Counter Strike 2.0", 100.0, Set.of("ADVENTURE"), Set.of("PC"),
                "Description", Instant.parse("2023-04-10T00:00:00Z"), "Sony Interactive Entertainment", "picUrl");

        when(gameRepository.existsById(counterStrike.name())).thenReturn(true);

        EntityExistsException exception = assertThrows(EntityExistsException.class, () -> gameService.addGame(counterStrike));

        assertEquals(exception.getMessage(), String.format("Game with name \"%s\" already exists.", counterStrike.name()));
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
        Game nfsEntity = new Game("Need For Speed: Underground", 70.0, Set.of(racing), Set.of(ps5), "Updated description", Instant.parse("2012-10-30T00:00:00Z")
                , electronicArts, "picUrl");

        GameDto updatedGame = new GameDto("Need For Speed: Underground", 70.0, Set.of("RACING"), Set.of("PS5"), "Updated description", Instant.parse("2012-10-30T00:00:00Z")
                , "Electronic Arts", "picUrl");

        Game gameToUpdate = games.get("Need For Speed U");
        when(publisherRepository.findByname(electronicArts.getName())).thenReturn(Optional.of(electronicArts));
        when(platformRepository.findById(ps5.getName())).thenReturn(Optional.of(ps5));
        when(genreRepository.findById(racing.getName())).thenReturn(Optional.of(racing));
        when(gameRepository.findById(gameToUpdate.getName())).thenReturn(Optional.of(gameToUpdate));
        when(gameRepository.save(nfsEntity)).thenReturn(nfsEntity);
        GameDto result = gameService.updateGame(updatedGame.name(), updatedGame);

        assertThat(result).extracting(GameDto::name, game -> game.releaseDate().toString(), GameDto::price,
                        GameDto::platforms, GameDto::description, GameDto::pictureUrl, GameDto::publisher)
                .containsExactly(updatedGame.name(), updatedGame.releaseDate().toString(),
                        updatedGame.price(), updatedGame.platforms(), updatedGame.description(),
                        updatedGame.pictureUrl(), updatedGame.publisher());
    }
}