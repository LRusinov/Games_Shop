package com.fmi.java.web.games_shop.controller;

import com.fmi.java.web.games_shop.dto.PublisherDto;
import com.fmi.java.web.games_shop.model.Platform;
import com.fmi.java.web.games_shop.model.Publisher;
import com.fmi.java.web.games_shop.service.PublisherService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/games-shop/publishers")
@CrossOrigin(origins = "http://localhost:4200")
public class PublisherController {

    private final PublisherService publisherService;

    public PublisherController(final PublisherService publisherService) {
        this.publisherService = publisherService;
    }

    @GetMapping
    List<PublisherDto> getAllPublishers() {
        return publisherService.getAllPublishers().stream().map(this::entityToDto).toList();
    }

    @GetMapping("/{name}")
    public PublisherDto getGameById(@PathVariable("name") final String name) {
        return entityToDto(publisherService.getPublisherByName(name));
    }

    private PublisherDto entityToDto(final Publisher publisher) {
        return new PublisherDto(publisher.getId(), publisher.getName(), publisher.getLogoPictureUrl(), publisher.getYearOfCreation(), publisher.getDescription());
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<Publisher> createGame(@RequestBody final PublisherDto publisherDto) {
        final Publisher newPlatform = publisherService.addPublisher(dtoToEntity(publisherDto));
        return new ResponseEntity<>(newPlatform, HttpStatus.CREATED);
    }

    private static Publisher dtoToEntity(final PublisherDto publisherDto) {
        return new Publisher(publisherDto.id(), publisherDto.name(), publisherDto.logoPictureUrl(), publisherDto.yearOfCreation(), publisherDto.description());
    }

    @DeleteMapping("/{name}")
    @ResponseBody
    public ResponseEntity<Boolean> deleteGame(@PathVariable final String name) {
        publisherService.deletePublisher(name);
        return new ResponseEntity<>(Boolean.TRUE, HttpStatus.ACCEPTED);

    }
}
