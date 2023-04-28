package com.fmi.java.web.games_shop.controller;

import com.fmi.java.web.games_shop.model.Publisher;
import com.fmi.java.web.games_shop.service.PublisherService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/games-shop/publishers")
@CrossOrigin(origins = "http://localhost:4200")
public class PublisherController {

    private final PublisherService publisherService;

    public PublisherController(PublisherService publisherService) {
        this.publisherService = publisherService;
    }

    @GetMapping("/publishers")
    List<String> getAllPublishers() {
        return publisherService.getAllPublishers().stream().map(Publisher::getName).toList();
    }
}
