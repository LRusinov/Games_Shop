package com.fmi.java.web.games_shop.service;

import com.fmi.java.web.games_shop.exception.EntityExistsException;
import com.fmi.java.web.games_shop.exception.EntityNotFoundException;
import com.fmi.java.web.games_shop.model.Platform;
import com.fmi.java.web.games_shop.repository.PlatformRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public final class PlatformServiceTest {

    @Mock
    private static PlatformRepository platformRepository;

    private static PlatformService platformService;

    private static Map<String, Platform> platforms;

    @BeforeAll
    static void setUp() {
        Platform pc = new Platform("PC");
        Platform ps4 = new Platform("PS4");
        Platform ps5 = new Platform("PS5");

        platforms = Map.of("PC", pc, "PS4", ps4, "PS5", ps5);

        platformRepository = mock(PlatformRepository.class);
        platformService = new PlatformService(platformRepository);
    }

    @Test
    public void shouldGetAllPlatforms() {
        when(platformRepository.findAll()).thenReturn(List.of(platforms.get("PC"), platforms.get("PS4")));

        List<Platform> platformsList = platformService.getAllPlatforms();
        assertThat(platformsList).extracting(Platform::getName).contains("PC", "PS4");
    }

    @Test
    public void shouldAddPlatform() {
        Platform newPlatform = new Platform("XBOX ONE");
        when(platformRepository.save(newPlatform)).thenReturn(newPlatform);

        Platform result = platformService.addPlatform(newPlatform);
        verify(platformRepository).save(newPlatform);

        assertEquals(result.getName(), newPlatform.getName());
    }

    @Test
    public void addPlatformShouldThrowException() {
        Platform newPlatform = new Platform("OPEN-WORLD");
        when(platformRepository.existsById(newPlatform.getName())).thenReturn(true);

        assertThrows(EntityExistsException.class, () -> platformService.addPlatform(newPlatform));
    }

    @Test
    public void shouldDeletePlatform() {
        Platform platformToDelete = platforms.get("PS5");
        when(platformRepository.findById(platformToDelete.getName())).thenReturn(Optional.of(platformToDelete));
        platformService.deletePlatform(platformToDelete.getName());

        verify(platformRepository, times(1)).delete(platformToDelete);
    }

    @Test
    public void deletePlatformShouldThrowException() {
        Platform platformToDelete = platforms.get("PS5");
        when(platformRepository.findById(platformToDelete.getName())).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> platformService.deletePlatform(platformToDelete.getName()));
    }
}