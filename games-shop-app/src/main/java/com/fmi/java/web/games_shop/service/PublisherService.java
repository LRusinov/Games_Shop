package com.fmi.java.web.games_shop.service;

import com.fmi.java.web.games_shop.exception.EntityExistsException;
import com.fmi.java.web.games_shop.exception.EntityNotFoundException;
import com.fmi.java.web.games_shop.model.Publisher;
import com.fmi.java.web.games_shop.repository.PublisherRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PublisherService {
    private final PublisherRepository publisherRepository;

    public PublisherService(final PublisherRepository publisherRepository) {
        this.publisherRepository = publisherRepository;
    }

    public List<Publisher> getAllPublishers() {
        return publisherRepository.findAll();
    }

    public Publisher getPublisherByName(final String name) {
        return publisherRepository.findByname(name).orElseThrow(() -> new EntityNotFoundException(String.format("Platform with name \"%s\" does not exist.", name)));
    }

    public Publisher addPublisher(final Publisher newPublisher) {

        String publisherName= newPublisher.getName();
        if(publisherRepository.findByname(publisherName).isPresent()){
            throw new EntityExistsException(String.format("Publisher with name \"%s\" already exists.",publisherName));
        }else{
        return publisherRepository.save(newPublisher);}
    }

    public void deletePublisher(final String name) {
        final Publisher publisherToDelete = publisherRepository.findByname(name).orElseThrow(() -> new EntityNotFoundException(String.format("Platform with name \"%s\" does not exist.", name)));
        publisherRepository.delete(publisherToDelete);
    }
}
