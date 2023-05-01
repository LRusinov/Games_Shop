package com.fmi.java.web.games_shop.service;

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
}
