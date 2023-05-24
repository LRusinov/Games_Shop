package com.fmi.java.web.games_shop.controller;

import com.fmi.java.web.games_shop.dto.PlatformDto;
import com.fmi.java.web.games_shop.model.Platform;
import com.fmi.java.web.games_shop.service.PlatformService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    List<PlatformDto> getAllPlatforms() {
        return platformService.getAllPlatforms().stream().map(platform -> new PlatformDto(platform.getName())).toList();
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<Platform> createGame(@RequestBody final PlatformDto platformDto) {
        final Platform newPlatform = platformService.addPlatform(new Platform(platformDto.name()));
        return new ResponseEntity<>(newPlatform, HttpStatus.CREATED);
    }

    @DeleteMapping("/{name}")
    @ResponseBody
    public ResponseEntity<Boolean> deleteGame(@PathVariable final String name) {
        platformService.deletePlatform(name);
        return new ResponseEntity<>(Boolean.TRUE, HttpStatus.OK);
    }
}
