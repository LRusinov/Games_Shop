package com.fmi.java.web.games_shop.service;

import com.fmi.java.web.games_shop.dto.PublisherDto;
import com.fmi.java.web.games_shop.exception.EntityExistsException;
import com.fmi.java.web.games_shop.exception.EntityNotFoundException;
import com.fmi.java.web.games_shop.model.Publisher;
import com.fmi.java.web.games_shop.repository.PublisherRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.tuple;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

class PublisherServiceTest {

    @Mock
    private static PublisherRepository publisherRepository;

    private static PublisherService publisherService;

    private static Map<String, Publisher> publishers;

    @BeforeAll
    public static void setUp() {
        Publisher valve = new Publisher(1L, "Valve", "LogoPictureUrl", 2000,
                "Publisher description");
        Publisher electronicArts = new Publisher(2L, "Electronic Arts", "LogoPictureUrl", 2000,
                "Publisher description");
        Publisher ubisoft = new Publisher(3L, "Ubisoft", "LogoPictureUrl", 1986,
                "Description");
        publishers = Map.of("Valve", valve, "Electronic Arts", electronicArts, "Ubisoft", ubisoft);

        publisherRepository = mock(PublisherRepository.class);
        publisherService = new PublisherService(publisherRepository);
    }

    @Test
    public void shouldGetAllPublishers() {
        when(publisherRepository.findAll()).thenReturn(List.of(publishers.get("Valve"),
                publishers.get("Electronic " + "Arts")));

        List<Publisher> publishersList = publisherService.getAllPublishers();

        assertThat(publishersList)
                .extracting(Publisher::getId, Publisher::getName, Publisher::getLogoPictureUrl,
                        Publisher::getYearOfCreation, Publisher::getDescription)
                .contains(tuple(1L, "Valve", "LogoPictureUrl", 2000, "Publisher " + "description"),
                        tuple(2L, "Electronic Arts", "LogoPictureUrl", 2000, "Publisher " + "description"));
    }

    @Test
    public void shouldGetPublisherByName() {
        Publisher valve = publishers.get("Valve");

        when(publisherRepository.findByname("Valve")).thenReturn(Optional.of(publishers.get("Valve")));

        Publisher foundPublisher = publisherService.getPublisherByName("Valve");
        assertThat(foundPublisher)
                .extracting(Publisher::getId, Publisher::getName, Publisher::getLogoPictureUrl,
                        Publisher::getYearOfCreation, Publisher::getDescription)
                .containsExactly(valve.getId(), valve.getName(), valve.getLogoPictureUrl(), valve.getYearOfCreation(),
                        valve.getDescription());
    }

    @Test
    public void getPublisherByNameShouldThrowException() {
        Publisher valve = publishers.get("Valve");

        when(publisherRepository.findByname("Valve")).thenReturn(Optional.empty());

        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> publisherService.getPublisherByName(valve.getName()));
        assertEquals(exception.getMessage(), String.format("Publisher with name \"%s\" does not exist.", valve.getName()));
    }

    @Test
    public void shouldAddPublisher() {
        PublisherDto dtoPublisher = new PublisherDto(3L, "Sony Interactive Entertainment", "LogoPictureUrl"
                , 1993, "Publisher description");
        Publisher entityPublisher = new Publisher(3L, "Sony Interactive Entertainment", "LogoPictureUrl"
                , 1993, "Publisher description");

        when(publisherRepository.findByname(dtoPublisher.name())).thenReturn(Optional.empty());
        given(publisherRepository.save(entityPublisher)).willReturn(entityPublisher);
        PublisherDto newPublisher = publisherService.addPublisher(dtoPublisher);

        verify(publisherRepository).save(entityPublisher);
        assertThat(newPublisher).extracting(PublisherDto::id, PublisherDto::name,
                        PublisherDto::logoPictureUrl, PublisherDto::yearOfCreation, PublisherDto::description)
                .containsExactly(dtoPublisher.id(), dtoPublisher.name(),
                        dtoPublisher.logoPictureUrl(),
                        dtoPublisher.yearOfCreation(),
                        dtoPublisher.description());
    }

    @Test
    public void addPublisherShouldThrowException() {
        PublisherDto dtoPublisher = new PublisherDto(1L, "Sony Interactive Entertainment", "LogoPictureUrl"
                , 1993, "Publisher description");
        Publisher entityPublisher = new Publisher(1L, "Sony Interactive Entertainment", "LogoPictureUrl"
                , 1993, "Publisher description");

        when(publisherRepository.findByname(dtoPublisher.name())).thenReturn(Optional.of(entityPublisher));

        EntityExistsException exception = assertThrows(EntityExistsException.class, () -> publisherService.addPublisher(dtoPublisher));
        assertEquals(exception.getMessage(), String.format("Publisher with name \"%s\" already exists.", dtoPublisher.name()));
    }

    @Test
    public void shouldDeletePublisher() {
        Publisher publisherToDelete = publishers.get("Ubisoft");

        when(publisherRepository.findByname(publisherToDelete.getName())).thenReturn(Optional.of(publisherToDelete));
        publisherService.deletePublisher(publisherToDelete.getName());

        verify(publisherRepository, times(1)).delete(publisherToDelete);
    }

    @Test
    public void deletePublisherShouldThrowException() {
        Publisher publisherToDelete = publishers.get("Ubisoft");

        when(publisherRepository.findByname(publisherToDelete.getName())).thenReturn(Optional.empty());

        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class,
                () -> publisherService.deletePublisher(publisherToDelete.getName()));
        assertEquals(exception.getMessage(), String.format("Publisher with name \"%s\" does not exist.", publisherToDelete.getName()));
    }
}