package com.fmi.java.web.games_shop.controller;

import com.fmi.java.web.games_shop.model.Platform;
import com.fmi.java.web.games_shop.service.PlatformService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/games-shop/platforms")
@CrossOrigin(origins = "http://localhost:4200")
public class PlatformController {

    private final PlatformService platformService;

    public PlatformController(final PlatformService platformService) {
        this.platformService = platformService;
    }

    @GetMapping
    List<String> getAllPlatforms() {
        return platformService.getAllPlatforms().stream().map(Platform::getName).toList();
    }
}
