package com.fmi.java.web.games_shop.service;

import com.fmi.java.web.games_shop.dto.PublisherDto;
import com.fmi.java.web.games_shop.exception.EntityExistsException;
import com.fmi.java.web.games_shop.exception.EntityNotFoundException;
import com.fmi.java.web.games_shop.model.Publisher;
import com.fmi.java.web.games_shop.repository.PublisherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class PublisherService {
    private final PublisherRepository publisherRepository;

    private static final String EXCEPTION_MESSAGE = "Publisher with name \"%s\" does not exist.";

    public List<Publisher> getAllPublishers() {
        return publisherRepository.findAll();
    }

    public Publisher getPublisherByName(final String name) {
        return publisherRepository.findByname(name).orElseThrow(() -> new EntityNotFoundException(String.format(EXCEPTION_MESSAGE, name)));
    }

    public PublisherDto addPublisher(final PublisherDto newPublisher) {
        String publisherName = newPublisher.name();
        if (publisherRepository.findByname(publisherName).isPresent()) {
            throw new EntityExistsException(String.format("Publisher with name \"%s\" already exists.", publisherName));
        } else {
            return entityToDto(publisherRepository.save(dtoToEntity(newPublisher)));
        }
    }

    public void deletePublisher(final String name) {
        final Publisher publisherToDelete = publisherRepository.findByname(name).orElseThrow(() -> new EntityNotFoundException(String.format(EXCEPTION_MESSAGE, name)));
        publisherRepository.delete(publisherToDelete);
    }

    private PublisherDto entityToDto(Publisher publisher) {
        return new PublisherDto(publisher.getId(), publisher.getName(), publisher.getLogoPictureUrl(), publisher.getYearOfCreation(), publisher.getDescription());
    }

    private Publisher dtoToEntity(PublisherDto publisherDto) {
        return new Publisher(publisherDto.id(), publisherDto.name(), publisherDto.logoPictureUrl(), publisherDto.yearOfCreation(), publisherDto.description());
    }
}
