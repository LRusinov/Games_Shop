package com.fmi.java.web.games_shop.controller;

import com.fmi.java.web.games_shop.dto.PublisherDto;
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
    public PublisherDto getPublisherById(@PathVariable("name") final String name) {
        return entityToDto(publisherService.getPublisherByName(name));
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<PublisherDto> createPublisher(@RequestBody final PublisherDto publisherDto) {
        return new ResponseEntity<>(publisherService.addPublisher(publisherDto), HttpStatus.CREATED);
    }

    @DeleteMapping("/{name}")
    @ResponseBody
    public ResponseEntity<Boolean> deletePublisher(@PathVariable final String name) {
        publisherService.deletePublisher(name);
        return new ResponseEntity<>(Boolean.TRUE, HttpStatus.ACCEPTED);
    }

    private PublisherDto entityToDto(final Publisher publisher) {
        return new PublisherDto(publisher.getId(), publisher.getName(), publisher.getLogoPictureUrl(),
                publisher.getYearOfCreation(), publisher.getDescription());
    }
}
