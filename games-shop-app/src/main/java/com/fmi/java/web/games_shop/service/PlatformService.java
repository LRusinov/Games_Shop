package com.fmi.java.web.games_shop.service;

import com.fmi.java.web.games_shop.exception.EntityExistsException;
import com.fmi.java.web.games_shop.exception.EntityNotFoundException;
import com.fmi.java.web.games_shop.model.Platform;
import com.fmi.java.web.games_shop.repository.PlatformRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlatformService {
    private final PlatformRepository platformRepository;

    public PlatformService(final PlatformRepository platformRepository) {
        this.platformRepository = platformRepository;
    }

    public List<Platform> getAllPlatforms() {
        return platformRepository.findAll();
    }

    public Platform addPlatform(final Platform newPlatform) {
        String platformName= newPlatform.getName();
        if(platformRepository.existsById(platformName)){
            throw new EntityExistsException(String.format("Platform \"%s\" already exists.",platformName));
        }else{
        return platformRepository.save(newPlatform);}
    }

    public void deletePlatform(final String name) {
        final Platform platformToDelete = platformRepository.findById(name).orElseThrow(() -> new EntityNotFoundException(String.format("Platform with name \"%s\" does not exist.", name)));
        platformRepository.delete(platformToDelete);
    }
}
