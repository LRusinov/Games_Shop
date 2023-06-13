package com.fmi.java.web.games_shop.service;

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
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class PublisherServiceTest {

    @Mock
    private static PublisherRepository publisherRepository;

    private static PublisherService publisherService;

    private static Map<String, Publisher> publishers;

    @BeforeAll
    static void setUp() {

        Publisher valve = new Publisher(1L, "Valve", "LogoPictureUrl", 2000, "Publisher description");
        Publisher electronicArts = new Publisher(2L, "Electronic Arts", "LogoPictureUrl", 2000, "Publisher " +
                "description");
        Publisher ubisoft = new Publisher(3L, "Ubisoft",
                "LogoPictureUrl", 1986,
                "Description");


        publishers = Map.of("Valve", valve, "Electronic Arts", electronicArts, "Ubisoft", ubisoft);

        publisherRepository = mock(PublisherRepository.class);
        publisherService = new PublisherService(publisherRepository);
    }

    @Test
    void shouldGetAllPublishers() {
        when(publisherRepository.findAll()).thenReturn(List.of(publishers.get("Valve"),
                publishers.get("Electronic " + "Arts")));

        List<Publisher> publishersList = publisherService.getAllPublishers();
        assertThat(publishersList).extracting(Publisher::getId, Publisher::getName, Publisher::getLogoPictureUrl,
                Publisher::getYearOfCreation, Publisher::getDescription).contains(tuple(1L, "Valve", "LogoPictureUrl"
                , 2000, "Publisher " + "description"), tuple(2L, "Electronic Arts", "LogoPictureUrl", 2000,
                "Publisher " + "description"));
    }

    @Test
    void shouldGetPublisherByName() {
        Publisher valve = publishers.get("Valve");
        when(publisherRepository.findByname("Valve")).thenReturn(Optional.of(publishers.get("Valve")));

        Publisher foundPublisher = publisherService.getPublisherByName("Valve");
        assertThat(foundPublisher).extracting(Publisher::getId, Publisher::getName, Publisher::getLogoPictureUrl,
                Publisher::getYearOfCreation, Publisher::getDescription).containsExactly(valve.getId(),
                valve.getName(), valve.getLogoPictureUrl(), valve.getYearOfCreation(), valve.getDescription());
    }

    @Test
    void shouldAddPublisher() {
        Publisher sonyInteractiveEntertainment = new Publisher(3L, "Sony Interactive Entertainment", "LogoPictureUrl"
                , 1993, "Publisher description");

        when(publisherRepository.save(sonyInteractiveEntertainment)).thenReturn(sonyInteractiveEntertainment);

        Publisher newPublisher = publisherService.addPublisher(sonyInteractiveEntertainment);
        verify(publisherRepository).save(sonyInteractiveEntertainment);
        assertThat(newPublisher).extracting(Publisher::getId, Publisher::getName,
                        Publisher::getLogoPictureUrl, Publisher::getYearOfCreation, Publisher::getDescription)
                .containsExactly(sonyInteractiveEntertainment.getId(), sonyInteractiveEntertainment.getName(),
                        sonyInteractiveEntertainment.getLogoPictureUrl(),
                        sonyInteractiveEntertainment.getYearOfCreation(),
                        sonyInteractiveEntertainment.getDescription());
    }

    @Test
    void addPublisherShouldThrowException() {
        Publisher sonyInteractiveEntertainment = new Publisher(1L, "Sony Interactive Entertainment", "LogoPictureUrl"
                , 1993, "Publisher description");

        when(publisherRepository.findByname(sonyInteractiveEntertainment.getName())).thenReturn(Optional.of(sonyInteractiveEntertainment));

        assertThrows(EntityExistsException.class, () -> publisherService.addPublisher(sonyInteractiveEntertainment));
    }

    @Test
    void shouldDeletePublisher() {
        Publisher publisherToDelete = publishers.get("Ubisoft");
        when(publisherRepository.findByname(publisherToDelete.getName())).thenReturn(Optional.of(publisherToDelete));
        publisherService.deletePublisher(publisherToDelete.getName());

        verify(publisherRepository, times(1)).delete(publisherToDelete);
    }

    @Test
    void deletePublisherShouldThrowException() {
        Publisher publisherToDelete = publishers.get("Ubisoft");
        when(publisherRepository.findByname(publisherToDelete.getName())).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class,
                () -> publisherService.deletePublisher(publisherToDelete.getName()));
    }
}