package com.fmi.java.web.games_shop.service;

import com.fmi.java.web.games_shop.dto.PlatformDto;
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

class PlatformServiceTest {

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
        List<PlatformDto> platformsList = platformService.getAllPlatforms();

        assertThat(platformsList).extracting(PlatformDto::name).contains("PC", "PS4");
    }

    @Test
    public void shouldAddPlatform() {
        PlatformDto dtoPlatform = new PlatformDto("XBOX ONE");
        Platform entityPlatform = new Platform("XBOX ONE");

        when(platformRepository.existsById(dtoPlatform.name())).thenReturn(false);
        when(platformRepository.save(entityPlatform)).thenReturn(entityPlatform);
        PlatformDto result = platformService.addPlatform(dtoPlatform);

        verify(platformRepository).save(entityPlatform);
        assertEquals(result.name(), dtoPlatform.name());
    }

    @Test
    public void addPlatformShouldThrowException() {
        PlatformDto newPlatform = new PlatformDto("OPEN-WORLD");

        when(platformRepository.existsById(newPlatform.name())).thenReturn(true);

        EntityExistsException exception = assertThrows(EntityExistsException.class, () -> platformService.addPlatform(newPlatform));
        assertEquals(exception.getMessage(), (String.format("Platform \"%s\" already exists.", newPlatform.name())));
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

        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> platformService.deletePlatform(platformToDelete.getName()));
        assertEquals(exception.getMessage(), (String.format("Platform with name \"%s\" does not exist.", platformToDelete.getName())));
    }
}