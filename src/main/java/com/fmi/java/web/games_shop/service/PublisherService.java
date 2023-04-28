package com.fmi.java.web.games_shop.service;

import com.fmi.java.web.games_shop.model.Publisher;
import com.fmi.java.web.games_shop.repository.PublisherRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PublisherService {
    private final PublisherRepository publisherRepository;

    public PublisherService(PublisherRepository publisherRepository) {
        this.publisherRepository = publisherRepository;
    }

    public List<Publisher> getAllPublishers() {
        return publisherRepository.findAll();
    }

    public Publisher getPublisherByName(String name) {
        return publisherRepository.findByname(name);
    }
}
